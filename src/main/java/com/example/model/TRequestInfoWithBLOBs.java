package com.example.model;

public class TRequestInfoWithBLOBs extends TRequestInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_request_info.reason
     *
     * @mbg.generated
     */
    private String reason;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_request_info.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_request_info.reason
     *
     * @return the value of t_request_info.reason
     *
     * @mbg.generated
     */
    public String getReason() {
        return reason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_request_info.reason
     *
     * @param reason the value for t_request_info.reason
     *
     * @mbg.generated
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_request_info.content
     *
     * @return the value of t_request_info.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_request_info.content
     *
     * @param content the value for t_request_info.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}