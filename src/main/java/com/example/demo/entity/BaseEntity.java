package com.example.demo.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Component
public class BaseEntity  implements Serializable {
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;


    public String getCreatedUser() {
        return createdUser;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(getCreatedUser(), that.getCreatedUser()) && Objects.equals(getCreatedTime(), that.getCreatedTime()) && Objects.equals(getModifiedUser(), that.getModifiedUser()) && Objects.equals(getModifiedTime(), that.getModifiedTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreatedUser(), getCreatedTime(), getModifiedUser(), getModifiedTime());
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "created_user='" + createdUser + '\'' +
                ", created_time=" + createdTime +
                ", modified_user='" + modifiedUser + '\'' +
                ", modified_time=" + modifiedTime +
                '}';
    }
}