package org.sattv.beans;

import java.io.Serializable;
import java.util.List;

    /**
     *   This bean will hold the customer
     *   personal details along with the channels/
     *   packages/services subscribed to him.
     *
     *   @author kapilb
     *   @since 1.0.0.RELEASE
    */
public class Customer implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String mobile;
    private Double balance;
    /**
     *  this will contain channels opted separately,
     *  not one already included in the package
    */
    private List<Channel> channels;
    private Package packageOpted;
    private List<Subscription> subscriptions;

    public Customer(Integer id, String name, String email, String mobile, Double balance, List<Channel> channels, Package packageOpted, List<Subscription> subscriptions) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.balance = balance;
        this.channels = channels;
        this.packageOpted = packageOpted;
        this.subscriptions = subscriptions;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public Package getPackageOpted() {
        return packageOpted;
    }

    public void setPackageOpted(Package packageOpted) {
        this.packageOpted = packageOpted;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", balance=" + balance +
                ", channels=" + channels +
                ", packageOpted=" + packageOpted +
                ", services=" + subscriptions +
                '}';
    }
}
