package org.sattv.beans;

import java.io.Serializable;
import java.util.List;

    /**
     *    This bean will hold the details of the
     *   package available in the d2h service.
     *  Every package will have list of channels associated
     *  to it.
     *  Ex. Gold (Zee, SONY etc)
     *
     *  @author kapilb
     *  @since 1.0.0.RELEASE
    */
public class Package implements Serializable {
    private Integer id;
    private String name;
    /**  this property will be used as UI display name
        and also used by user while entering the package
        for subscription
    */
    private String abbreviation;
    private Double price;
    private List<Channel> channels;

    public Package(Integer id, String name, String abbreviation, Double price, List<Channel> channels) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.price = price;
        this.channels = channels;
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

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", channels=" + channels +
                '}';
    }

}
