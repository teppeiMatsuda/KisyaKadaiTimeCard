package com.example.repository;

import com.example.model.TUserDetail;
import com.example.model.TUserDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserDetailMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_detail
	 * @mbg.generated
	 */
	long countByExample(TUserDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_detail
	 * @mbg.generated
	 */
	int deleteByExample(TUserDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_detail
	 * @mbg.generated
	 */
	int insert(TUserDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_detail
	 * @mbg.generated
	 */
	int insertSelective(TUserDetail record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_detail
	 * @mbg.generated
	 */
	List<TUserDetail> selectByExample(TUserDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_detail
	 * @mbg.generated
	 */
	int updateByExampleSelective(@Param("record") TUserDetail record, @Param("example") TUserDetailExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_user_detail
	 * @mbg.generated
	 */
	int updateByExample(@Param("record") TUserDetail record, @Param("example") TUserDetailExample example);
}