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
 * @TableName rescuerecords
 */
@TableName(value ="rescuerecords")
@Data
public class Rescuerecords implements Serializable {
    /**
     * 
     */
    @TableId
    private String rescueid;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Shanghai")
    private Date rescuetime;

    /**
     * 
     */
    private String rescueplace;

    /**
     * 
     */
    private String rescuestatus;

    /**
     * 
     */
    private String vipnum;

    /**
     * 
     */
    private String carid;

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
        Rescuerecords other = (Rescuerecords) that;
        return (this.getRescueid() == null ? other.getRescueid() == null : this.getRescueid().equals(other.getRescueid()))
            && (this.getRescuetime() == null ? other.getRescuetime() == null : this.getRescuetime().equals(other.getRescuetime()))
            && (this.getRescueplace() == null ? other.getRescueplace() == null : this.getRescueplace().equals(other.getRescueplace()))
            && (this.getRescuestatus() == null ? other.getRescuestatus() == null : this.getRescuestatus().equals(other.getRescuestatus()))
            && (this.getVipnum() == null ? other.getVipnum() == null : this.getVipnum().equals(other.getVipnum()))
            && (this.getCarid() == null ? other.getCarid() == null : this.getCarid().equals(other.getCarid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRescueid() == null) ? 0 : getRescueid().hashCode());
        result = prime * result + ((getRescuetime() == null) ? 0 : getRescuetime().hashCode());
        result = prime * result + ((getRescueplace() == null) ? 0 : getRescueplace().hashCode());
        result = prime * result + ((getRescuestatus() == null) ? 0 : getRescuestatus().hashCode());
        result = prime * result + ((getVipnum() == null) ? 0 : getVipnum().hashCode());
        result = prime * result + ((getCarid() == null) ? 0 : getCarid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rescueid=").append(rescueid);
        sb.append(", rescuetime=").append(rescuetime);
        sb.append(", rescueplace=").append(rescueplace);
        sb.append(", rescuestatus=").append(rescuestatus);
        sb.append(", vipnum=").append(vipnum);
        sb.append(", carid=").append(carid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}