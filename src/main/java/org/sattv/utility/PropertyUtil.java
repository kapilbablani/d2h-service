package org.sattv.utility;

import org.sattv.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PropertyUtil {

    private static final String ERR_MSG_INVALID_EMAIL = "Entered Email is Invalid";
    private static final String ERR_MSG_INVALID_MOBILE = "Entered Mobile Number is Invalid";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String MOBILE_REGEX = "(0/91)?[7-9][0-9]{9}";
    private static final Integer DISCOUNTED_MONTH_COUNT = 3;
    private static final Double APPLICABLE_DISCOUNT_PERCENT = 10D;
    public static final Double INITIAL_BALANCE = 100D;
    public static final Double MIN_RECHARGE_AMOUNT = 20D;

    public static final String ERR_MSG_INVALID_INPUT = "Input is Invalid";
    public static final String ERR_MSG_INVALID_SELECTION = "Invalid Menu Option Selection. Please try again";
    public static final String ERR_MSG_MIN_RECHARGE_AMT = "Min. Recharge Amount is Rs."+MIN_RECHARGE_AMOUNT;
    public static final String ERR_MSG_MAIL_UNAVAILABLE = "Email not registered to send mail notification";
    public static final String ERR_MSG_MOBILE_UNAVAILABLE = "Mobile Number not registered to send mail notification";
    public static final String ERR_MSG_NO_PACKAGE_OPTED = "No Package Opted";
    public static final String ERR_MSG_NO_CHANNEL_OPTED = "No Channel Opted";
    public static final String ERR_MSG_NO_SUBSCRIPTION_OPTED = "No Subscription Opted";
    public static final String ERR_MSG_INSUFFICIENT_BAL_SUBSCRIBE = "Insufficient Balance to Opt Subscription: ";
    public static final String ERR_MSG_SUBSCRIPTION_ALREADY_OPTED = "This Subscription is already opted by you";
    public static final String ERR_MSG_INSUFFICIENT_BAL_PACKAGE = "Insufficient Balance to Opt Package: ";
    public static final String ERR_MSG_INSUFFICIENT_BAL_CHANNEL = "Insufficient Balance to Opt Channel/s: ";
    public static final String ERR_MSG_PACKAGE_ALREADY_OPTED = "This same Package is already opted by you";
    public static final String ERR_MSG_CHANNEL_ALREADY_IN_PACKAGE = "Selected channel/s are already in your package";
    public static final String ERR_MSG_CHANNEL_ALREADY_IN_PLAN = "Selected channel/s is already opted by you";

    public static final String USER_INPUT_MESSAGE = "Enter The Option : ";
    public static final String USER_MSG_RECHARGE_COMPLETE = "Recharge completed successfully. Current balance is ";
    public static final String USER_MSG_RECHARGE_AMOUNT = "Enter the amount to recharge : ";
    public static final String USER_MSG_CURRENT_BALANCE = "Current Balance is Rs.";
    public static final String USER_MSG_ENTER_EMAIL = "Enter the email: ";
    public static final String USER_MSG_ENTER_PHONE = "Enter phone: ";
    public static final String USER_MSG_CONTACT_UPDATE_SUCCESS = "Email and Phone updated successfully";
    public static final String USER_MSG_CURRENT_SUBSCRIPTION = "Currently subscribed packs and channels: ";
    public static final String USER_MSG_CURRENT_SERVICES = "Currently subscribed services: ";
    public static final String USER_MSG_MAIL_SENT = "Email notification sent successfully";
    public static final String USER_MSG_SMS_SENT = "SMS notification sent successfully";
    public static final String USER_MSG_ENTER_SERVICE_NAME = "Enter the service name: (LearnEnglish/LearnCooking): ";
    public static final String USER_MSG_ENTER_PACK = "Enter the Pack you wish to subscribe: (Silver: ‘S’, Gold: ‘G’): ";
    public static final String USER_MSG_ENTER_PACK_MONTH = "Enter the months: ";
    public static final String USER_MSG_SUCCESS_MSG_PACK_SUBSCRIBED = "You have successfully subscribed the following packs - ";
    public static final String USER_MSG_MONTHLY_PRICE = "Monthly price: ";
    public static final String USER_MSG_NO_OF_MONTH = "No of months: ";
    public static final String USER_MSG_SUBSCRIPTION_AMT = "Subscription Amount: ";
    public static final String USER_MSG_DISCOUNT_APPLIED = "Discount applied: ";
    public static final String USER_MSG_PRICE_AFTER_DISCOUNT = "Final Price after discount: ";
    public static final String USER_MSG_ACC_BALANCE = "Account balance: ";
    public static final String USER_MSG_ENTER_CHANNEL = "Enter channel names to add (separated by commas): ";
    public static final String USER_MSG_CHANNEL_ADDED = "Channels added successfully. ";
    public static final String SUCCESS_MSG_SVC_SUBSCRIBED = "Service subscribed successfully";

    public static final String MENU_OPTION_VIEW_BAL = "View current balance in the account";
    public static final String MENU_OPTION_RECHARGE = "Recharge Account";
    public static final String MENU_OPTION_VIEW_PACKS = "View available packs, channels and services";
    public static final String MENU_OPTION_SUBSCRIBE_BASE_PACK = "Subscribe to base packs";
    public static final String MENU_OPTION_ADD_CHANNEL = "Add channels to an existing subscription";
    public static final String MENU_OPTION_SUBSCRIBE_SERVICE = "Subscribe to special services";
    public static final String MENU_OPTION_VIEW_SUBSCRIPTION = "View current subscription details";
    public static final String MENU_OPTION_UPDATE_CONTACT = "Update email and phone number for notifications";
    public static final String MENU_OPTION_EXIT = "EXIT";

    public static final String GRATITUDE_MESSAGE = "Thank You for being part of SatTV !!";
    public static final String INPUT_SEPARATOR = ",";

    public static final String LINE_BREAKER = "========================================";

    public static final void populateMenu() {
        System.out.println("Welcome to SatTV");
        System.out.println("1. "+ MENU_OPTION_VIEW_BAL);
        System.out.println("2. "+ MENU_OPTION_RECHARGE);
        System.out.println("3. "+ MENU_OPTION_VIEW_PACKS);
        System.out.println("4. "+ MENU_OPTION_SUBSCRIBE_BASE_PACK);
        System.out.println("5. "+ MENU_OPTION_ADD_CHANNEL);
        System.out.println("6. "+ MENU_OPTION_SUBSCRIBE_SERVICE);
        System.out.println("7. "+ MENU_OPTION_VIEW_SUBSCRIPTION);
        System.out.println("8. "+ MENU_OPTION_UPDATE_CONTACT);
        System.out.println("9. "+ MENU_OPTION_EXIT);
        System.out.print(USER_INPUT_MESSAGE);
    }

    public static final Integer getDiscountedMonth() {
        return DISCOUNTED_MONTH_COUNT;
    }

    public static final Double getDiscountPercent() {
        return APPLICABLE_DISCOUNT_PERCENT;
    }

    /** validate the email entered by user
     *  is valid or not
     *
     * @param email
     */
    public static final void validateEmail(String email) {
        if(null == email || email.length() < 5 || !Pattern.compile(EMAIL_REGEX).matcher(email).matches())
            throw new InvalidInputException(ERR_MSG_INVALID_EMAIL);
    }

    /** Only validates Indian Mobile Number including
        +91 and 0 if entered by user at first place
     */
    public static final void validateMobile(String mobile) {
        if(null == mobile || mobile.length() < 10 || !Pattern.compile(MOBILE_REGEX).matcher(mobile).matches())
            throw new InvalidInputException(ERR_MSG_INVALID_MOBILE);
    }

    public static final void validateSubscriptionName(String subscriptionName) {
        List<String> subscriptionNames = Arrays.asList("LearnEnglish", "LearnCooking");


        if(!subscriptionNames.contains(subscriptionName))
                throw new InvalidInputException(ERR_MSG_INVALID_INPUT);
    }

    /**
     * This method is used to valid the package subscription input
     * is matching the one that the service provider provides.
     *
     * Ex. G stands for Gold Package
     * @param pack
     */
    public static final void validatePack(String pack) {
        List<String> packageNames = Arrays.asList("G", "S");

        if(null == pack || "".equals(pack) || pack.length() != 1)
            throw new InvalidInputException(ERR_MSG_INVALID_INPUT);
        if(!packageNames.contains(pack))
            throw new InvalidInputException(ERR_MSG_INVALID_INPUT);
    }

    /**
     * This is used to validate whether the provided number from input
     * route is an valid integer or not.
     *
     * @param number
     * @return
     */
    public static final Integer validateInteger(String number) {
        try{
            Integer num = Integer.parseInt(number);
            if(num <= 0)
                throw new InvalidInputException(ERR_MSG_INVALID_INPUT);
            return  num;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(ERR_MSG_INVALID_INPUT);
        }
    }

    /**
     * This method checks for the channel list provided by user is matching with the
     * one that we have them as available channels or not.
     * @param channels
     */
    public static final List<String> validateChannelList(String channels) {
        List<String> channelList = Arrays.asList("ZEE", "SONY", "Star Plus", "Discovery", "Nat GEO");
        List<String> inputChannels = null;

        if(null == channels || "".equals(channels))
            throw new InvalidInputException(ERR_MSG_INVALID_INPUT);

        try {
            String[] str = channels.split(INPUT_SEPARATOR);
            inputChannels = new ArrayList<>();
            for(String s: str)
                inputChannels.add(s.trim());
        } catch(PatternSyntaxException e) {
            throw new InvalidInputException(ERR_MSG_INVALID_INPUT);
        }

        if(!channelList.containsAll(inputChannels))
            throw new InvalidInputException(ERR_MSG_INVALID_INPUT);

        return inputChannels;
    }

    /**
     * This method checks for the channel list provided by user is matching with the
     * one that we have them as available channels or not.
     * @param channels
     */
    public static final void validateChannelList(List<String> channels) {
        List<String> validchannelListInput = Arrays.asList("ZEE", "SONY", "Star Plus", "Discovery", "Nat GEO");

        if(null == channels || channels.isEmpty())
            throw new InvalidInputException(ERR_MSG_INVALID_INPUT);

        if(!validchannelListInput.containsAll(channels))
            throw new InvalidInputException(ERR_MSG_INVALID_INPUT);
    }

}
