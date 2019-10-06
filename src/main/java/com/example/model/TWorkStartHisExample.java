package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWorkStartHisExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    public TWorkStartHisExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andWorkUnitHisIdIsNull() {
            addCriterion("work_unit_his_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitHisIdIsNotNull() {
            addCriterion("work_unit_his_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitHisIdEqualTo(Integer value) {
            addCriterion("work_unit_his_id =", value, "workUnitHisId");
            return (Criteria) this;
        }

        public Criteria andWorkUnitHisIdNotEqualTo(Integer value) {
            addCriterion("work_unit_his_id <>", value, "workUnitHisId");
            return (Criteria) this;
        }

        public Criteria andWorkUnitHisIdGreaterThan(Integer value) {
            addCriterion("work_unit_his_id >", value, "workUnitHisId");
            return (Criteria) this;
        }

        public Criteria andWorkUnitHisIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("work_unit_his_id >=", value, "workUnitHisId");
            return (Criteria) this;
        }

        public Criteria andWorkUnitHisIdLessThan(Integer value) {
            addCriterion("work_unit_his_id <", value, "workUnitHisId");
            return (Criteria) this;
        }

        public Criteria andWorkUnitHisIdLessThanOrEqualTo(Integer value) {
            addCriterion("work_unit_his_id <=", value, "workUnitHisId");
            return (Criteria) this;
        }

        public Criteria andWorkUnitHisIdIn(List<Integer> values) {
            addCriterion("work_unit_his_id in", values, "workUnitHisId");
            return (Criteria) this;
        }

        public Criteria andWorkUnitHisIdNotIn(List<Integer> values) {
            addCriterion("work_unit_his_id not in", values, "workUnitHisId");
            return (Criteria) this;
        }

        public Criteria andWorkUnitHisIdBetween(Integer value1, Integer value2) {
            addCriterion("work_unit_his_id between", value1, value2, "workUnitHisId");
            return (Criteria) this;
        }

        public Criteria andWorkUnitHisIdNotBetween(Integer value1, Integer value2) {
            addCriterion("work_unit_his_id not between", value1, value2, "workUnitHisId");
            return (Criteria) this;
        }

        public Criteria andStartOfWorkTimeIsNull() {
            addCriterion("start_of_work_time is null");
            return (Criteria) this;
        }

        public Criteria andStartOfWorkTimeIsNotNull() {
            addCriterion("start_of_work_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartOfWorkTimeEqualTo(Date value) {
            addCriterion("start_of_work_time =", value, "startOfWorkTime");
            return (Criteria) this;
        }

        public Criteria andStartOfWorkTimeNotEqualTo(Date value) {
            addCriterion("start_of_work_time <>", value, "startOfWorkTime");
            return (Criteria) this;
        }

        public Criteria andStartOfWorkTimeGreaterThan(Date value) {
            addCriterion("start_of_work_time >", value, "startOfWorkTime");
            return (Criteria) this;
        }

        public Criteria andStartOfWorkTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_of_work_time >=", value, "startOfWorkTime");
            return (Criteria) this;
        }

        public Criteria andStartOfWorkTimeLessThan(Date value) {
            addCriterion("start_of_work_time <", value, "startOfWorkTime");
            return (Criteria) this;
        }

        public Criteria andStartOfWorkTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_of_work_time <=", value, "startOfWorkTime");
            return (Criteria) this;
        }

        public Criteria andStartOfWorkTimeIn(List<Date> values) {
            addCriterion("start_of_work_time in", values, "startOfWorkTime");
            return (Criteria) this;
        }

        public Criteria andStartOfWorkTimeNotIn(List<Date> values) {
            addCriterion("start_of_work_time not in", values, "startOfWorkTime");
            return (Criteria) this;
        }

        public Criteria andStartOfWorkTimeBetween(Date value1, Date value2) {
            addCriterion("start_of_work_time between", value1, value2, "startOfWorkTime");
            return (Criteria) this;
        }

        public Criteria andStartOfWorkTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_of_work_time not between", value1, value2, "startOfWorkTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_work_start_his
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_work_start_his
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}