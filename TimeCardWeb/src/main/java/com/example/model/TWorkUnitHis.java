package com.example.model;

import java.util.Date;

public class TWorkUnitHis extends TWorkUnitHisKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_work_unit_his.user_id
	 * @mbg.generated
	 */
	private Integer userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_work_unit_his.work_date
	 * @mbg.generated
	 */
	private Date workDate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_work_unit_his.user_id
	 * @return  the value of t_work_unit_his.user_id
	 * @mbg.generated
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_work_unit_his.user_id
	 * @param userId  the value for t_work_unit_his.user_id
	 * @mbg.generated
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_work_unit_his.work_date
	 * @return  the value of t_work_unit_his.work_date
	 * @mbg.generated
	 */
	public Date getWorkDate() {
		return workDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_work_unit_his.work_date
	 * @param workDate  the value for t_work_unit_his.work_date
	 * @mbg.generated
	 */
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
}