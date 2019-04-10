package models;

import play.data.validation.Constraints;
import javax.persistence.Entity;

/**
 * Trailler entity managed by Ebean
 */
@Entity
public class Trailler extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Integer registerType;
    private Integer registerTotal;
    private String reserved;

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    public Integer getRegisterTotal() {
        return registerTotal;
    }

    public void setRegisterTotal(Integer registerTotal) {
        this.registerTotal = registerTotal;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

}
