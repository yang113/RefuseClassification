package com.example.carclub.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 * @TableName classification
 */
@TableName(value ="classification")
public class Classification implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String minClassNum;

    /**
     * 
     */
    private String minClass;

    /**
     * 
     */
    private String classname;

    /**
     * 
     */
    private String handle;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     */
    public String getMinClassNum() {
        return minClassNum;
    }

    /**
     * 
     */
    public void setMinClassNum(String minClassNum) {
        this.minClassNum = minClassNum;
    }

    /**
     * 
     */
    public String getMinClass() {
        return minClass;
    }

    /**
     * 
     */
    public void setMinClass(String minClass) {
        this.minClass = minClass;
    }

    /**
     * 
     */
    public String getClassname() {
        return classname;
    }

    /**
     * 
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /**
     * 
     */
    public String getHandle() {
        return handle;
    }

    /**
     * 
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Classification other = (Classification) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMinClassNum() == null ? other.getMinClassNum() == null : this.getMinClassNum().equals(other.getMinClassNum()))
            && (this.getMinClass() == null ? other.getMinClass() == null : this.getMinClass().equals(other.getMinClass()))
            && (this.getClassname() == null ? other.getClassname() == null : this.getClassname().equals(other.getClassname()))
            && (this.getHandle() == null ? other.getHandle() == null : this.getHandle().equals(other.getHandle()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMinClassNum() == null) ? 0 : getMinClassNum().hashCode());
        result = prime * result + ((getMinClass() == null) ? 0 : getMinClass().hashCode());
        result = prime * result + ((getClassname() == null) ? 0 : getClassname().hashCode());
        result = prime * result + ((getHandle() == null) ? 0 : getHandle().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", minClassNum=").append(minClassNum);
        sb.append(", minClass=").append(minClass);
        sb.append(", classname=").append(classname);
        sb.append(", handle=").append(handle);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}