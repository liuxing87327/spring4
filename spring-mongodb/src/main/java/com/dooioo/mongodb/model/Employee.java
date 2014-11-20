package com.dooioo.mongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

/**
 * 功能说明：Employee
 * 作者：liuxing(2014-11-21 04:31)
 */
@Document(collection = "Employee")
public class Employee {

    @Id
    private String userCode;
    private String userName;
    private String orgName;
    private String status;
    private String position;
    private Long createdAt;
    private Long updatedAt;

    @Override
    public String toString() {
        return "Employee[userCode=" + userCode + ",userName=" + userName + ",orgName=" + orgName + "]";
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

}
