package org.sattv.factory;

import org.sattv.beans.Channel;
import org.sattv.beans.Customer;
import org.sattv.beans.Package;
import org.sattv.beans.Subscription;

import java.util.List;

/**
 * This class is the builder class for the Customer as
 * the customer properties are large in number and many
 * of them can be treated as Optional
 *
 * Based on Builder Design Pattern
 */
public class CustomerBuilder {
    private Integer id;
    private String name;
    private String email;
    private String mobile;
    private Double balance;
    // this will contain channels opted separately,
    // not one already included in the package
    private List<Channel> channels;
    private Package packageOpted;
    private List<Subscription> subscriptions;

    public CustomerBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public CustomerBuilder setBalance(Double balance) {
        this.balance = balance;
        return this;
    }

    public CustomerBuilder setChannels(List<Channel> channels) {
        this.channels = channels;
        return this;
    }

    public CustomerBuilder setPackageOpted(Package packageOpted) {
        this.packageOpted = packageOpted;
        return this;
    }

    public CustomerBuilder setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
        return this;
    }

    public Customer build() {
        return new Customer(id, name, email, mobile, balance, channels, packageOpted, subscriptions);
    }
}
