package com.example.carclub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName department
 */
@TableName(value ="department")
@Data
public class Department implements Serializable {
    /**
     * 
     */
    @TableId
    private String depnum;

    /**
     * 
     */
    private String depname;

    /**
     * 
     */
    private String minister;

    /**
     * 
     */
    private Integer depnumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
        Department other = (Department) that;
        return (this.getDepnum() == null ? other.getDepnum() == null : this.getDepnum().equals(other.getDepnum()))
            && (this.getDepname() == null ? other.getDepname() == null : this.getDepname().equals(other.getDepname()))
            && (this.getMinister() == null ? other.getMinister() == null : this.getMinister().equals(other.getMinister()))
            && (this.getDepnumber() == null ? other.getDepnumber() == null : this.getDepnumber().equals(other.getDepnumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDepnum() == null) ? 0 : getDepnum().hashCode());
        result = prime * result + ((getDepname() == null) ? 0 : getDepname().hashCode());
        result = prime * result + ((getMinister() == null) ? 0 : getMinister().hashCode());
        result = prime * result + ((getDepnumber() == null) ? 0 : getDepnumber().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", depnum=").append(depnum);
        sb.append(", depname=").append(depname);
        sb.append(", minister=").append(minister);
        sb.append(", depnumber=").append(depnumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}