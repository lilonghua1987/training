package org.training.entities;

import com.beust.jcommander.Parameter;
/* 
 * 
-queue rdre \
-reducers 15 \
-market FIM \
-inputdb rdredb \
-smart_merge_demo_table rdre_gstar_demographic \
-smart_merge_finanical_table rdre_gstar_financial \
-outputdb rdredb \	
-output_account_table rdre_temp_account \
-output_customer_table rdre_temp_customer \
 * */
public class Inputs {
	@Parameter(names = {"-queue","-q"}, description = "job Queue", required = true)
	private String jobQueueName;
	
	@Parameter(names = {"-reducers","-r"}, description = "number of reducers", required = true)
	private Integer numReducers;
	
	@Parameter(names = {"-market","-m"}, description = "market", required = true)
	private String processMarket;
	
	@Parameter(names = {"-inputdb","-i_db"}, description = "input database", required = true)
	private String inputDatabase;
	
	@Parameter(names = {"-smart_merge_demo_table","-sm_demo"}, description = "input smart merge demo table", required = true)
	private String smartMergeDemoTable;
	
	@Parameter(names = {"-smart_merge_finanical_table","-sm_fin"}, description = "input smart merge finanical table", required = true)
	private String smartMergeFinancialTable;
	
	@Parameter(names = {"-outputdb","-o_db"}, description = "output database", required = true)	
	private String outputDatabase;
	
	@Parameter(names = {"-output_account_table","-o_acc"}, description = "output account table", required = true)
	private String outputAccountTable;
	
	@Parameter(names = {"-output_customer_table","o_cus"}, description = "output customer table", required = true)
	private String outputCustomerTable;
	
	public String getJobQueueName() {
		return jobQueueName;
	}	
	public void setJobQueueName(String jobQueueName) {
		this.jobQueueName = jobQueueName;
	}
	public Integer getNumReducers() {
		return numReducers;
	}
	public void setNumReducers(Integer numReducers) {
		this.numReducers = numReducers;
	}
	public String getProcessMarket() {
		return processMarket;
	}
	public void setProcessMarket(String processMarket) {
		this.processMarket = processMarket;
	}
	public String getInputDatabase() {
		return inputDatabase;
	}
	public void setInputDatabase(String inputDatabase) {
		this.inputDatabase = inputDatabase;
	}
	public String getSmartMergeDemoTable() {
		return smartMergeDemoTable;
	}
	public void setSmartMergeDemoTable(String smartMergeDemoTable) {
		this.smartMergeDemoTable = smartMergeDemoTable;
	}
	public String getSmartMergeFinancialTable() {
		return smartMergeFinancialTable;
	}
	public void setSmartMergeFinancialTable(String smartMergeFinancialTable) {
		this.smartMergeFinancialTable = smartMergeFinancialTable;
	}
	public String getOutputDatabase() {
		return outputDatabase;
	}
	public void setOutputDatabase(String outputDatabase) {
		this.outputDatabase = outputDatabase;
	}
	public String getOutputAccountTable() {
		return outputAccountTable;
	}
	public void setOutputAccountTable(String outputAccountTable) {
		this.outputAccountTable = outputAccountTable;
	}
	public String getOutputCustomerTable() {
		return outputCustomerTable;
	}
	public void setOutputCustomerTable(String outputCustomerTable) {
		this.outputCustomerTable = outputCustomerTable;
	}
}
