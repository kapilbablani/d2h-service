package org.sattv.beans;

import java.io.Serializable;

    /**
     *    This bean will be used to hand over
     *    the discount details to the UI
     *  whenever any discount applies through any subscription
     *
     *  @author kapilb
     *  @since 1.0.0.RELEASE
    */
public class Discount implements Serializable {
    private Double price;
    private Integer month;
    private Double amount;
    private Double discountApplied;
    private Double priceAfterDiscount;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(Double discountApplied) {
        this.discountApplied = discountApplied;
    }

    public Double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(Double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }
}
