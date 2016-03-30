package org.training.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hive.hcatalog.data.DefaultHCatRecord;
import org.apache.hive.hcatalog.data.HCatRecord;
import org.apache.hive.hcatalog.data.schema.HCatSchema;
import org.apache.hive.hcatalog.mapreduce.HCatInputFormat;
import org.apache.hive.hcatalog.mapreduce.HCatOutputFormat;
import org.apache.hive.hcatalog.mapreduce.MultiOutputFormat;
import org.apache.hive.hcatalog.mapreduce.MultiOutputFormat.JobConfigurer;
import org.apache.hive.hcatalog.mapreduce.OutputJobInfo;
import org.training.entities.Inputs;
import org.training.entities.SMGlobstarDemo;
import org.training.entities.SMGlobstarFinancial;
import org.training.hcatmultipleinputs.HCatMultipleInputs;
import org.training.utils.DataParser;

import com.beust.jcommander.JCommander;
//import org.datanucleus.store.rdbms.table.Table;


public class SampleMapreduce {

	enum MapCounters {
		MAP_NOT_PARSED,
		GS_DEMO_PROCESSED,
		GS_FIN_PROCESSED,
	}

	enum ReduceCounters {
		RED_NOT_PARSED,
	}
	private static String className = "SampleMapreduce";

	/* Static Helper Class to compare data */
	static class CompareData{
		Long cus_id_seq_no;
		Long act_no_key_da;
		String source;
		Boolean cycle;
		Long feedKey;
		/*Custid,Accountno,Source,Cycle,Feedkey*/
		boolean parse(Text input){		
			String[] parts = input.toString().split(",");
			if(parts.length<5) {
				System.out.println("Unable to parse input:"+input);
				return false;
			}
			cus_id_seq_no = getValue(parts[0]);
			act_no_key_da = getValue(parts[1]);
			source = parts[2];
			cycle = Boolean.parseBoolean(parts[3].trim());
			if(parts[4].contentEquals(""))feedKey = null;
			else
				feedKey = getValue(parts[4]);
			return true;
		}
		
		Long getValue(String str){
			String s1 = str.trim();
			if(s1.contentEquals("")) return Long.MIN_VALUE;
			else return Long.parseLong(s1);			
		}

		int getOrder(){
			if(source.contains("SMGlobstarDemo") || source.contains("SMTriumphDemo")) return 1;
			if(source.contains("SMGlobstarFinancial") || source.contains("SMTriumphDailyFinancial")) return 2;
			return 3;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((cus_id_seq_no == null) ? 0 : cus_id_seq_no
							.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompareData other = (CompareData) obj;
			if (cus_id_seq_no == null) {
				if (other.cus_id_seq_no != null)
					return false;
			} else if (cus_id_seq_no - other.cus_id_seq_no != 0)
				return false;
			return true;
		}

	} /* end of CompareData class */

	static class MapOutputComparator extends WritableComparator {
		protected MapOutputComparator() {
			super(Text.class, true);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public int compare(WritableComparable o1, WritableComparable o2) {
			Text p1 = (Text) o1;
			Text p2 = (Text) o2;
			CompareData op1 = new CompareData();
			CompareData op2 = new CompareData();
			if(op1.parse(p1)){
				if(op2.parse(p2)){
					if(op1.equals(op2)){
						if(op1.act_no_key_da - op2.act_no_key_da == 0) {
							if(op1.getOrder() - op2.getOrder() == 0){
								if(op1.feedKey - op2.feedKey == 0){
									if(op1.cycle && !op2.cycle) return 1;
									else return 0;
								}
								return op1.feedKey.compareTo(op2.feedKey);
							}
							else
								return (op1.getOrder()-op2.getOrder());
						} else return(op1.act_no_key_da.compareTo(op2.act_no_key_da));

					}else return (op1.cus_id_seq_no.compareTo(op2.cus_id_seq_no));
				}else return -1;
			}
			return 1;
		}
	}


	static class ReducerGroupComparator extends WritableComparator {
		protected ReducerGroupComparator() {
			super(Text.class, true);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public int compare(WritableComparable o1, WritableComparable o2) {
			Text p1 = (Text) o1;
			Text p2 = (Text) o2;
			CompareData op1 = new CompareData();
			CompareData op2 = new CompareData();
			if(op1.parse(p1)){
				if(op2.parse(p2)){
					return (op1.cus_id_seq_no.compareTo(op2.cus_id_seq_no));
				}else return -1;
			}
			return 1;
		}
	}


	public static class FirstPartitioner extends Partitioner<Text, DefaultHCatRecord> {
		@Override
		public int getPartition(Text key, DefaultHCatRecord value, int numPartitions) {
			Text p1 = (Text) key;
			CompareData op1 = new CompareData();   		    
			if(op1.parse(p1)){
				return ((int) (((op1.cus_id_seq_no).hashCode() & Long.MAX_VALUE) % numPartitions));
			}
			return 1;
		}
	}


	@SuppressWarnings("rawtypes")
	public static class SmartMergeGlobstarDemoMapper extends Mapper<WritableComparable, HCatRecord,Text,DefaultHCatRecord> {
		@Override
		protected void map(WritableComparable key, HCatRecord value,Context context)
				throws IOException, InterruptedException {

			String cus_id = (String) value.get(4);
			String cm13 = (String) value.get(7);
			Long feedkey = (Long) value.get(0);
			context.write(new Text(cus_id+","+cm13+",SMGlobstarDemo,"+false+","+feedkey),(DefaultHCatRecord)value);
			context.getCounter(MapCounters.GS_DEMO_PROCESSED).increment(1);
		}
	}


	@SuppressWarnings("rawtypes")
	public static class SmartMergeGlobstarFinancialMapper extends Mapper<WritableComparable, HCatRecord,Text,DefaultHCatRecord> {
		@Override
		protected void map(WritableComparable key, HCatRecord value,Context context)
				throws IOException, InterruptedException {
			String cus_id = (String) value.get(4);
			String cm13 = (String) value.get(7);
			String rectype = (String) value.get(10);
			Long feedkey = (Long) value.get(0);
			context.write(new Text(cus_id+","+cm13+",SMGlobstarFinancial,"+rectype.contentEquals("08_CYC")+","+feedkey),(DefaultHCatRecord)value);
			context.getCounter(MapCounters.GS_FIN_PROCESSED).increment(1);
		}
	}


	public static class Reduce extends Reducer<Text, HCatRecord, NullWritable,HCatRecord> {
		private SMGlobstarDemo sMGlobstarDemo = new SMGlobstarDemo();
		private SMGlobstarFinancial sMGlobstarFinancial = new SMGlobstarFinancial();
		private CompareData cD = new CompareData();

		@Override
		protected void setup(
				Context context)
						throws IOException, InterruptedException {
			System.out.println("In Setup Method!!");

		}

		private void processLogic(){
			System.out.println("processing Logic!!");
		}

		@Override
		protected void reduce( Text key,
				java.lang.Iterable<HCatRecord> values,Context context)
						throws IOException, InterruptedException {
			/**
			 *------------------------- 
			 *  Order of data expected
			 *-------------------------
			 * ----> Customer
			 * ----> Account
			 * ----> SMGlobstarDemo/SMTriumphDemo
			 * ----> SMGlobstarFinancial/SMTriumphDailyFinancial
			 * ----> SMTriumphCycleFinancial
			 */
			
			for(HCatRecord value : values){
				System.out.println("key: "+key+" value:"+value);
				
				if(cD.parse(key)){
					switch(cD.source){
					case "SMGlobstarDemo":
						if(DataParser.parseAndNullsToMins(value,sMGlobstarDemo)) {
							writeOutput("SMGlobstarDemo",NullWritable.get(), DataParser.getRecord(sMGlobstarDemo), context);
						}
						context.getCounter(ReduceCounters.RED_NOT_PARSED).increment(1);
						break;



					case "SMGlobstarFinancial":
						if(DataParser.parseAndNullsToMins(value,sMGlobstarFinancial)){
							writeOutput("SMGlobstarFinancial",NullWritable.get(), DataParser.getRecord(sMGlobstarFinancial), context);
						}
						context.getCounter(ReduceCounters.RED_NOT_PARSED).increment(1);
						break;

					default:
						throw new RuntimeException("Source type not found"+cD.source);
					}
				}
			}
			System.out.println("-------------------------------------------");
		}


		private static void writeOutput(String type,NullWritable key, HCatRecord value,Context context){
			try {
				MultiOutputFormat.write(type,key, value, context);
			} catch (Exception e) {
				System.out.println("error in getting record of type: "+type+" value: "+value);
				e.printStackTrace();
			}
		}

		protected void cleanUp(Context context)
				throws IOException, InterruptedException {

			System.out.println("In the clean up Method");
		}

	}



	public static void main(String[] args) throws Exception{
		Configuration conf = new Configuration();

		args = new GenericOptionsParser(conf, args).getRemainingArgs();
		// Get the input and output table names as arguments
		Inputs input = new Inputs();
		JCommander cmd = new JCommander(input);
		for(String s: args)System.out.println("arguments: "+s);
		cmd.parse(args);
		//		cmd.setVerbose(1);
		if(cmd.getColumnSize() < 5){
			System.out.println("invalid parameters");
			cmd.usage();
			System.exit(0);
		}
		System.out.println("Starting !!!!!!");

		conf.set("mapred.job.queue.name",input.getJobQueueName());
		Job job = Job.getInstance(conf, "Sample for Campus20 training");

		// initialize HCatOutputFormat
		job.setInputFormatClass(HCatInputFormat.class);
		job.setJarByClass(SampleMapreduce.class);	

		if(input.getProcessMarket().contentEquals("USD")){
		}
		else{
			HCatMultipleInputs.addInput(job, input.getSmartMergeDemoTable(),input.getInputDatabase(),null, SmartMergeGlobstarDemoMapper.class);
			HCatMultipleInputs.addInput(job, input.getSmartMergeFinancialTable(),input.getInputDatabase(),null, SmartMergeGlobstarFinancialMapper.class);
		}

		

		//set inputs
		HCatInputFormat.setInput(job.getConfiguration(), input.getInputDatabase(), input.getSmartMergeDemoTable());
		HCatInputFormat.setInput(job.getConfiguration(), input.getInputDatabase(), input.getSmartMergeFinancialTable());


		// Comparator and partitioner classes
		job.setReducerClass(Reduce.class);
		job.setPartitionerClass(FirstPartitioner.class);
		job.setSortComparatorClass(MapOutputComparator.class);	
		job.setGroupingComparatorClass(ReducerGroupComparator.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DefaultHCatRecord.class);
		job.setNumReduceTasks(input.getNumReducers());

		job.setOutputValueClass(HCatRecord.class);
		//Using MultiOutputFormat
		job.setOutputFormatClass(MultiOutputFormat.class);
		JobConfigurer configurer = MultiOutputFormat.createConfigurer(job);
		//output1
		configurer.addOutputFormat("SMGlobstarDemo", HCatOutputFormat.class, NullWritable.class, HCatRecord.class);
		configurer.getJob("SMGlobstarDemo").getConfiguration().set("mapred.job.queue.name","rdre");
		HCatOutputFormat.setOutput(configurer.getJob("SMGlobstarDemo"), OutputJobInfo.create(input.getOutputDatabase(), input.getOutputCustomerTable(), null));
		HCatSchema s = HCatOutputFormat.getTableSchema(configurer.getJob("SMGlobstarDemo").getConfiguration());
		HCatOutputFormat.setSchema(configurer.getJob("SMGlobstarDemo"), s);
		//output2
		configurer.addOutputFormat("SMGlobstarFinancial", HCatOutputFormat.class, NullWritable.class, HCatRecord.class);
		configurer.getJob("SMGlobstarFinancial").getConfiguration().set("mapred.job.queue.name","rdre");
		HCatOutputFormat.setOutput(configurer.getJob("SMGlobstarFinancial"), OutputJobInfo.create(input.getOutputDatabase(), input.getOutputAccountTable(), null));
		HCatSchema s2 = HCatOutputFormat.getTableSchema(configurer.getJob("SMGlobstarFinancial").getConfiguration());
		HCatOutputFormat.setSchema(configurer.getJob("SMGlobstarFinancial"), s2);

		configurer.configure();
		job.waitForCompletion(true);
		System.exit(0);

	}
}