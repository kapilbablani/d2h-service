package org.sattv.beans;

import java.io.Serializable;

    /**
     *  This bean will hold the details of the
     *  some additional services provided by d2h partners.
     *  Ex. LearnCooking, LearnEnglish
     *
     *  @author kapilb
     *  @since 1.0.0.RELEASE
    */
public class Subscription implements Serializable {
    private Integer id;
    private String name;
    /** this property will be used as UI display name
    and also enter enters the subscription
    name without word "Subscription"
    so this will hold the name without the word "Subscription" */
    private String abbreviation;
    private Double price;

    public Subscription(Integer id, String name, String abbreviation, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.abbreviation = abbreviation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
