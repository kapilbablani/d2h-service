package org.sattv.beans;

import java.io.Serializable;
import java.util.List;

/**
 *  This bean will hold the channel details
 *  along with the package it belongs to.
 *
 *  @author kapilb
 *  @since 1.0.0.RELEASE
 */
public class Channel implements Serializable {
    private Integer id;
    private String name;
    private Double price;
    private List<Integer> packageIds;

    public Channel(Integer id, String name, Double price, List<Integer> packageIds) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.packageIds = packageIds;
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

    public List<Integer> getPackageId() {
        return packageIds;
    }

    public void setPackageId(List<Integer> packageIds) {
        this.packageIds = packageIds;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", packageIds=" + packageIds +
                '}';
    }
}
