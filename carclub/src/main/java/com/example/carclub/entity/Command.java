package com.example.carclub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName command
 */
@TableName(value ="command")
@Data
public class Command implements Serializable {
    /**
     * 
     */
    @TableId
    private String serveid;

    /**
     * 
     */
    @TableId
    private String vipnum;

    /**
     * 
     */
    private Date ctime;

    /**
     * 
     */
    private String ename;

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
        Command other = (Command) that;
        return (this.getServeid() == null ? other.getServeid() == null : this.getServeid().equals(other.getServeid()))
            && (this.getVipnum() == null ? other.getVipnum() == null : this.getVipnum().equals(other.getVipnum()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getEname() == null ? other.getEname() == null : this.getEname().equals(other.getEname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getServeid() == null) ? 0 : getServeid().hashCode());
        result = prime * result + ((getVipnum() == null) ? 0 : getVipnum().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getEname() == null) ? 0 : getEname().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serveid=").append(serveid);
        sb.append(", vipnum=").append(vipnum);
        sb.append(", ctime=").append(ctime);
        sb.append(", ename=").append(ename);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}