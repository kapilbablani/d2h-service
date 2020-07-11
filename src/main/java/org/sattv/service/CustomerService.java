package org.sattv.service;

import org.sattv.beans.Channel;
import org.sattv.beans.Customer;
import org.sattv.beans.Discount;
import org.sattv.beans.Subscription;

import java.util.List;

/**
 * This service layer abstraction is responsible
 * for the customer operations
 *
 * @author kapilb
 * @since 1.0.0.RELEASE
 */
public interface CustomerService {

    Double getBalance(Customer customer);

    Double rechargeAccount(Customer customer, String rechargeAmount);

    Customer updateContact(Customer customer, String email, String phone);

    String sendNotificationOverMail(Customer customer);

    String sendNotificationOverSMS(Customer customer);

    Subscription subscribeSpecialService(Customer customer, String subscriptionName);

    Discount subscribePackage(Customer customer, String packNeeded, String months);

    List<Channel> subscribeChannels(Customer customer, String channelList);
}
