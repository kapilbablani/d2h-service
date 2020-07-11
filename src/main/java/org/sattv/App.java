package org.sattv;

import org.sattv.beans.*;
import org.sattv.beans.Package;
import org.sattv.exception.InvalidInputException;
import org.sattv.factory.CustomerBuilder;
import org.sattv.service.ChannelService;
import org.sattv.service.CustomerService;
import org.sattv.service.PackageService;
import org.sattv.service.SubscriptionService;
import org.sattv.service.impl.ChannelServiceImpl;
import org.sattv.service.impl.CustomerServiceImpl;
import org.sattv.service.impl.PackageServiceImpl;
import org.sattv.service.impl.SubscriptionServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.sattv.utility.PropertyUtil.*;

public class App 
{
    public static void main( String[] args ) throws IOException {

        CustomerService customerService = new CustomerServiceImpl();

        Customer customer = new CustomerBuilder()
                .setId(1)
                .setName("Test")
                .setEmail("test@test.com")
                .setMobile("1112223334")
                .setBalance(INITIAL_BALANCE)
                .build();

        while(true) {
            populateMenu();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine();

            try {
                int selection = Integer.parseInt(name);
                switch (selection) {
                    case 1:
                        System.out.println(MENU_OPTION_VIEW_BAL);
                        System.out.println(USER_MSG_CURRENT_BALANCE + customerService.getBalance(customer));
                        System.out.println(LINE_BREAKER);
                        break;
                    case 2:
                        System.out.print(USER_MSG_RECHARGE_AMOUNT);
                        BufferedReader amount = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println(USER_MSG_RECHARGE_COMPLETE + customerService.rechargeAccount(customer, amount.readLine()));
                        System.out.println(LINE_BREAKER);
                        break;
                    case 3:
                        System.out.println(MENU_OPTION_VIEW_PACKS);
                        getPackages();
                        getAvailableChannels();
                        getAvailableSubscription();
                        System.out.println(LINE_BREAKER);
                        break;
                    case 4:
                        System.out.print(USER_MSG_ENTER_PACK);
                        BufferedReader pack = new BufferedReader(new InputStreamReader(System.in));
                        String packNeeded = pack.readLine();
                        System.out.print(USER_MSG_ENTER_PACK_MONTH);
                        String months = pack.readLine();
                        Discount discount = customerService.subscribePackage(customer, packNeeded, months);
                        System.out.println(USER_MSG_SUCCESS_MSG_PACK_SUBSCRIBED + customer.getPackageOpted().getAbbreviation());
                        System.out.println(USER_MSG_MONTHLY_PRICE + discount.getPrice() + "Rs.");
                        System.out.println(USER_MSG_NO_OF_MONTH + discount.getMonth());
                        System.out.println(USER_MSG_SUBSCRIPTION_AMT + discount.getAmount());
                        System.out.println(USER_MSG_DISCOUNT_APPLIED + discount.getDiscountApplied());
                        System.out.println(USER_MSG_PRICE_AFTER_DISCOUNT + discount.getPriceAfterDiscount());
                        System.out.println(USER_MSG_ACC_BALANCE + customer.getBalance());
                        System.out.println(customerService.sendNotificationOverMail(customer));
                        System.out.println(customerService.sendNotificationOverSMS(customer));
                        System.out.println(LINE_BREAKER);
                        break;
                    case 5:
                        System.out.println(MENU_OPTION_ADD_CHANNEL);
                        System.out.print(USER_MSG_ENTER_CHANNEL);
                        BufferedReader channel = new BufferedReader(new InputStreamReader(System.in));
                        customerService.subscribeChannels(customer, channel.readLine());
                        System.out.println(USER_MSG_CHANNEL_ADDED);
                        System.out.println(USER_MSG_ACC_BALANCE + customer.getBalance());
                        System.out.println(LINE_BREAKER);
                        break;
                    case 6:
                        System.out.print(USER_MSG_ENTER_SERVICE_NAME);
                        BufferedReader subscriptionName = new BufferedReader(new InputStreamReader(System.in));
                        customerService.subscribeSpecialService(customer, subscriptionName.readLine());
                        System.out.println(SUCCESS_MSG_SVC_SUBSCRIBED);
                        System.out.println(USER_MSG_CURRENT_BALANCE + customerService.getBalance(customer));
                        System.out.println(customerService.sendNotificationOverMail(customer));
                        customerService.sendNotificationOverSMS(customer);
                        System.out.println(LINE_BREAKER);
                        break;
                    case 7:
                        System.out.println(MENU_OPTION_VIEW_SUBSCRIPTION);
                        System.out.println(USER_MSG_CURRENT_SUBSCRIPTION + getPackageOpted(customer) + ", " + getChannelOpted(customer));
                        System.out.println(USER_MSG_CURRENT_SERVICES + getSubscriptionOpted(customer));
                        System.out.println(LINE_BREAKER);
                        break;
                    case 8:
                        System.out.println(MENU_OPTION_UPDATE_CONTACT);
                        System.out.print(USER_MSG_ENTER_EMAIL);
                        BufferedReader email = new BufferedReader(new InputStreamReader(System.in));
                        String inputEmail = email.readLine();
                        System.out.print(USER_MSG_ENTER_PHONE);
                        BufferedReader phone = new BufferedReader(new InputStreamReader(System.in));
                        String inputMobile = phone.readLine();
                        customerService.updateContact(customer, inputEmail, inputMobile);
                        System.out.println(USER_MSG_CONTACT_UPDATE_SUCCESS);
                        System.out.println(LINE_BREAKER);
                        break;
                    case 9:
                        System.out.println(GRATITUDE_MESSAGE);
                        System.exit(0);
                }
            } catch(InvalidInputException e) {
                System.out.println(e.getMessage());
                System.out.println(LINE_BREAKER);
            } catch (Exception e) {
                System.out.println(ERR_MSG_INVALID_SELECTION);
                System.out.println(LINE_BREAKER);
            }
        }
    }

    /* The below all methods are just for UI purpose
        It call the respective services for channels,
        subscription & packages and format the output
        in the way UI wants it
     */
    private static void getPackages() {
        PackageService packageService = new PackageServiceImpl();
        System.out.println("Available packs for subscription");
        List<Package> packageList = packageService.getAllPackages();
        for(Package p : packageList) {
            System.out.print(p.getName() + ": ");
            for(Channel ch : p.getChannels())
                System.out.print(ch.getName() + ", ");
            System.out.println(": " + p.getPrice() + " Rs.");
        }
    }

    private static void getAvailableChannels() {
        ChannelService channelService = new ChannelServiceImpl();
        System.out.println("Available channels for subscription");
        List<Channel> channelList = channelService.getAllChannels();
        for(Channel ch : channelList)
            System.out.println(ch.getName() + ": "+ch.getPrice() + " Rs.");
    }

    private static void getAvailableSubscription() {
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        System.out.println("Available services for subscription");
        List<Subscription> subscriptions = subscriptionService.getAllSubscription();
        for(Subscription sub : subscriptions)
            System.out.println(sub.getName() + ": "+sub.getPrice() + " Rs.");
    }

    private static String getPackageOpted(Customer customer) {
        if(null == customer || null == customer.getPackageOpted() || "".equals(customer.getPackageOpted()))
            return ERR_MSG_NO_PACKAGE_OPTED;
        return customer.getPackageOpted().getName();
    }

    private static StringBuffer getChannelOpted(Customer customer) {
        if(null == customer || null == customer.getChannels() || customer.getChannels().isEmpty())
            return new StringBuffer(ERR_MSG_NO_CHANNEL_OPTED);
        StringBuffer str = new StringBuffer();
        for(Channel ch : customer.getChannels()) {
            str.append(ch.getName() + " + ");
        }
        return str;
    }

    private static StringBuffer getSubscriptionOpted(Customer customer) {
        if(null == customer || null == customer.getSubscriptions() || customer.getSubscriptions().isEmpty())
            return new StringBuffer(ERR_MSG_NO_SUBSCRIPTION_OPTED);
        StringBuffer str = new StringBuffer();
        for(Subscription sub : customer.getSubscriptions()) {
            str.append(sub.getName() + " + ");
        }
        return str;
    }


}
