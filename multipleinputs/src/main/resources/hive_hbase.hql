hbase shell
hbase(main):001:0> create 'cutmonth','cf1'
0 row(s) in 1.1790 seconds

CREATE EXTERNAL TABLE cutmonth_hbase (
cut_mo_iso_curr         string,                                      
cut_mo_cycle            string,                                      
cut_mo_product          string,                                      
cutdate_year_mo         string,                                      
cut_mo_jdate            string,                                      
cut_mo_cutoff_grp       string)
STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'
WITH SERDEPROPERTIES (
  "hbase.columns.mapping" =
  ":key,cf1:cut_mo_product,cf1:cut_mo_iso_curr,cf1:cutdate_year_mo,cf1:cut_mo_jdate,cf1:cut_mo_cutoff_grp"
)
TBLPROPERTIES("hbase.table.name" = "cutmonth");

insert into table cutmonth_hbase select * from cutmonth;