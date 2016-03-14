package org.training.mapreduce;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hive.hcatalog.data.HCatRecord;
import org.apache.hive.hcatalog.data.schema.HCatSchema;
import org.apache.hive.hcatalog.mapreduce.HCatInputFormat;
import org.apache.hive.hcatalog.mapreduce.HCatOutputFormat;
import org.apache.hive.hcatalog.mapreduce.MultiOutputFormat;
import org.apache.hive.hcatalog.mapreduce.MultiOutputFormat.JobConfigurer;
import org.apache.hive.hcatalog.mapreduce.OutputJobInfo;
//import org.datanucleus.store.rdbms.table.Table;
import org.training.utils.DataParser;
import org.training.hcatmultipleinputs.HCatMultipleInputs;


public class MultipleInputsTest {

	@SuppressWarnings("rawtypes")
	public static class StandardDataLayoutMapper1 extends Mapper<WritableComparable, HCatRecord,Text,NullWritable> {
		private StandardDataLayout operationalDataStandardDataLayout = new StandardDataLayout();
		@Override
		protected void setup(Context context){
			
		}

		@Override
		protected void map(WritableComparable key, HCatRecord value,Context context)
				throws IOException, InterruptedException {
			if(DataParser.parse(value,operationalDataStandardDataLayout)){
				context.write(new Text(DataParser.toString(operationalDataStandardDataLayout)),NullWritable.get());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static class StandardDataLayoutMapper2 extends Mapper<WritableComparable, HCatRecord,Text,NullWritable> {
		private StandardDataLayout operationalDataStandardDataLayout = new StandardDataLayout();

		@Override
		protected void map(WritableComparable key, HCatRecord value,Context context)
				throws IOException, InterruptedException {
			if(DataParser.parse(value,operationalDataStandardDataLayout)){
				context.write(new Text(DataParser.toString(operationalDataStandardDataLayout)),NullWritable.get());
			}
		}
	}
	
	
	public static class Reduce extends Reducer<Text, NullWritable,NullWritable,HCatRecord> {
		int i = 0;
		@Override
		protected void reduce( Text key,
				java.lang.Iterable<NullWritable> values,Context context)
						throws IOException, InterruptedException {
			StandardDataLayout sdl = new StandardDataLayout();
			/**
			 *------------------------- 
			 *  Order of data expected
			 *-------------------------
			 * ----> Customer
			 * ----> StandardDataLayout
			 * ----> SMGlobstarDemo
			 * ----> SMGlobstarFinancial
			 */
			
			for(NullWritable value : values){
				// Emit the group name and ID as a record
				i++;
				if(DataParser.parse(key,sdl)){
				if(i%2 == 0) MultiOutputFormat.write("accomodator6", NullWritable.get(),(HCatRecord) DataParser.getRecord(sdl), context);
				else MultiOutputFormat.write("accomodator5", NullWritable.get(),(HCatRecord)DataParser.getRecord(sdl), context);
				}
			}
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception{
		Configuration conf = new Configuration();
		args = new GenericOptionsParser(conf, args).getRemainingArgs();
		for(String s : args) System.out.println(" args: "+s);
		// Get the input and output table names as arguments
		Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                              envName,
                              env.get(envName));
        }
		System.out.println("Starting !!!!!!");
		String inputTable1 = "accomodator3";
		String inputTable2 = "accomodator4";
		//Second table - need better naming
		String outputTableName1 = "accomodator5";
		String outputTableName2 = "accomodator6";
//		System.out.println("arguments 1 and 2 "+args[0]+" args2:"+args[1]+" args3:"f+args[2]);
		// Assume the default database
		String dbName = null;
		conf.set("mapred.job.queue.name","grms");
		Job job = new Job(conf, "UpdateOperationalDataStore");
		// initialize HCatOutputFormat
		job.setInputFormatClass(HCatInputFormat.class);
		job.setJarByClass(MultipleInputsTest.class);	
		//read all the input tables
//		HashMap<String,String> partitions = new HashMap<String,String>();
//		partitions.put("market", "FIM");
//		partitions.put("region","EMEA");
		HCatMultipleInputs.addInput(job, "accomodator3",dbName,null, StandardDataLayoutMapper1.class);
		HCatMultipleInputs.addInput(job, "accomodator4",dbName,null, StandardDataLayoutMapper2.class);
		//set inputs
		HCatInputFormat.setInput(job.getConfiguration(), dbName, "accomodator3");
		HCatInputFormat.setInput(job.getConfiguration(), dbName, "accomodator4");
		// Comparator and partitioner classes
		job.setReducerClass(Reduce.class);
//		job.setPartitionerClass(FirstPartitioner.class);
//		job.setSortComparatorClass(MapOutputComparator.class);	
//		job.setGroupingComparatorClass(ReducerGroupComparator.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		job.setOutputValueClass(HCatRecord.class);
		//Using MultiOutputFormat
		JobConfigurer configurer = MultiOutputFormat.createConfigurer(job);
		//output1
		configurer.addOutputFormat("accomodator5", HCatOutputFormat.class, NullWritable.class, HCatRecord.class);
		configurer.getJob("accomodator5").getConfiguration().set("mapred.job.queue.name","grms");
		HCatOutputFormat.setOutput(configurer.getJob("accomodator5"), OutputJobInfo.create(dbName, "accomodator5", null));
		HCatSchema s = HCatOutputFormat.getTableSchema(configurer.getJob("accomodator5").getConfiguration());
		HCatOutputFormat.setSchema(configurer.getJob("accomodator5"), s);
		//output2
		configurer.addOutputFormat("accomodator6", HCatOutputFormat.class, NullWritable.class, HCatRecord.class);
		configurer.getJob("accomodator6").getConfiguration().set("mapred.job.queue.name","grms");
		HCatOutputFormat.setOutput(configurer.getJob("accomodator6"), OutputJobInfo.create(dbName, "accomodator6", null));
		HCatSchema s2 = HCatOutputFormat.getTableSchema(configurer.getJob("accomodator6").getConfiguration());
		HCatOutputFormat.setSchema(configurer.getJob("accomodator6"), s2);
		//job.setOutputFormatClass(HCatOutputFormat.class);
		configurer.configure();
		job.setOutputFormatClass(MultiOutputFormat.class);

		job.waitForCompletion(true);
	}
}