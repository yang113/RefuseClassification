package com.example.carclub.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName information
 */
@TableName(value ="information")
public class Information implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer value;

    /**
     * 
     */
    private Date date;

    /**
     * 
     */
    private String province;

    /**
     * 
     */
    private String city;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String link;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * 
     */
    public Date getDate() {
        return date;
    }

    /**
     * 
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 
     */
    public String getProvince() {
        return province;
    }

    /**
     * 
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     */
    public String getLink() {
        return link;
    }

    /**
     * 
     */
    public void setLink(String link) {
        this.link = link;
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
        Information other = (Information) that;
        return (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getLink() == null ? other.getLink() == null : this.getLink().equals(other.getLink()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getLink() == null) ? 0 : getLink().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", value=").append(value);
        sb.append(", date=").append(date);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", title=").append(title);
        sb.append(", link=").append(link);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}