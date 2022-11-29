package com.example.carclub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName vip
 */
@TableName(value ="vip")
@Data
public class Vip implements Serializable {
    /**
     * 
     */
    @TableId
    private String vipnum;

    /**
     * 
     */
    private String vname;

    /**
     * 
     */
    private String vsex;

    /**
     * 
     */
    private String vphone;

    /**
     * 
     */
    private String vrank;

    /**
     * 
     */
    private Integer vmoney;

    /**
     * 
     */
    private String vipid;

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
        Vip other = (Vip) that;
        return (this.getVipnum() == null ? other.getVipnum() == null : this.getVipnum().equals(other.getVipnum()))
            && (this.getVname() == null ? other.getVname() == null : this.getVname().equals(other.getVname()))
            && (this.getVsex() == null ? other.getVsex() == null : this.getVsex().equals(other.getVsex()))
            && (this.getVphone() == null ? other.getVphone() == null : this.getVphone().equals(other.getVphone()))
            && (this.getVrank() == null ? other.getVrank() == null : this.getVrank().equals(other.getVrank()))
            && (this.getVmoney() == null ? other.getVmoney() == null : this.getVmoney().equals(other.getVmoney()))
            && (this.getVipid() == null ? other.getVipid() == null : this.getVipid().equals(other.getVipid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVipnum() == null) ? 0 : getVipnum().hashCode());
        result = prime * result + ((getVname() == null) ? 0 : getVname().hashCode());
        result = prime * result + ((getVsex() == null) ? 0 : getVsex().hashCode());
        result = prime * result + ((getVphone() == null) ? 0 : getVphone().hashCode());
        result = prime * result + ((getVrank() == null) ? 0 : getVrank().hashCode());
        result = prime * result + ((getVmoney() == null) ? 0 : getVmoney().hashCode());
        result = prime * result + ((getVipid() == null) ? 0 : getVipid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", vipnum=").append(vipnum);
        sb.append(", vname=").append(vname);
        sb.append(", vsex=").append(vsex);
        sb.append(", vphone=").append(vphone);
        sb.append(", vrank=").append(vrank);
        sb.append(", vmoney=").append(vmoney);
        sb.append(", vipid=").append(vipid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}