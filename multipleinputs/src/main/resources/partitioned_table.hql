set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
set hive.exec.max.dynamic.partitions.pernode=300;
set hive.exec.max.created.files=1500;

CREATE EXTERNAL TABLE cutmonth
(cut_mo_iso_curr string, 
cut_mo_cycle string, cut_mo_product string, cutdate_year_mo string, cut_mo_jdate string )
partitioned by (cut_mo_cutoff_grp String)
location '/idn/home/aiyenger/cutmonth';


insert into table cutmonth partition(cut_mo_cutoff_grp)
select cut_mo_iso_curr, cut_mo_cycle, cut_mo_product, cutdate_year_mo, cut_mo_jdate,cut_mo_cutoff_grp from rdredb.rdre_cutmonth;


show partitions cutmonth;
msck repair table cutmonth;



