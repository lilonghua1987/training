package org.training.entities;


import org.apache.hadoop.hive.common.type.HiveDecimal;
import java.sql.Timestamp;



public class SMGlobstarDemo{
	private Long dr_cstone_feed_key;  //DR_CSTONE_FEED_KEY -> Julian Date along with extra char
	private Timestamp dr_cstone_last_updatetm;   //DR_CSTONE_LAST_UPDATETM  Time
	private String dr_org;          //DR_ORG --> Market Code (491 FIN)
	private String dr_region;
	private String dr_cus_id;
	private String dr_cus_curr_cd;
	private String dr_cm15;
	private String dr_cm13;
	private String dr_logo;         //DR_LOGO --> Product details
	private String dr_act_prod_type_cd;
	private String dr_act_sub_prod_type_cd;
	private String dr_act_actv_canc_in;
	private String dr_rec_type;
	private String dr_cd_cust_nbr;
	private Integer dr_cm_opt_addr_dt;
	private String dr_cm_opt_zip_intl;
	private Integer dr_cm_opt_anniv_dt;
	private String dr_crd_new_ind;
	private String dr_cm_opt_canc_reas_Intl; //Changed name from cm_opt_canc_reas_Integerl to cm_opt_canc_reas_Intl
	private Integer dr_canc_dt;
	private Integer dr_canc_tm;
	private String dr_cm_opt_coll_loc_Intl; //Changed name from cm_opt_coll_loc_Integerl to cm_opt_coll_loc_Intl
	private Integer dr_loc_dt;
	private Integer dr_loc_tm;
	private String dr_cm_opt_rein_reas_Intl; //Changed name from cm_opt_rein_reas_Integerl to cm_opt_rein_reas_Intl
	private Integer dr_rein_dt;
	private Integer dr_rein_tm;
	private Double dr_acc_crlim;
	private Integer dr_cm_opt_acct_eff_dt;
	private String dr_cut_grp;
	private Integer dr_acc_date_last_cycle;
	private String dr_acc_bill_group;
	private Integer dr_field_01;
	private Integer dr_field_02;
	private String dr_field_03;
	private String dr_field_04;
	private Integer dr_field_05;
	private Integer dr_field_06;
	private Double dr_field_07;
	private Double dr_field_08;
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
	public String getDr_act_prod_type_cd() {
		return dr_act_prod_type_cd;
	}
	public void setDr_act_prod_type_cd(String dr_act_prod_type_cd) {
		this.dr_act_prod_type_cd = dr_act_prod_type_cd;
	}
	public String getDr_act_sub_prod_type_cd() {
		return dr_act_sub_prod_type_cd;
	}
	public void setDr_act_sub_prod_type_cd(String dr_act_sub_prod_type_cd) {
		this.dr_act_sub_prod_type_cd = dr_act_sub_prod_type_cd;
	}
	public String getDr_act_actv_canc_in() {
		return dr_act_actv_canc_in;
	}
	public void setDr_act_actv_canc_in(String dr_act_actv_canc_in) {
		this.dr_act_actv_canc_in = dr_act_actv_canc_in;
	}
	public String getDr_rec_type() {
		return dr_rec_type;
	}
	public void setDr_rec_type(String dr_rec_type) {
		this.dr_rec_type = dr_rec_type;
	}
	public String getDr_cd_cust_nbr() {
		return dr_cd_cust_nbr;
	}
	public void setDr_cd_cust_nbr(String dr_cd_cust_nbr) {
		this.dr_cd_cust_nbr = dr_cd_cust_nbr;
	}
	public Integer getDr_cm_opt_addr_dt() {
		return dr_cm_opt_addr_dt;
	}
	public void setDr_cm_opt_addr_dt(Integer dr_cm_opt_addr_dt) {
		this.dr_cm_opt_addr_dt = dr_cm_opt_addr_dt;
	}
	public String getDr_cm_opt_zip_intl() {
		return dr_cm_opt_zip_intl;
	}
	public void setDr_cm_opt_zip_intl(String dr_cm_opt_zip_intl) {
		this.dr_cm_opt_zip_intl = dr_cm_opt_zip_intl;
	}
	public Integer getDr_cm_opt_anniv_dt() {
		return dr_cm_opt_anniv_dt;
	}
	public void setDr_cm_opt_anniv_dt(Integer dr_cm_opt_anniv_dt) {
		this.dr_cm_opt_anniv_dt = dr_cm_opt_anniv_dt;
	}
	public String getDr_crd_new_ind() {
		return dr_crd_new_ind;
	}
	public void setDr_crd_new_ind(String dr_crd_new_ind) {
		this.dr_crd_new_ind = dr_crd_new_ind;
	}
	public String getDr_cm_opt_canc_reas_Intl() {
		return dr_cm_opt_canc_reas_Intl;
	}
	public void setDr_cm_opt_canc_reas_Intl(String dr_cm_opt_canc_reas_Intl) {
		this.dr_cm_opt_canc_reas_Intl = dr_cm_opt_canc_reas_Intl;
	}
	public Integer getDr_canc_dt() {
		return dr_canc_dt;
	}
	public void setDr_canc_dt(Integer dr_canc_dt) {
		this.dr_canc_dt = dr_canc_dt;
	}
	public Integer getDr_canc_tm() {
		return dr_canc_tm;
	}
	public void setDr_canc_tm(Integer dr_canc_tm) {
		this.dr_canc_tm = dr_canc_tm;
	}
	public String getDr_cm_opt_coll_loc_Intl() {
		return dr_cm_opt_coll_loc_Intl;
	}
	public void setDr_cm_opt_coll_loc_Intl(String dr_cm_opt_coll_loc_Intl) {
		this.dr_cm_opt_coll_loc_Intl = dr_cm_opt_coll_loc_Intl;
	}
	public Integer getDr_loc_dt() {
		return dr_loc_dt;
	}
	public void setDr_loc_dt(Integer dr_loc_dt) {
		this.dr_loc_dt = dr_loc_dt;
	}
	public Integer getDr_loc_tm() {
		return dr_loc_tm;
	}
	public void setDr_loc_tm(Integer dr_loc_tm) {
		this.dr_loc_tm = dr_loc_tm;
	}
	public String getDr_cm_opt_rein_reas_Intl() {
		return dr_cm_opt_rein_reas_Intl;
	}
	public void setDr_cm_opt_rein_reas_Intl(String dr_cm_opt_rein_reas_Intl) {
		this.dr_cm_opt_rein_reas_Intl = dr_cm_opt_rein_reas_Intl;
	}
	public Integer getDr_rein_dt() {
		return dr_rein_dt;
	}
	public void setDr_rein_dt(Integer dr_rein_dt) {
		this.dr_rein_dt = dr_rein_dt;
	}
	public Integer getDr_rein_tm() {
		return dr_rein_tm;
	}
	public void setDr_rein_tm(Integer dr_rein_tm) {
		this.dr_rein_tm = dr_rein_tm;
	}
	public Double getDr_acc_crlim() {
		return dr_acc_crlim;
	}
	public void setDr_acc_crlim(Double dr_acc_crlim) {
		this.dr_acc_crlim = dr_acc_crlim;
	}
	public Integer getDr_cm_opt_acct_eff_dt() {
		return dr_cm_opt_acct_eff_dt;
	}
	public void setDr_cm_opt_acct_eff_dt(Integer dr_cm_opt_acct_eff_dt) {
		this.dr_cm_opt_acct_eff_dt = dr_cm_opt_acct_eff_dt;
	}
	public String getDr_cut_grp() {
		return dr_cut_grp;
	}
	public void setDr_cut_grp(String dr_cut_grp) {
		this.dr_cut_grp = dr_cut_grp;
	}
	public Integer getDr_acc_date_last_cycle() {
		return dr_acc_date_last_cycle;
	}
	public void setDr_acc_date_last_cycle(Integer dr_acc_date_last_cycle) {
		this.dr_acc_date_last_cycle = dr_acc_date_last_cycle;
	}
	public String getDr_acc_bill_group() {
		return dr_acc_bill_group;
	}
	public void setDr_acc_bill_group(String dr_acc_bill_group) {
		this.dr_acc_bill_group = dr_acc_bill_group;
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
	public String getDr_field_03() {
		return dr_field_03;
	}
	public void setDr_field_03(String dr_field_03) {
		this.dr_field_03 = dr_field_03;
	}
	public String getDr_field_04() {
		return dr_field_04;
	}
	public void setDr_field_04(String dr_field_04) {
		this.dr_field_04 = dr_field_04;
	}
	public Integer getDr_field_05() {
		return dr_field_05;
	}
	public void setDr_field_05(Integer dr_field_05) {
		this.dr_field_05 = dr_field_05;
	}
	public Integer getDr_field_06() {
		return dr_field_06;
	}
	public void setDr_field_06(Integer dr_field_06) {
		this.dr_field_06 = dr_field_06;
	}
	public Double getDr_field_07() {
		return dr_field_07;
	}
	public void setDr_field_07(Double dr_field_07) {
		this.dr_field_07 = dr_field_07;
	}
	public Double getDr_field_08() {
		return dr_field_08;
	}
	public void setDr_field_08(Double dr_field_08) {
		this.dr_field_08 = dr_field_08;
	}
}
