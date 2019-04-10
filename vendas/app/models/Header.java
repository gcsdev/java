package models;

import play.data.validation.Constraints;
import javax.persistence.Entity;

/**
 * Header entity managed by Ebean
 */
@Entity
public class Header extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Integer register;
    private Integer establishment;
    private String dateProcessing;
    private Integer initialPeriod;
    private Integer finalPeriod;
    private Integer sequence;
    private String enterprise;
    private Integer extractType;
    private String filler;
    private String layoutVersion;
    private String releaseVersion;
    private String reserved;

    public Integer getRegister() {
        return register;
    }

    public void setRegister(Integer register) {
        this.register = register;
    }

    public Integer getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Integer establishment) {
        this.establishment = establishment;
    }

    public String getDateProcessing() {
        return dateProcessing;
    }

    public void setDateProcessing(String dateProcessing) {
        this.dateProcessing = dateProcessing;
    }

    public Integer getInitialPeriod() {
        return initialPeriod;
    }

    public void setInitialPeriod(Integer initialPeriod) {
        this.initialPeriod = initialPeriod;
    }

    public Integer getFinalPeriod() {
        return finalPeriod;
    }

    public void setFinalPeriod(Integer finalPeriod) {
        this.finalPeriod = finalPeriod;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public Integer getExtractType() {
        return extractType;
    }

    public void setExtractType(Integer extractType) {
        this.extractType = extractType;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getLayoutVersion() {
        return layoutVersion;
    }

    public void setLayoutVersion(String layoutVersion) {
        this.layoutVersion = layoutVersion;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

}
