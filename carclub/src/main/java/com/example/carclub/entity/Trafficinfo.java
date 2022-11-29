package com.example.carclub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName trafficinfo
 */
@TableName(value ="trafficinfo")
@Data
public class Trafficinfo implements Serializable {
    /**
     * 
     */
    @TableId
    private String serveid;

    /**
     * 
     */
    private String servename;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")
    private Date servetime;

    /**
     * 
     */
    private String servecon;

    /**
     * 
     */
    private Integer vipnum;

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
        Trafficinfo other = (Trafficinfo) that;
        return (this.getServeid() == null ? other.getServeid() == null : this.getServeid().equals(other.getServeid()))
            && (this.getServename() == null ? other.getServename() == null : this.getServename().equals(other.getServename()))
            && (this.getServetime() == null ? other.getServetime() == null : this.getServetime().equals(other.getServetime()))
            && (this.getServecon() == null ? other.getServecon() == null : this.getServecon().equals(other.getServecon()))
            && (this.getVipnum() == null ? other.getVipnum() == null : this.getVipnum().equals(other.getVipnum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getServeid() == null) ? 0 : getServeid().hashCode());
        result = prime * result + ((getServename() == null) ? 0 : getServename().hashCode());
        result = prime * result + ((getServetime() == null) ? 0 : getServetime().hashCode());
        result = prime * result + ((getServecon() == null) ? 0 : getServecon().hashCode());
        result = prime * result + ((getVipnum() == null) ? 0 : getVipnum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serveid=").append(serveid);
        sb.append(", servename=").append(servename);
        sb.append(", servetime=").append(servetime);
        sb.append(", servecon=").append(servecon);
        sb.append(", vipnum=").append(vipnum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}