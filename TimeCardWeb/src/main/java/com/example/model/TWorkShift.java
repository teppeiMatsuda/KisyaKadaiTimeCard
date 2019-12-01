package com.example.model;

import java.util.Date;

public class TWorkShift {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_work_shift.user_id
	 * @mbg.generated
	 */
	private Integer userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_work_shift.work_date
	 * @mbg.generated
	 */
	private Date workDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_work_shift.start_of_work_time
	 * @mbg.generated
	 */
	private String startOfWorkTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_work_shift.end_of_work_time
	 * @mbg.generated
	 */
	private String endOfWorkTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_work_shift.delete_flag
	 * @mbg.generated
	 */
	private String deleteFlag;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_work_shift.user_id
	 * @return  the value of t_work_shift.user_id
	 * @mbg.generated
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_work_shift.user_id
	 * @param userId  the value for t_work_shift.user_id
	 * @mbg.generated
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_work_shift.work_date
	 * @return  the value of t_work_shift.work_date
	 * @mbg.generated
	 */
	public Date getWorkDate() {
		return workDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_work_shift.work_date
	 * @param workDate  the value for t_work_shift.work_date
	 * @mbg.generated
	 */
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_work_shift.start_of_work_time
	 * @return  the value of t_work_shift.start_of_work_time
	 * @mbg.generated
	 */
	public String getStartOfWorkTime() {
		return startOfWorkTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_work_shift.start_of_work_time
	 * @param startOfWorkTime  the value for t_work_shift.start_of_work_time
	 * @mbg.generated
	 */
	public void setStartOfWorkTime(String startOfWorkTime) {
		this.startOfWorkTime = startOfWorkTime == null ? null : startOfWorkTime.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_work_shift.end_of_work_time
	 * @return  the value of t_work_shift.end_of_work_time
	 * @mbg.generated
	 */
	public String getEndOfWorkTime() {
		return endOfWorkTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_work_shift.end_of_work_time
	 * @param endOfWorkTime  the value for t_work_shift.end_of_work_time
	 * @mbg.generated
	 */
	public void setEndOfWorkTime(String endOfWorkTime) {
		this.endOfWorkTime = endOfWorkTime == null ? null : endOfWorkTime.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_work_shift.delete_flag
	 * @return  the value of t_work_shift.delete_flag
	 * @mbg.generated
	 */
	public String getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_work_shift.delete_flag
	 * @param deleteFlag  the value for t_work_shift.delete_flag
	 * @mbg.generated
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
	}
}