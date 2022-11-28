package com.example.carclub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName travel
 */
@TableName(value ="travel")
@Data
public class Travel implements Serializable {
    /**
     * 
     */
    @TableId
    private String travelid;

    /**
     * 
     */
    private String vipnum;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")
    private Date traveltime;

    /**
     * 
     */
    private String traveladdress;

    /**
     * 
     */
    private BigDecimal travelprice;

    /**
     * 
     */
    private String carnum;

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
        Travel other = (Travel) that;
        return (this.getTravelid() == null ? other.getTravelid() == null : this.getTravelid().equals(other.getTravelid()))
            && (this.getVipnum() == null ? other.getVipnum() == null : this.getVipnum().equals(other.getVipnum()))
            && (this.getTraveltime() == null ? other.getTraveltime() == null : this.getTraveltime().equals(other.getTraveltime()))
            && (this.getTraveladdress() == null ? other.getTraveladdress() == null : this.getTraveladdress().equals(other.getTraveladdress()))
            && (this.getTravelprice() == null ? other.getTravelprice() == null : this.getTravelprice().equals(other.getTravelprice()))
            && (this.getCarnum() == null ? other.getCarnum() == null : this.getCarnum().equals(other.getCarnum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTravelid() == null) ? 0 : getTravelid().hashCode());
        result = prime * result + ((getVipnum() == null) ? 0 : getVipnum().hashCode());
        result = prime * result + ((getTraveltime() == null) ? 0 : getTraveltime().hashCode());
        result = prime * result + ((getTraveladdress() == null) ? 0 : getTraveladdress().hashCode());
        result = prime * result + ((getTravelprice() == null) ? 0 : getTravelprice().hashCode());
        result = prime * result + ((getCarnum() == null) ? 0 : getCarnum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", travelid=").append(travelid);
        sb.append(", vipnum=").append(vipnum);
        sb.append(", traveltime=").append(traveltime);
        sb.append(", traveladdress=").append(traveladdress);
        sb.append(", travelprice=").append(travelprice);
        sb.append(", carnum=").append(carnum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}