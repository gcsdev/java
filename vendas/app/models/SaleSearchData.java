package models;

import play.data.validation.Constraints;

/** DTO class to SaleSearch */
public class SaleSearchData {

    @Constraints.Required
    private Integer date;

    public SaleSearchData() {

    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getDate() {
        return this.date;
    }
}