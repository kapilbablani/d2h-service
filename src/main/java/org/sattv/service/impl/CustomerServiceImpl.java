package org.sattv.service.impl;

import org.sattv.beans.*;
import org.sattv.beans.Package;
import org.sattv.exception.InvalidInputException;
import org.sattv.service.ChannelService;
import org.sattv.service.CustomerService;
import org.sattv.service.PackageService;
import org.sattv.service.SubscriptionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.sattv.utility.PropertyUtil.*;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public Double getBalance(Customer customer) {
        if(null == customer || null == customer.getBalance() || customer.getBalance() < 0)
            throw new InvalidInputException("Balance against customer is incorrect");
        return customer.getBalance();
    }

    @Override
    public Double rechargeAccount(Customer customer, String rechargeAmount) {
        try {
            Double rechargeAmt = Double.parseDouble(rechargeAmount);
            if(rechargeAmt < MIN_RECHARGE_AMOUNT)
                throw new InvalidInputException(ERR_MSG_MIN_RECHARGE_AMT);
            customer.setBalance(customer.getBalance() + rechargeAmt);
            return customer.getBalance();
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Recharge Amount Format is invalid");
        }
    }

    @Override
    public Customer updateContact(Customer customer, String email, String phone) {
        validateEmail(email);
        validateMobile(phone);
        customer.setEmail(email);
        customer.setMobile(phone);
        return customer;
    }

    @Override
    public String sendNotificationOverMail(Customer customer) {
        if(null == customer || null == customer.getEmail() || "".equalsIgnoreCase(customer.getEmail()))
            throw new InvalidInputException(ERR_MSG_MAIL_UNAVAILABLE);
        return USER_MSG_MAIL_SENT;
    }

    @Override
    public String sendNotificationOverSMS(Customer customer) {
        if(null == customer || null == customer.getMobile() || "".equalsIgnoreCase(customer.getMobile()))
            throw new InvalidInputException(ERR_MSG_MOBILE_UNAVAILABLE);
        return USER_MSG_SMS_SENT;
    }

    @Override
    public Subscription subscribeSpecialService(Customer customer,String subscriptionName) {
        validateSubscriptionName(subscriptionName);
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        Subscription subscription = subscriptionService.getSubscriptionByName(subscriptionName);

        if(customer.getBalance() < subscription.getPrice())
            throw new InvalidInputException(ERR_MSG_INSUFFICIENT_BAL_SUBSCRIBE + subscriptionName);
        List<Subscription> subscriptionList = null != customer.getSubscriptions() ? customer.getSubscriptions() : new ArrayList<>();

        if(subscriptionList.contains(subscription))
            throw new InvalidInputException(ERR_MSG_SUBSCRIPTION_ALREADY_OPTED);
        subscriptionList.add(subscription);
        customer.setSubscriptions(subscriptionList);
        customer.setBalance(customer.getBalance() - subscription.getPrice());
        return subscription;
    }

    @Override
    public Discount subscribePackage(Customer customer, String packNeeded, String months) {
        validatePack(packNeeded);
        int month = validateInteger(months);
        PackageService packageService = new PackageServiceImpl();
        Package packAboutToSubscribe = packageService.getPackageByName(packNeeded);
        
        if(customer.getBalance() < (packAboutToSubscribe.getPrice() * month))
            throw new InvalidInputException(ERR_MSG_INSUFFICIENT_BAL_PACKAGE + packAboutToSubscribe.getName() + " for "+ month + " months");

        /*  The below three lines of code is to check if package
            already opted then throw error, but intentionally
            commenting the code as to not block UI once
         */
        Package alreadySubscribed = null != customer.getPackageOpted() ? customer.getPackageOpted() : null;
        if(null != alreadySubscribed && packNeeded.equalsIgnoreCase(alreadySubscribed.getAbbreviation()))
            throw new InvalidInputException(ERR_MSG_PACKAGE_ALREADY_OPTED);

        Discount discount = calculateNetPriceAfterDiscount(packAboutToSubscribe, month);

        customer.setPackageOpted(packAboutToSubscribe);
        customer.setBalance(customer.getBalance() - discount.getPriceAfterDiscount());
        return discount;
    }

    private Discount calculateNetPriceAfterDiscount(Package pack, Integer months) {
        Discount discount = new Discount();
        discount.setPrice(pack.getPrice());
        discount.setMonth(months);

        Double actualAmount = pack.getPrice() * months.doubleValue();
        discount.setAmount(actualAmount);

        if(months >= getDiscountedMonth()) {
            Double priceAfterDiscount = actualAmount - (actualAmount * (getDiscountPercent() / 100D));
            discount.setPriceAfterDiscount(priceAfterDiscount);
            Double discountApplied = actualAmount - priceAfterDiscount;
            discount.setDiscountApplied(discountApplied);
        } else {
            discount.setPriceAfterDiscount(actualAmount);
            discount.setDiscountApplied(0D);
        }
        return discount;
    }

    @Override
    public List<Channel> subscribeChannels(Customer customer, String inputChannel) {
        List<String> channelList = validateChannelList(inputChannel);

        ChannelService channelService = new ChannelServiceImpl();
        List<Channel> channelListAboutToOpt = channelService.getChannelsByChannelNames(channelList);

        //  this method checks if user already has channel
        //  in package or individually added
        validateIfChannelAlreadyExistWithUser(customer, channelList);

        //  this method checks if user has
        //  sufficient balance to opt channels
        //  returns balance Required to opt
        Double totalBalRequiredToOptChannels = validateUserAccountBalanceToOptChannels(customer, channelListAboutToOpt);

        customer.setChannels(channelListAboutToOpt);
        customer.setBalance(customer.getBalance() - totalBalRequiredToOptChannels);
        return channelListAboutToOpt;
    }

    /**
     * This method is used to validate whether the user has sufficient
     * account balance to opt for the channels he/she requested
     *
     *
     * @param customer
     * @param channelListAboutToOpt
     * @return
     */
    private Double validateUserAccountBalanceToOptChannels(Customer customer, List<Channel> channelListAboutToOpt) {
        Double totalBalRequiredToOptChannels = channelListAboutToOpt
                .stream()
                .map(ch -> ch.getPrice())
                .reduce(0D, Double :: sum);

        if(customer.getBalance() < totalBalRequiredToOptChannels)
            throw new InvalidInputException(ERR_MSG_INSUFFICIENT_BAL_CHANNEL);
        return totalBalRequiredToOptChannels;
    }

    /**
     *  This method validates if the user has already subscribed for the same channel
     *  in his active plan either in form of package or individual channel selection
     *
     * @param customer
     * @param channelListAboutToOpt
     */
    private void validateIfChannelAlreadyExistWithUser(Customer customer, List<String> channelListAboutToOpt) {
        validateIfChannelAlreadyExistInPackage(customer, channelListAboutToOpt);
        validateIfChannelAlreadyExistInUserChannelList(customer, channelListAboutToOpt);
    }

    /**
     * This method validates if the user has already subscribed for the same channel
     *  in his active plan package
     *
     * @param customer
     * @param channelListAboutToOpt
     */
    private void validateIfChannelAlreadyExistInPackage(Customer customer, List<String> channelListAboutToOpt) {
        List<Channel> userChannelsInPack = null != customer.getPackageOpted() ? customer.getPackageOpted().getChannels() : new ArrayList<>();
        List<Channel> duplicateChannelInPack =  userChannelsInPack
                .stream()
                .filter(userChannel -> channelListAboutToOpt
                        .stream()
                        .anyMatch(ch -> ch.equalsIgnoreCase(userChannel.getName()))
                )
                .collect(Collectors.toList());

        if(! duplicateChannelInPack.isEmpty())
            throw new InvalidInputException(ERR_MSG_CHANNEL_ALREADY_IN_PACKAGE);
    }

    /**
     * this method validates if the user has already been subscribed for the
     * same channel in his channel selection through Individual channel selection mode
     *
     * @param customer
     * @param channelListAboutToOpt
     */
    private void validateIfChannelAlreadyExistInUserChannelList(Customer customer, List<String> channelListAboutToOpt) {
        List<Channel> userChannels = null != customer.getChannels() ? customer.getChannels() : new ArrayList<>();
        List<Channel> duplicateChannel =  userChannels
                .stream()
                .filter(userChannel -> channelListAboutToOpt
                        .stream()
                        .anyMatch(ch -> ch.equalsIgnoreCase(userChannel.getName()))
                )
                .collect(Collectors.toList());

        if(! duplicateChannel.isEmpty())
            throw new InvalidInputException(ERR_MSG_CHANNEL_ALREADY_IN_PLAN);
    }
}
