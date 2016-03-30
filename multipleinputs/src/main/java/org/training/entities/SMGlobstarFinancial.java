package org.training.entities;


import java.sql.Timestamp;


public class SMGlobstarFinancial {
	
	private Long dr_cstone_feed_key ;
	
	private Timestamp dr_cstone_last_updatetm ;
	
	private String dr_org ;
	
	private String dr_region ;
	
	private String dr_cus_id;
	
	private String dr_cus_curr_cd ;
	private String dr_cm15 ;
	
	private String dr_cm13 ;
	
	private String dr_logo ;
	
	private Double dr_opt_curr_due ;
	
	private String dr_rec_type ;
	
	private Double dr_opt_030_day ;
	
	private Double dr_opt_060_day ;
	
	private Double dr_opt_090_day ;
	
	private Double dr_opt_120_day ;
	
	private Double dr_opt_150_day ;
	
	private Double dr_ngck_rd_am ;
	
	private Integer dr_ngck_rd_ct ;
	
	private String dr_opt_dly_ngck_rd ;
	
	private Double dr_ngck_rn_am ;
	
	private Integer dr_ngck_rn_ct ;
	
	private String dr_opt_dly_ngck_rn ;
	
	private Double dr_opt_age_cr_am ;
	
	private Integer dr_opt_age_cr_ct ;
	
	private Double dr_opt_roc_am ;
	
	private Integer dr_opt_roc_ct ;
	
	private Double dr_opt_dly_chrg_db_am ;
	
	private Double dr_cm_opt_line_size_am ;
	
	private Double dr_opt_dly_cr_am ;
	
	private Integer dr_opt_dly_cr_ct ;
	
	private Double dr_opt_cyc_cr_am ;
	
	private Integer dr_opt_cyc_cr_ct ;
	
	private Double dr_opt_dly_db_am ;
	
	private Integer dr_opt_dly_db_ct ;
	
	private Double dr_opt_cyc_db_am ;
	
	private Integer dr_opt_cyc_db_ct ;
	
	private Integer dr_opt_bal_age ;  // Changed the type from Double to Integer
	
	private Double dr_opt_curr_min ;
	
	private Double dr_opt_min_pay ;
	
	private String dr_acc_acct_type_ind ;
	
	private Double dr_opt_fee_bal ;  //Missing
	
	private Double dr_opt_cash_bal ;  //Missing
	
	private Double dr_opt_dly_cash_db_am ; //Missing
	
	private Integer dr_opt_dly_cash_db_ct ; //Missing
	
	private Double dr_opt_cyc_cash_db_am ; //Missing
	
	private Integer dr_opt_cyc_cash_db_ct ; //Missing
	
	private Double dr_opt_dly_pay_am ; //Missing
	
	private Integer dr_opt_dly_pay_ct ; //Missing
	
	private Double dr_opt_cyc_pay_am ; //Missing
	
	private Integer dr_opt_cyc_pay_ct ; //Missing
	
	private Double dr_snt_cyc_cr ; //Missing
	
	private Integer dr_snt_cyc_cr_ct ; //Missing
	
	private Double dr_chrg_dly_curr_day; //Missing
	
	private Double dr_snt_cyc_db;//Missing
	
	private Integer dr_snt_cyc_db_ct;//Missing
	
	private Double dr_opt_tot_due; //Missing
	
	private Double dr_pay_due_am_pf;//Missing
	
	private Double dr_snt_total_due;//Missing
	
	private Double dr_opt_bal_subj_fin; //Missing
	
	private Double dr_opt_bal_n_subj_fin;//Missing
	
	private Double dr_opt_bal_open_fin; //Missing
	
	private Integer dr_rcp_dly_epp_plan_ct;//Missing
	
	private Integer dr_opt_dly_epp_plan_ct;//Missing
	
	private Integer dr_opt_epp_unbill_ct;//Added on Jan 29
	
	private Integer dr_opt_cyc_epp_unbill_ct;//Missing
	
	private Double dr_rcp_dly_epp_out_bal;//Missing
	
	private Double dr_opt_epp_out_bal;//Missing
	
	private Double dr_rcp_epp_out_bal;//Missing
	
	private Double dr_opt_cyc_epp_out_bal;//Missing
	
	private Double dr_opt_dly_epp_out_bal;//Missing
	
	private Double dr_rcp_dly_epp_mo_inst;//Missing
	
	private Double dr_opt_epp_mo_inst;//Missing
	
	private Double dr_rcp_epp_mo_inst;//Missing
	
	private Double dr_opt_cyc_epp_mo_inst;//Missing
	
	private Integer dr_rcp_dly_epp_rem_inst; //Missing
	
	private Integer dr_opt_dly_epp_rem_inst; //Missing
	
	private Integer dr_rcp_epp_rem_inst;//Missing
	
	private Integer dr_opt_cyc_epp_rem_inst; //Missing
	
	private Integer dr_rcp_dly_dpp_plan_ct; //Missing
	
	private Integer dr_opt_dpp_count; //Missing
	
	private Integer dr_rcp_dpp_count;
	
	private Integer dr_opt_cyc_dpp_count;
	
	private Double dr_rcp_dly_dpp_out_bal;
	
	private Double dr_opt_dpp_out_bal; //Missing
	
	private Double dr_rcp_dpp_out_bal;
	
	private Double dr_opt_cyc_dpp_out_bal;
	
	private Double dr_rcp_dly_dpp_mo_inst;
	
	private Double dr_opt_dpp_mo_inst;
	
	private Double dr_rcp_dpp_mo_inst;
	
	private Double dr_opt_cyc_dpp_mo_inst;
	
	private Integer dr_rcp_dly_dpp_rem_inst;
	
	private Integer dr_opt_dly_dpp_rem_inst;
	
	private Integer dr_rcp_dpp_rem_inst;
	
	private Integer dr_opt_cyc_dpp_rem_inst;
	
	private Integer dr_rcp_dly_dpp_ph_plan_ct;
	
	private Integer dr_opt_dpp_ph_plan_ct;
	
	private Integer dr_rcp_dpp_by_phone;
	
	private Integer dr_opt_cyc_dpp_ph_plan_ct;
	
	private Double dr_rcp_dly_dpp_ph_out_bal;
	
	private Double dr_opt_dpp_ph_out_bal;
	
	private Double dr_rcp_dpp_ph_out_bal;
	
	private Double dr_opt_cyc_dpp_ph_out_bal;
	
	private Double dr_rcp_dly_dpp_ph_mo_inst;
	
	private Double dr_opt_dpp_ph_mo_inst;
	
	private Double dr_rcp_dpp_ph_mo_inst;
	
	private Double dr_opt_cyc_dpp_ph_mo_inst;
	
	private Integer dr_rcp_dly_dpp_ph_rem_inst;
	
	private Integer dr_opt_dly_dpp_ph_rem_inst;
	
	private Integer dr_rcp_dpp_ph_rem_inst;
	
	private Integer dr_opt_cyc_dpp_ph_rem_inst;
	
	private Integer dr_rcp_dly_plan_n_plan_ct;
	
	private Integer dr_opt_plan_n_count;
	
	private Integer dr_rcp_plan_n_count;
	
	private Integer dr_opt_cyc_plan_n_count;
	
	private Double dr_rcp_dly_plan_n_out_bal;
	
	private Double dr_opt_plan_n_out_bal;
	
	private Double dr_rcp_plan_n_out_bal;
	
	private Double dr_opt_cyc_plan_n_out_bal;
	
	private Double dr_rcp_dly_plan_n_mo_inst;
	
	private Double dr_opt_plan_n_mo_inst;
	
	private Double dr_rcp_plan_n_mo_inst;
	
	private Double dr_opt_cyc_plan_n_mo_inst;
	
	private Integer dr_rcp_dly_plan_n_rem_inst;
	
	private Integer dr_opt_dly_plan_n_rem_inst;
	
	private Integer dr_rcp_plan_n_rem_inst;
	
	private Integer dr_opt_cyc_plan_n_rem_inst;
	
	private Integer dr_opt_day_age_old_date;
	
	private Double dr_snt_fin_due;
	
	private Double dr_snt_min_pay;
	
	private Double dr_snt_n_subj_fin_am;
	
	private Double dr_snt_dly_cr;
	
	private Integer dr_snt_dly_cr_ct;
	
	private Double dr_snt_dly_db;
	
	private Integer dr_snt_dly_db_ct;
	
	private Double dr_snt_dly_pay;
	
	private Integer dr_snt_dly_pay_ct;
	
	private Double dr_snt_bal_subj_fin;
	
	private Double dr_snt_bal_open_fin;
	
	private Double dr_snt_dly_ngck_nn;
	
	private Double dr_snt_dly_ngck_rn;
	
	private Double dr_snt_roc;
	
	private Integer dr_snt_roc_ct;
	
	private Integer ws_days_difference;
	
	private Long ws_start_cycle_date;
	
	private Integer dr_field_01;
	
	private Integer dr_field_02;
	
	private Integer dr_field_03;
	
	private String dr_field_04;
	
	private String dr_field_05;
	
	private String dr_field_06;
	
	private Long dr_field_07;
	
	private Long dr_field_08;
	
	private Long dr_field_09;
	
	private Double dr_field_10;
	
	private Double dr_field_11;
	
	private Double dr_field_12;

	public Long getDr_cstone_feed_key() {
		return dr_cstone_feed_key;
	}

	public void setDr_cstone_feed_key(Long dr_cstone_feed_key) {
		this.dr_cstone_feed_key = dr_cstone_feed_key;
	}

	public Timestamp getDr_cstone_last_updatetm() {
		return dr_cstone_last_updatetm;
	}

	public void setDr_cstone_last_updatetm(Timestamp dr_cstone_last_updatetm) {
		this.dr_cstone_last_updatetm = dr_cstone_last_updatetm;
	}

	public String getDr_org() {
		return dr_org;
	}

	public void setDr_org(String dr_org) {
		this.dr_org = dr_org;
	}

	public String getDr_region() {
		return dr_region;
	}

	public void setDr_region(String dr_region) {
		this.dr_region = dr_region;
	}

	public String getDr_cus_id() {
		return dr_cus_id;
	}

	public void setDr_cus_id(String dr_cus_id) {
		this.dr_cus_id = dr_cus_id;
	}

	public String getDr_cus_curr_cd() {
		return dr_cus_curr_cd;
	}

	public void setDr_cus_curr_cd(String dr_cus_curr_cd) {
		this.dr_cus_curr_cd = dr_cus_curr_cd;
	}

	public String getDr_cm15() {
		return dr_cm15;
	}

	public void setDr_cm15(String dr_cm15) {
		this.dr_cm15 = dr_cm15;
	}

	public String getDr_cm13() {
		return dr_cm13;
	}

	public void setDr_cm13(String dr_cm13) {
		this.dr_cm13 = dr_cm13;
	}

	public String getDr_logo() {
		return dr_logo;
	}

	public void setDr_logo(String dr_logo) {
		this.dr_logo = dr_logo;
	}

	public Double getDr_opt_curr_due() {
		return dr_opt_curr_due;
	}

	public void setDr_opt_curr_due(Double dr_opt_curr_due) {
		this.dr_opt_curr_due = dr_opt_curr_due;
	}

	public String getDr_rec_type() {
		return dr_rec_type;
	}

	public void setDr_rec_type(String dr_rec_type) {
		this.dr_rec_type = dr_rec_type;
	}

	public Double getDr_opt_030_day() {
		return dr_opt_030_day;
	}

	public void setDr_opt_030_day(Double dr_opt_030_day) {
		this.dr_opt_030_day = dr_opt_030_day;
	}

	public Double getDr_opt_060_day() {
		return dr_opt_060_day;
	}

	public void setDr_opt_060_day(Double dr_opt_060_day) {
		this.dr_opt_060_day = dr_opt_060_day;
	}

	public Double getDr_opt_090_day() {
		return dr_opt_090_day;
	}

	public void setDr_opt_090_day(Double dr_opt_090_day) {
		this.dr_opt_090_day = dr_opt_090_day;
	}

	public Double getDr_opt_120_day() {
		return dr_opt_120_day;
	}

	public void setDr_opt_120_day(Double dr_opt_120_day) {
		this.dr_opt_120_day = dr_opt_120_day;
	}

	public Double getDr_opt_150_day() {
		return dr_opt_150_day;
	}

	public void setDr_opt_150_day(Double dr_opt_150_day) {
		this.dr_opt_150_day = dr_opt_150_day;
	}

	public Double getDr_ngck_rd_am() {
		return dr_ngck_rd_am;
	}

	public void setDr_ngck_rd_am(Double dr_ngck_rd_am) {
		this.dr_ngck_rd_am = dr_ngck_rd_am;
	}

	public Integer getDr_ngck_rd_ct() {
		return dr_ngck_rd_ct;
	}

	public void setDr_ngck_rd_ct(Integer dr_ngck_rd_ct) {
		this.dr_ngck_rd_ct = dr_ngck_rd_ct;
	}

	public String getDr_opt_dly_ngck_rd() {
		return dr_opt_dly_ngck_rd;
	}

	public void setDr_opt_dly_ngck_rd(String dr_opt_dly_ngck_rd) {
		this.dr_opt_dly_ngck_rd = dr_opt_dly_ngck_rd;
	}

	public Double getDr_ngck_rn_am() {
		return dr_ngck_rn_am;
	}

	public void setDr_ngck_rn_am(Double dr_ngck_rn_am) {
		this.dr_ngck_rn_am = dr_ngck_rn_am;
	}

	public Integer getDr_ngck_rn_ct() {
		return dr_ngck_rn_ct;
	}

	public void setDr_ngck_rn_ct(Integer dr_ngck_rn_ct) {
		this.dr_ngck_rn_ct = dr_ngck_rn_ct;
	}

	public String getDr_opt_dly_ngck_rn() {
		return dr_opt_dly_ngck_rn;
	}

	public void setDr_opt_dly_ngck_rn(String dr_opt_dly_ngck_rn) {
		this.dr_opt_dly_ngck_rn = dr_opt_dly_ngck_rn;
	}

	public Double getDr_opt_age_cr_am() {
		return dr_opt_age_cr_am;
	}

	public void setDr_opt_age_cr_am(Double dr_opt_age_cr_am) {
		this.dr_opt_age_cr_am = dr_opt_age_cr_am;
	}

	public Integer getDr_opt_age_cr_ct() {
		return dr_opt_age_cr_ct;
	}

	public void setDr_opt_age_cr_ct(Integer dr_opt_age_cr_ct) {
		this.dr_opt_age_cr_ct = dr_opt_age_cr_ct;
	}

	public Double getDr_opt_roc_am() {
		return dr_opt_roc_am;
	}

	public void setDr_opt_roc_am(Double dr_opt_roc_am) {
		this.dr_opt_roc_am = dr_opt_roc_am;
	}

	public Integer getDr_opt_roc_ct() {
		return dr_opt_roc_ct;
	}

	public void setDr_opt_roc_ct(Integer dr_opt_roc_ct) {
		this.dr_opt_roc_ct = dr_opt_roc_ct;
	}

	public Double getDr_opt_dly_chrg_db_am() {
		return dr_opt_dly_chrg_db_am;
	}

	public void setDr_opt_dly_chrg_db_am(Double dr_opt_dly_chrg_db_am) {
		this.dr_opt_dly_chrg_db_am = dr_opt_dly_chrg_db_am;
	}

	public Double getDr_cm_opt_line_size_am() {
		return dr_cm_opt_line_size_am;
	}

	public void setDr_cm_opt_line_size_am(Double dr_cm_opt_line_size_am) {
		this.dr_cm_opt_line_size_am = dr_cm_opt_line_size_am;
	}

	public Double getDr_opt_dly_cr_am() {
		return dr_opt_dly_cr_am;
	}

	public void setDr_opt_dly_cr_am(Double dr_opt_dly_cr_am) {
		this.dr_opt_dly_cr_am = dr_opt_dly_cr_am;
	}

	public Integer getDr_opt_dly_cr_ct() {
		return dr_opt_dly_cr_ct;
	}

	public void setDr_opt_dly_cr_ct(Integer dr_opt_dly_cr_ct) {
		this.dr_opt_dly_cr_ct = dr_opt_dly_cr_ct;
	}

	public Double getDr_opt_cyc_cr_am() {
		return dr_opt_cyc_cr_am;
	}

	public void setDr_opt_cyc_cr_am(Double dr_opt_cyc_cr_am) {
		this.dr_opt_cyc_cr_am = dr_opt_cyc_cr_am;
	}

	public Integer getDr_opt_cyc_cr_ct() {
		return dr_opt_cyc_cr_ct;
	}

	public void setDr_opt_cyc_cr_ct(Integer dr_opt_cyc_cr_ct) {
		this.dr_opt_cyc_cr_ct = dr_opt_cyc_cr_ct;
	}

	public Double getDr_opt_dly_db_am() {
		return dr_opt_dly_db_am;
	}

	public void setDr_opt_dly_db_am(Double dr_opt_dly_db_am) {
		this.dr_opt_dly_db_am = dr_opt_dly_db_am;
	}

	public Integer getDr_opt_dly_db_ct() {
		return dr_opt_dly_db_ct;
	}

	public void setDr_opt_dly_db_ct(Integer dr_opt_dly_db_ct) {
		this.dr_opt_dly_db_ct = dr_opt_dly_db_ct;
	}

	public Double getDr_opt_cyc_db_am() {
		return dr_opt_cyc_db_am;
	}

	public void setDr_opt_cyc_db_am(Double dr_opt_cyc_db_am) {
		this.dr_opt_cyc_db_am = dr_opt_cyc_db_am;
	}

	public Integer getDr_opt_cyc_db_ct() {
		return dr_opt_cyc_db_ct;
	}

	public void setDr_opt_cyc_db_ct(Integer dr_opt_cyc_db_ct) {
		this.dr_opt_cyc_db_ct = dr_opt_cyc_db_ct;
	}

	public Integer getDr_opt_bal_age() {
		return dr_opt_bal_age;
	}

	public void setDr_opt_bal_age(Integer dr_opt_bal_age) {
		this.dr_opt_bal_age = dr_opt_bal_age;
	}

	public Double getDr_opt_curr_min() {
		return dr_opt_curr_min;
	}

	public void setDr_opt_curr_min(Double dr_opt_curr_min) {
		this.dr_opt_curr_min = dr_opt_curr_min;
	}

	public Double getDr_opt_min_pay() {
		return dr_opt_min_pay;
	}

	public void setDr_opt_min_pay(Double dr_opt_min_pay) {
		this.dr_opt_min_pay = dr_opt_min_pay;
	}

	public String getDr_acc_acct_type_ind() {
		return dr_acc_acct_type_ind;
	}

	public void setDr_acc_acct_type_ind(String dr_acc_acct_type_ind) {
		this.dr_acc_acct_type_ind = dr_acc_acct_type_ind;
	}

	public Double getDr_opt_fee_bal() {
		return dr_opt_fee_bal;
	}

	public void setDr_opt_fee_bal(Double dr_opt_fee_bal) {
		this.dr_opt_fee_bal = dr_opt_fee_bal;
	}

	public Double getDr_opt_cash_bal() {
		return dr_opt_cash_bal;
	}

	public void setDr_opt_cash_bal(Double dr_opt_cash_bal) {
		this.dr_opt_cash_bal = dr_opt_cash_bal;
	}

	public Double getDr_opt_dly_cash_db_am() {
		return dr_opt_dly_cash_db_am;
	}

	public void setDr_opt_dly_cash_db_am(Double dr_opt_dly_cash_db_am) {
		this.dr_opt_dly_cash_db_am = dr_opt_dly_cash_db_am;
	}

	public Integer getDr_opt_dly_cash_db_ct() {
		return dr_opt_dly_cash_db_ct;
	}

	public void setDr_opt_dly_cash_db_ct(Integer dr_opt_dly_cash_db_ct) {
		this.dr_opt_dly_cash_db_ct = dr_opt_dly_cash_db_ct;
	}

	public Double getDr_opt_cyc_cash_db_am() {
		return dr_opt_cyc_cash_db_am;
	}

	public void setDr_opt_cyc_cash_db_am(Double dr_opt_cyc_cash_db_am) {
		this.dr_opt_cyc_cash_db_am = dr_opt_cyc_cash_db_am;
	}

	public Integer getDr_opt_cyc_cash_db_ct() {
		return dr_opt_cyc_cash_db_ct;
	}

	public void setDr_opt_cyc_cash_db_ct(Integer dr_opt_cyc_cash_db_ct) {
		this.dr_opt_cyc_cash_db_ct = dr_opt_cyc_cash_db_ct;
	}

	public Double getDr_opt_dly_pay_am() {
		return dr_opt_dly_pay_am;
	}

	public void setDr_opt_dly_pay_am(Double dr_opt_dly_pay_am) {
		this.dr_opt_dly_pay_am = dr_opt_dly_pay_am;
	}

	public Integer getDr_opt_dly_pay_ct() {
		return dr_opt_dly_pay_ct;
	}

	public void setDr_opt_dly_pay_ct(Integer dr_opt_dly_pay_ct) {
		this.dr_opt_dly_pay_ct = dr_opt_dly_pay_ct;
	}

	public Double getDr_opt_cyc_pay_am() {
		return dr_opt_cyc_pay_am;
	}

	public void setDr_opt_cyc_pay_am(Double dr_opt_cyc_pay_am) {
		this.dr_opt_cyc_pay_am = dr_opt_cyc_pay_am;
	}

	public Integer getDr_opt_cyc_pay_ct() {
		return dr_opt_cyc_pay_ct;
	}

	public void setDr_opt_cyc_pay_ct(Integer dr_opt_cyc_pay_ct) {
		this.dr_opt_cyc_pay_ct = dr_opt_cyc_pay_ct;
	}

	public Double getDr_snt_cyc_cr() {
		return dr_snt_cyc_cr;
	}

	public void setDr_snt_cyc_cr(Double dr_snt_cyc_cr) {
		this.dr_snt_cyc_cr = dr_snt_cyc_cr;
	}

	public Integer getDr_snt_cyc_cr_ct() {
		return dr_snt_cyc_cr_ct;
	}

	public void setDr_snt_cyc_cr_ct(Integer dr_snt_cyc_cr_ct) {
		this.dr_snt_cyc_cr_ct = dr_snt_cyc_cr_ct;
	}

	public Double getDr_chrg_dly_curr_day() {
		return dr_chrg_dly_curr_day;
	}

	public void setDr_chrg_dly_curr_day(Double dr_chrg_dly_curr_day) {
		this.dr_chrg_dly_curr_day = dr_chrg_dly_curr_day;
	}

	public Double getDr_snt_cyc_db() {
		return dr_snt_cyc_db;
	}

	public void setDr_snt_cyc_db(Double dr_snt_cyc_db) {
		this.dr_snt_cyc_db = dr_snt_cyc_db;
	}

	public Integer getDr_snt_cyc_db_ct() {
		return dr_snt_cyc_db_ct;
	}

	public void setDr_snt_cyc_db_ct(Integer dr_snt_cyc_db_ct) {
		this.dr_snt_cyc_db_ct = dr_snt_cyc_db_ct;
	}

	public Double getDr_opt_tot_due() {
		return dr_opt_tot_due;
	}

	public void setDr_opt_tot_due(Double dr_opt_tot_due) {
		this.dr_opt_tot_due = dr_opt_tot_due;
	}

	public Double getDr_pay_due_am_pf() {
		return dr_pay_due_am_pf;
	}

	public void setDr_pay_due_am_pf(Double dr_pay_due_am_pf) {
		this.dr_pay_due_am_pf = dr_pay_due_am_pf;
	}

	public Double getDr_snt_total_due() {
		return dr_snt_total_due;
	}

	public void setDr_snt_total_due(Double dr_snt_total_due) {
		this.dr_snt_total_due = dr_snt_total_due;
	}

	public Double getDr_opt_bal_subj_fin() {
		return dr_opt_bal_subj_fin;
	}

	public void setDr_opt_bal_subj_fin(Double dr_opt_bal_subj_fin) {
		this.dr_opt_bal_subj_fin = dr_opt_bal_subj_fin;
	}

	public Double getDr_opt_bal_n_subj_fin() {
		return dr_opt_bal_n_subj_fin;
	}

	public void setDr_opt_bal_n_subj_fin(Double dr_opt_bal_n_subj_fin) {
		this.dr_opt_bal_n_subj_fin = dr_opt_bal_n_subj_fin;
	}

	public Double getDr_opt_bal_open_fin() {
		return dr_opt_bal_open_fin;
	}

	public void setDr_opt_bal_open_fin(Double dr_opt_bal_open_fin) {
		this.dr_opt_bal_open_fin = dr_opt_bal_open_fin;
	}

	public Integer getDr_rcp_dly_epp_plan_ct() {
		return dr_rcp_dly_epp_plan_ct;
	}

	public void setDr_rcp_dly_epp_plan_ct(Integer dr_rcp_dly_epp_plan_ct) {
		this.dr_rcp_dly_epp_plan_ct = dr_rcp_dly_epp_plan_ct;
	}

	public Integer getDr_opt_dly_epp_plan_ct() {
		return dr_opt_dly_epp_plan_ct;
	}

	public void setDr_opt_dly_epp_plan_ct(Integer dr_opt_dly_epp_plan_ct) {
		this.dr_opt_dly_epp_plan_ct = dr_opt_dly_epp_plan_ct;
	}

	public Integer getDr_opt_epp_unbill_ct() {
		return dr_opt_epp_unbill_ct;
	}

	public void setDr_opt_epp_unbill_ct(Integer dr_opt_epp_unbill_ct) {
		this.dr_opt_epp_unbill_ct = dr_opt_epp_unbill_ct;
	}

	public Integer getDr_opt_cyc_epp_unbill_ct() {
		return dr_opt_cyc_epp_unbill_ct;
	}

	public void setDr_opt_cyc_epp_unbill_ct(Integer dr_opt_cyc_epp_unbill_ct) {
		this.dr_opt_cyc_epp_unbill_ct = dr_opt_cyc_epp_unbill_ct;
	}

	public Double getDr_rcp_dly_epp_out_bal() {
		return dr_rcp_dly_epp_out_bal;
	}

	public void setDr_rcp_dly_epp_out_bal(Double dr_rcp_dly_epp_out_bal) {
		this.dr_rcp_dly_epp_out_bal = dr_rcp_dly_epp_out_bal;
	}

	public Double getDr_opt_epp_out_bal() {
		return dr_opt_epp_out_bal;
	}

	public void setDr_opt_epp_out_bal(Double dr_opt_epp_out_bal) {
		this.dr_opt_epp_out_bal = dr_opt_epp_out_bal;
	}

	public Double getDr_rcp_epp_out_bal() {
		return dr_rcp_epp_out_bal;
	}

	public void setDr_rcp_epp_out_bal(Double dr_rcp_epp_out_bal) {
		this.dr_rcp_epp_out_bal = dr_rcp_epp_out_bal;
	}

	public Double getDr_opt_cyc_epp_out_bal() {
		return dr_opt_cyc_epp_out_bal;
	}

	public void setDr_opt_cyc_epp_out_bal(Double dr_opt_cyc_epp_out_bal) {
		this.dr_opt_cyc_epp_out_bal = dr_opt_cyc_epp_out_bal;
	}

	public Double getDr_opt_dly_epp_out_bal() {
		return dr_opt_dly_epp_out_bal;
	}

	public void setDr_opt_dly_epp_out_bal(Double dr_opt_dly_epp_out_bal) {
		this.dr_opt_dly_epp_out_bal = dr_opt_dly_epp_out_bal;
	}

	public Double getDr_rcp_dly_epp_mo_inst() {
		return dr_rcp_dly_epp_mo_inst;
	}

	public void setDr_rcp_dly_epp_mo_inst(Double dr_rcp_dly_epp_mo_inst) {
		this.dr_rcp_dly_epp_mo_inst = dr_rcp_dly_epp_mo_inst;
	}

	public Double getDr_opt_epp_mo_inst() {
		return dr_opt_epp_mo_inst;
	}

	public void setDr_opt_epp_mo_inst(Double dr_opt_epp_mo_inst) {
		this.dr_opt_epp_mo_inst = dr_opt_epp_mo_inst;
	}

	public Double getDr_rcp_epp_mo_inst() {
		return dr_rcp_epp_mo_inst;
	}

	public void setDr_rcp_epp_mo_inst(Double dr_rcp_epp_mo_inst) {
		this.dr_rcp_epp_mo_inst = dr_rcp_epp_mo_inst;
	}

	public Double getDr_opt_cyc_epp_mo_inst() {
		return dr_opt_cyc_epp_mo_inst;
	}

	public void setDr_opt_cyc_epp_mo_inst(Double dr_opt_cyc_epp_mo_inst) {
		this.dr_opt_cyc_epp_mo_inst = dr_opt_cyc_epp_mo_inst;
	}

	public Integer getDr_rcp_dly_epp_rem_inst() {
		return dr_rcp_dly_epp_rem_inst;
	}

	public void setDr_rcp_dly_epp_rem_inst(Integer dr_rcp_dly_epp_rem_inst) {
		this.dr_rcp_dly_epp_rem_inst = dr_rcp_dly_epp_rem_inst;
	}

	public Integer getDr_opt_dly_epp_rem_inst() {
		return dr_opt_dly_epp_rem_inst;
	}

	public void setDr_opt_dly_epp_rem_inst(Integer dr_opt_dly_epp_rem_inst) {
		this.dr_opt_dly_epp_rem_inst = dr_opt_dly_epp_rem_inst;
	}

	public Integer getDr_rcp_epp_rem_inst() {
		return dr_rcp_epp_rem_inst;
	}

	public void setDr_rcp_epp_rem_inst(Integer dr_rcp_epp_rem_inst) {
		this.dr_rcp_epp_rem_inst = dr_rcp_epp_rem_inst;
	}

	public Integer getDr_opt_cyc_epp_rem_inst() {
		return dr_opt_cyc_epp_rem_inst;
	}

	public void setDr_opt_cyc_epp_rem_inst(Integer dr_opt_cyc_epp_rem_inst) {
		this.dr_opt_cyc_epp_rem_inst = dr_opt_cyc_epp_rem_inst;
	}

	public Integer getDr_rcp_dly_dpp_plan_ct() {
		return dr_rcp_dly_dpp_plan_ct;
	}

	public void setDr_rcp_dly_dpp_plan_ct(Integer dr_rcp_dly_dpp_plan_ct) {
		this.dr_rcp_dly_dpp_plan_ct = dr_rcp_dly_dpp_plan_ct;
	}

	public Integer getDr_opt_dpp_count() {
		return dr_opt_dpp_count;
	}

	public void setDr_opt_dpp_count(Integer dr_opt_dpp_count) {
		this.dr_opt_dpp_count = dr_opt_dpp_count;
	}

	public Integer getDr_rcp_dpp_count() {
		return dr_rcp_dpp_count;
	}

	public void setDr_rcp_dpp_count(Integer dr_rcp_dpp_count) {
		this.dr_rcp_dpp_count = dr_rcp_dpp_count;
	}

	public Integer getDr_opt_cyc_dpp_count() {
		return dr_opt_cyc_dpp_count;
	}

	public void setDr_opt_cyc_dpp_count(Integer dr_opt_cyc_dpp_count) {
		this.dr_opt_cyc_dpp_count = dr_opt_cyc_dpp_count;
	}

	public Double getDr_rcp_dly_dpp_out_bal() {
		return dr_rcp_dly_dpp_out_bal;
	}

	public void setDr_rcp_dly_dpp_out_bal(Double dr_rcp_dly_dpp_out_bal) {
		this.dr_rcp_dly_dpp_out_bal = dr_rcp_dly_dpp_out_bal;
	}

	public Double getDr_opt_dpp_out_bal() {
		return dr_opt_dpp_out_bal;
	}

	public void setDr_opt_dpp_out_bal(Double dr_opt_dpp_out_bal) {
		this.dr_opt_dpp_out_bal = dr_opt_dpp_out_bal;
	}

	public Double getDr_rcp_dpp_out_bal() {
		return dr_rcp_dpp_out_bal;
	}

	public void setDr_rcp_dpp_out_bal(Double dr_rcp_dpp_out_bal) {
		this.dr_rcp_dpp_out_bal = dr_rcp_dpp_out_bal;
	}

	public Double getDr_opt_cyc_dpp_out_bal() {
		return dr_opt_cyc_dpp_out_bal;
	}

	public void setDr_opt_cyc_dpp_out_bal(Double dr_opt_cyc_dpp_out_bal) {
		this.dr_opt_cyc_dpp_out_bal = dr_opt_cyc_dpp_out_bal;
	}

	public Double getDr_rcp_dly_dpp_mo_inst() {
		return dr_rcp_dly_dpp_mo_inst;
	}

	public void setDr_rcp_dly_dpp_mo_inst(Double dr_rcp_dly_dpp_mo_inst) {
		this.dr_rcp_dly_dpp_mo_inst = dr_rcp_dly_dpp_mo_inst;
	}

	public Double getDr_opt_dpp_mo_inst() {
		return dr_opt_dpp_mo_inst;
	}

	public void setDr_opt_dpp_mo_inst(Double dr_opt_dpp_mo_inst) {
		this.dr_opt_dpp_mo_inst = dr_opt_dpp_mo_inst;
	}

	public Double getDr_rcp_dpp_mo_inst() {
		return dr_rcp_dpp_mo_inst;
	}

	public void setDr_rcp_dpp_mo_inst(Double dr_rcp_dpp_mo_inst) {
		this.dr_rcp_dpp_mo_inst = dr_rcp_dpp_mo_inst;
	}

	public Double getDr_opt_cyc_dpp_mo_inst() {
		return dr_opt_cyc_dpp_mo_inst;
	}

	public void setDr_opt_cyc_dpp_mo_inst(Double dr_opt_cyc_dpp_mo_inst) {
		this.dr_opt_cyc_dpp_mo_inst = dr_opt_cyc_dpp_mo_inst;
	}

	public Integer getDr_rcp_dly_dpp_rem_inst() {
		return dr_rcp_dly_dpp_rem_inst;
	}

	public void setDr_rcp_dly_dpp_rem_inst(Integer dr_rcp_dly_dpp_rem_inst) {
		this.dr_rcp_dly_dpp_rem_inst = dr_rcp_dly_dpp_rem_inst;
	}

	public Integer getDr_opt_dly_dpp_rem_inst() {
		return dr_opt_dly_dpp_rem_inst;
	}

	public void setDr_opt_dly_dpp_rem_inst(Integer dr_opt_dly_dpp_rem_inst) {
		this.dr_opt_dly_dpp_rem_inst = dr_opt_dly_dpp_rem_inst;
	}

	public Integer getDr_rcp_dpp_rem_inst() {
		return dr_rcp_dpp_rem_inst;
	}

	public void setDr_rcp_dpp_rem_inst(Integer dr_rcp_dpp_rem_inst) {
		this.dr_rcp_dpp_rem_inst = dr_rcp_dpp_rem_inst;
	}

	public Integer getDr_opt_cyc_dpp_rem_inst() {
		return dr_opt_cyc_dpp_rem_inst;
	}

	public void setDr_opt_cyc_dpp_rem_inst(Integer dr_opt_cyc_dpp_rem_inst) {
		this.dr_opt_cyc_dpp_rem_inst = dr_opt_cyc_dpp_rem_inst;
	}

	public Integer getDr_rcp_dly_dpp_ph_plan_ct() {
		return dr_rcp_dly_dpp_ph_plan_ct;
	}

	public void setDr_rcp_dly_dpp_ph_plan_ct(Integer dr_rcp_dly_dpp_ph_plan_ct) {
		this.dr_rcp_dly_dpp_ph_plan_ct = dr_rcp_dly_dpp_ph_plan_ct;
	}

	public Integer getDr_opt_dpp_ph_plan_ct() {
		return dr_opt_dpp_ph_plan_ct;
	}

	public void setDr_opt_dpp_ph_plan_ct(Integer dr_opt_dpp_ph_plan_ct) {
		this.dr_opt_dpp_ph_plan_ct = dr_opt_dpp_ph_plan_ct;
	}

	public Integer getDr_rcp_dpp_by_phone() {
		return dr_rcp_dpp_by_phone;
	}

	public void setDr_rcp_dpp_by_phone(Integer dr_rcp_dpp_by_phone) {
		this.dr_rcp_dpp_by_phone = dr_rcp_dpp_by_phone;
	}

	public Integer getDr_opt_cyc_dpp_ph_plan_ct() {
		return dr_opt_cyc_dpp_ph_plan_ct;
	}

	public void setDr_opt_cyc_dpp_ph_plan_ct(Integer dr_opt_cyc_dpp_ph_plan_ct) {
		this.dr_opt_cyc_dpp_ph_plan_ct = dr_opt_cyc_dpp_ph_plan_ct;
	}

	public Double getDr_rcp_dly_dpp_ph_out_bal() {
		return dr_rcp_dly_dpp_ph_out_bal;
	}

	public void setDr_rcp_dly_dpp_ph_out_bal(Double dr_rcp_dly_dpp_ph_out_bal) {
		this.dr_rcp_dly_dpp_ph_out_bal = dr_rcp_dly_dpp_ph_out_bal;
	}

	public Double getDr_opt_dpp_ph_out_bal() {
		return dr_opt_dpp_ph_out_bal;
	}

	public void setDr_opt_dpp_ph_out_bal(Double dr_opt_dpp_ph_out_bal) {
		this.dr_opt_dpp_ph_out_bal = dr_opt_dpp_ph_out_bal;
	}

	public Double getDr_rcp_dpp_ph_out_bal() {
		return dr_rcp_dpp_ph_out_bal;
	}

	public void setDr_rcp_dpp_ph_out_bal(Double dr_rcp_dpp_ph_out_bal) {
		this.dr_rcp_dpp_ph_out_bal = dr_rcp_dpp_ph_out_bal;
	}

	public Double getDr_opt_cyc_dpp_ph_out_bal() {
		return dr_opt_cyc_dpp_ph_out_bal;
	}

	public void setDr_opt_cyc_dpp_ph_out_bal(Double dr_opt_cyc_dpp_ph_out_bal) {
		this.dr_opt_cyc_dpp_ph_out_bal = dr_opt_cyc_dpp_ph_out_bal;
	}

	public Double getDr_rcp_dly_dpp_ph_mo_inst() {
		return dr_rcp_dly_dpp_ph_mo_inst;
	}

	public void setDr_rcp_dly_dpp_ph_mo_inst(Double dr_rcp_dly_dpp_ph_mo_inst) {
		this.dr_rcp_dly_dpp_ph_mo_inst = dr_rcp_dly_dpp_ph_mo_inst;
	}

	public Double getDr_opt_dpp_ph_mo_inst() {
		return dr_opt_dpp_ph_mo_inst;
	}

	public void setDr_opt_dpp_ph_mo_inst(Double dr_opt_dpp_ph_mo_inst) {
		this.dr_opt_dpp_ph_mo_inst = dr_opt_dpp_ph_mo_inst;
	}

	public Double getDr_rcp_dpp_ph_mo_inst() {
		return dr_rcp_dpp_ph_mo_inst;
	}

	public void setDr_rcp_dpp_ph_mo_inst(Double dr_rcp_dpp_ph_mo_inst) {
		this.dr_rcp_dpp_ph_mo_inst = dr_rcp_dpp_ph_mo_inst;
	}

	public Double getDr_opt_cyc_dpp_ph_mo_inst() {
		return dr_opt_cyc_dpp_ph_mo_inst;
	}

	public void setDr_opt_cyc_dpp_ph_mo_inst(Double dr_opt_cyc_dpp_ph_mo_inst) {
		this.dr_opt_cyc_dpp_ph_mo_inst = dr_opt_cyc_dpp_ph_mo_inst;
	}

	public Integer getDr_rcp_dly_dpp_ph_rem_inst() {
		return dr_rcp_dly_dpp_ph_rem_inst;
	}

	public void setDr_rcp_dly_dpp_ph_rem_inst(Integer dr_rcp_dly_dpp_ph_rem_inst) {
		this.dr_rcp_dly_dpp_ph_rem_inst = dr_rcp_dly_dpp_ph_rem_inst;
	}

	public Integer getDr_opt_dly_dpp_ph_rem_inst() {
		return dr_opt_dly_dpp_ph_rem_inst;
	}

	public void setDr_opt_dly_dpp_ph_rem_inst(Integer dr_opt_dly_dpp_ph_rem_inst) {
		this.dr_opt_dly_dpp_ph_rem_inst = dr_opt_dly_dpp_ph_rem_inst;
	}

	public Integer getDr_rcp_dpp_ph_rem_inst() {
		return dr_rcp_dpp_ph_rem_inst;
	}

	public void setDr_rcp_dpp_ph_rem_inst(Integer dr_rcp_dpp_ph_rem_inst) {
		this.dr_rcp_dpp_ph_rem_inst = dr_rcp_dpp_ph_rem_inst;
	}

	public Integer getDr_opt_cyc_dpp_ph_rem_inst() {
		return dr_opt_cyc_dpp_ph_rem_inst;
	}

	public void setDr_opt_cyc_dpp_ph_rem_inst(Integer dr_opt_cyc_dpp_ph_rem_inst) {
		this.dr_opt_cyc_dpp_ph_rem_inst = dr_opt_cyc_dpp_ph_rem_inst;
	}

	public Integer getDr_rcp_dly_plan_n_plan_ct() {
		return dr_rcp_dly_plan_n_plan_ct;
	}

	public void setDr_rcp_dly_plan_n_plan_ct(Integer dr_rcp_dly_plan_n_plan_ct) {
		this.dr_rcp_dly_plan_n_plan_ct = dr_rcp_dly_plan_n_plan_ct;
	}

	public Integer getDr_opt_plan_n_count() {
		return dr_opt_plan_n_count;
	}

	public void setDr_opt_plan_n_count(Integer dr_opt_plan_n_count) {
		this.dr_opt_plan_n_count = dr_opt_plan_n_count;
	}

	public Integer getDr_rcp_plan_n_count() {
		return dr_rcp_plan_n_count;
	}

	public void setDr_rcp_plan_n_count(Integer dr_rcp_plan_n_count) {
		this.dr_rcp_plan_n_count = dr_rcp_plan_n_count;
	}

	public Integer getDr_opt_cyc_plan_n_count() {
		return dr_opt_cyc_plan_n_count;
	}

	public void setDr_opt_cyc_plan_n_count(Integer dr_opt_cyc_plan_n_count) {
		this.dr_opt_cyc_plan_n_count = dr_opt_cyc_plan_n_count;
	}

	public Double getDr_rcp_dly_plan_n_out_bal() {
		return dr_rcp_dly_plan_n_out_bal;
	}

	public void setDr_rcp_dly_plan_n_out_bal(Double dr_rcp_dly_plan_n_out_bal) {
		this.dr_rcp_dly_plan_n_out_bal = dr_rcp_dly_plan_n_out_bal;
	}

	public Double getDr_opt_plan_n_out_bal() {
		return dr_opt_plan_n_out_bal;
	}

	public void setDr_opt_plan_n_out_bal(Double dr_opt_plan_n_out_bal) {
		this.dr_opt_plan_n_out_bal = dr_opt_plan_n_out_bal;
	}

	public Double getDr_rcp_plan_n_out_bal() {
		return dr_rcp_plan_n_out_bal;
	}

	public void setDr_rcp_plan_n_out_bal(Double dr_rcp_plan_n_out_bal) {
		this.dr_rcp_plan_n_out_bal = dr_rcp_plan_n_out_bal;
	}

	public Double getDr_opt_cyc_plan_n_out_bal() {
		return dr_opt_cyc_plan_n_out_bal;
	}

	public void setDr_opt_cyc_plan_n_out_bal(Double dr_opt_cyc_plan_n_out_bal) {
		this.dr_opt_cyc_plan_n_out_bal = dr_opt_cyc_plan_n_out_bal;
	}

	public Double getDr_rcp_dly_plan_n_mo_inst() {
		return dr_rcp_dly_plan_n_mo_inst;
	}

	public void setDr_rcp_dly_plan_n_mo_inst(Double dr_rcp_dly_plan_n_mo_inst) {
		this.dr_rcp_dly_plan_n_mo_inst = dr_rcp_dly_plan_n_mo_inst;
	}

	public Double getDr_opt_plan_n_mo_inst() {
		return dr_opt_plan_n_mo_inst;
	}

	public void setDr_opt_plan_n_mo_inst(Double dr_opt_plan_n_mo_inst) {
		this.dr_opt_plan_n_mo_inst = dr_opt_plan_n_mo_inst;
	}

	public Double getDr_rcp_plan_n_mo_inst() {
		return dr_rcp_plan_n_mo_inst;
	}

	public void setDr_rcp_plan_n_mo_inst(Double dr_rcp_plan_n_mo_inst) {
		this.dr_rcp_plan_n_mo_inst = dr_rcp_plan_n_mo_inst;
	}

	public Double getDr_opt_cyc_plan_n_mo_inst() {
		return dr_opt_cyc_plan_n_mo_inst;
	}

	public void setDr_opt_cyc_plan_n_mo_inst(Double dr_opt_cyc_plan_n_mo_inst) {
		this.dr_opt_cyc_plan_n_mo_inst = dr_opt_cyc_plan_n_mo_inst;
	}

	public Integer getDr_rcp_dly_plan_n_rem_inst() {
		return dr_rcp_dly_plan_n_rem_inst;
	}

	public void setDr_rcp_dly_plan_n_rem_inst(Integer dr_rcp_dly_plan_n_rem_inst) {
		this.dr_rcp_dly_plan_n_rem_inst = dr_rcp_dly_plan_n_rem_inst;
	}

	public Integer getDr_opt_dly_plan_n_rem_inst() {
		return dr_opt_dly_plan_n_rem_inst;
	}

	public void setDr_opt_dly_plan_n_rem_inst(Integer dr_opt_dly_plan_n_rem_inst) {
		this.dr_opt_dly_plan_n_rem_inst = dr_opt_dly_plan_n_rem_inst;
	}

	public Integer getDr_rcp_plan_n_rem_inst() {
		return dr_rcp_plan_n_rem_inst;
	}

	public void setDr_rcp_plan_n_rem_inst(Integer dr_rcp_plan_n_rem_inst) {
		this.dr_rcp_plan_n_rem_inst = dr_rcp_plan_n_rem_inst;
	}

	public Integer getDr_opt_cyc_plan_n_rem_inst() {
		return dr_opt_cyc_plan_n_rem_inst;
	}

	public void setDr_opt_cyc_plan_n_rem_inst(Integer dr_opt_cyc_plan_n_rem_inst) {
		this.dr_opt_cyc_plan_n_rem_inst = dr_opt_cyc_plan_n_rem_inst;
	}

	public Integer getDr_opt_day_age_old_date() {
		return dr_opt_day_age_old_date;
	}

	public void setDr_opt_day_age_old_date(Integer dr_opt_day_age_old_date) {
		this.dr_opt_day_age_old_date = dr_opt_day_age_old_date;
	}

	public Double getDr_snt_fin_due() {
		return dr_snt_fin_due;
	}

	public void setDr_snt_fin_due(Double dr_snt_fin_due) {
		this.dr_snt_fin_due = dr_snt_fin_due;
	}

	public Double getDr_snt_min_pay() {
		return dr_snt_min_pay;
	}

	public void setDr_snt_min_pay(Double dr_snt_min_pay) {
		this.dr_snt_min_pay = dr_snt_min_pay;
	}

	public Double getDr_snt_n_subj_fin_am() {
		return dr_snt_n_subj_fin_am;
	}

	public void setDr_snt_n_subj_fin_am(Double dr_snt_n_subj_fin_am) {
		this.dr_snt_n_subj_fin_am = dr_snt_n_subj_fin_am;
	}

	public Double getDr_snt_dly_cr() {
		return dr_snt_dly_cr;
	}

	public void setDr_snt_dly_cr(Double dr_snt_dly_cr) {
		this.dr_snt_dly_cr = dr_snt_dly_cr;
	}

	public Integer getDr_snt_dly_cr_ct() {
		return dr_snt_dly_cr_ct;
	}

	public void setDr_snt_dly_cr_ct(Integer dr_snt_dly_cr_ct) {
		this.dr_snt_dly_cr_ct = dr_snt_dly_cr_ct;
	}

	public Double getDr_snt_dly_db() {
		return dr_snt_dly_db;
	}

	public void setDr_snt_dly_db(Double dr_snt_dly_db) {
		this.dr_snt_dly_db = dr_snt_dly_db;
	}

	public Integer getDr_snt_dly_db_ct() {
		return dr_snt_dly_db_ct;
	}

	public void setDr_snt_dly_db_ct(Integer dr_snt_dly_db_ct) {
		this.dr_snt_dly_db_ct = dr_snt_dly_db_ct;
	}

	public Double getDr_snt_dly_pay() {
		return dr_snt_dly_pay;
	}

	public void setDr_snt_dly_pay(Double dr_snt_dly_pay) {
		this.dr_snt_dly_pay = dr_snt_dly_pay;
	}

	public Integer getDr_snt_dly_pay_ct() {
		return dr_snt_dly_pay_ct;
	}

	public void setDr_snt_dly_pay_ct(Integer dr_snt_dly_pay_ct) {
		this.dr_snt_dly_pay_ct = dr_snt_dly_pay_ct;
	}

	public Double getDr_snt_bal_subj_fin() {
		return dr_snt_bal_subj_fin;
	}

	public void setDr_snt_bal_subj_fin(Double dr_snt_bal_subj_fin) {
		this.dr_snt_bal_subj_fin = dr_snt_bal_subj_fin;
	}

	public Double getDr_snt_bal_open_fin() {
		return dr_snt_bal_open_fin;
	}

	public void setDr_snt_bal_open_fin(Double dr_snt_bal_open_fin) {
		this.dr_snt_bal_open_fin = dr_snt_bal_open_fin;
	}

	public Double getDr_snt_dly_ngck_nn() {
		return dr_snt_dly_ngck_nn;
	}

	public void setDr_snt_dly_ngck_nn(Double dr_snt_dly_ngck_nn) {
		this.dr_snt_dly_ngck_nn = dr_snt_dly_ngck_nn;
	}

	public Double getDr_snt_dly_ngck_rn() {
		return dr_snt_dly_ngck_rn;
	}

	public void setDr_snt_dly_ngck_rn(Double dr_snt_dly_ngck_rn) {
		this.dr_snt_dly_ngck_rn = dr_snt_dly_ngck_rn;
	}

	public Double getDr_snt_roc() {
		return dr_snt_roc;
	}

	public void setDr_snt_roc(Double dr_snt_roc) {
		this.dr_snt_roc = dr_snt_roc;
	}

	public Integer getDr_snt_roc_ct() {
		return dr_snt_roc_ct;
	}

	public void setDr_snt_roc_ct(Integer dr_snt_roc_ct) {
		this.dr_snt_roc_ct = dr_snt_roc_ct;
	}

	public Integer getWs_days_difference() {
		return ws_days_difference;
	}

	public void setWs_days_difference(Integer ws_days_difference) {
		this.ws_days_difference = ws_days_difference;
	}

	public Long getWs_start_cycle_date() {
		return ws_start_cycle_date;
	}

	public void setWs_start_cycle_date(Long ws_start_cycle_date) {
		this.ws_start_cycle_date = ws_start_cycle_date;
	}

	public Integer getDr_field_01() {
		return dr_field_01;
	}

	public void setDr_field_01(Integer dr_field_01) {
		this.dr_field_01 = dr_field_01;
	}

	public Integer getDr_field_02() {
		return dr_field_02;
	}

	public void setDr_field_02(Integer dr_field_02) {
		this.dr_field_02 = dr_field_02;
	}

	public Integer getDr_field_03() {
		return dr_field_03;
	}

	public void setDr_field_03(Integer dr_field_03) {
		this.dr_field_03 = dr_field_03;
	}

	public String getDr_field_04() {
		return dr_field_04;
	}

	public void setDr_field_04(String dr_field_04) {
		this.dr_field_04 = dr_field_04;
	}

	public String getDr_field_05() {
		return dr_field_05;
	}

	public void setDr_field_05(String dr_field_05) {
		this.dr_field_05 = dr_field_05;
	}

	public String getDr_field_06() {
		return dr_field_06;
	}

	public void setDr_field_06(String dr_field_06) {
		this.dr_field_06 = dr_field_06;
	}

	public Long getDr_field_07() {
		return dr_field_07;
	}

	public void setDr_field_07(Long dr_field_07) {
		this.dr_field_07 = dr_field_07;
	}

	public Long getDr_field_08() {
		return dr_field_08;
	}

	public void setDr_field_08(Long dr_field_08) {
		this.dr_field_08 = dr_field_08;
	}

	public Long getDr_field_09() {
		return dr_field_09;
	}

	public void setDr_field_09(Long dr_field_09) {
		this.dr_field_09 = dr_field_09;
	}

	public Double getDr_field_10() {
		return dr_field_10;
	}

	public void setDr_field_10(Double dr_field_10) {
		this.dr_field_10 = dr_field_10;
	}

	public Double getDr_field_11() {
		return dr_field_11;
	}

	public void setDr_field_11(Double dr_field_11) {
		this.dr_field_11 = dr_field_11;
	}

	public Double getDr_field_12() {
		return dr_field_12;
	}

	public void setDr_field_12(Double dr_field_12) {
		this.dr_field_12 = dr_field_12;
	}
	
}
