CREATE  TABLE `gstar_demo_bucketed`(
  `dr_cstone_feed_key` bigint, 
  `dr_cstone_last_updatetm` timestamp, 
  `dr_org` string, 
  `dr_region` string, 
  `dr_cus_id_seq_no` string, 
  `dr_cus_curr_cd` string, 
  `dr_cm15` string, 
  `dr_cm13` string, 
  `dr_logo` string, 
  `dr_act_prod_type_cd` string, 
  `dr_act_sub_prod_type_cd` string, 
  `dr_act_actv_canc_in` string, 
  `dr_rec_type` string, 
  `dr_cd_cust_nbr` string, 
  `dr_cm_opt_addr_dt` int, 
  `dr_cm_opt_zip_intl` string, 
  `dr_cm_opt_anniv_dt` int, 
  `dr_crd_new_ind` string, 
  `dr_cm_opt_canc_reas_intl` string, 
  `dr_canc_dt` int, 
  `dr_canc_tm` int, 
  `dr_cm_opt_coll_loc_intl` string, 
  `dr_loc_dt` int, 
  `dr_loc_tm` int, 
  `dr_cm_opt_rein_reas_intl` string, 
  `dr_reinstate_dt` int, 
  `dr_reinstate_tm` int, 
  `dr_acc_crlim` double, 
  `dr_cm_opt_acct_eff_dt` int, 
  `dr_acc_bill_cycle` string, 
  `dr_acc_date_last_cycle` int, 
  `dr_acc_bill_grp` string, 
  `dr_field_1` int, 
  `dr_field_2` int, 
  `dr_field_3` string, 
  `dr_field_4` string, 
  `dr_field_5` int, 
  `dr_field_6` int, 
  `dr_field_7` double, 
  `dr_field_8` double)
  clustered by(dr_cm13) into 10 buckets
LOCATION
  'maprfs:/idn/home/aiyenger/gstar_demo_bucketed';

  
insert into table gstar_demo_bucketed select * from gstar_demo;

