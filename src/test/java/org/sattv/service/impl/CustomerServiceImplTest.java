package org.sattv.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.sattv.beans.*;
import org.sattv.beans.Package;
import org.sattv.dao.ChannelDao;
import org.sattv.dao.impl.ChannelDaoImpl;
import org.sattv.exception.InvalidInputException;
import org.sattv.factory.CustomerBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.sattv.utility.PropertyUtil.*;


public class CustomerServiceImplTest {

    private CustomerServiceImpl customerService = new CustomerServiceImpl();
    private Customer customer = new CustomerBuilder()
                .setEmail("test@test.com")
                .setMobile("8978545215")
                .setBalance(INITIAL_BALANCE)
                .build();

    @Test
    public void testA_itShouldGiveInitialBalance_getBalance() {
        //given

        //when
        Double balance = customerService.getBalance(customer);

        //then
        assertEquals(INITIAL_BALANCE, balance);
    }

    @Test
    public void testB_itShouldGiveUpdatedBalance_getBalance() {
        //given
        Double newBalance = 500D;
        customer.setBalance(newBalance);

        //when
        Double balance = customerService.getBalance(customer);

        //then
        assertEquals(newBalance, balance);
    }

    @Test(expected = InvalidInputException.class)
    public void testC_itShouldThrowExceptionIfCustomerBalanceIsNull_getBalance() {
        //given
        Double newBalance = null;
        customer.setBalance(newBalance);

        //when
        customerService.getBalance(customer);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testD_itShouldThrowExceptionIfCustomerObjectIsNull_getBalance() {
        //given

        //when
        customerService.getBalance(null);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testE_itShouldThrowExceptionIfCustomerBalanceIsNegativeNumber_getBalance() {
        //given
        Double newBalance = -58D;
        customer.setBalance(newBalance);

        //when
        customerService.getBalance(customer);

        //then
        // covered as expected exception
    }

    @Test
    public void testF_itShouldGiveUpdatedBalOnRecharge_rechargeAccount() {
        //given
        String rechargeAmt = "150";
        Double newBal = INITIAL_BALANCE + 150D;

        //when
        Double updatedBal = customerService.rechargeAccount(customer, rechargeAmt);

        //then
        assertEquals(newBal, updatedBal);
    }

    @Test(expected = InvalidInputException.class)
    public void testG_itShouldThrowExceptionOnRechargeAmtLessThanMinAmt_rechargeAccount() {
        //given
        String rechargeAmt = "15D";

        //when
        customerService.rechargeAccount(customer, rechargeAmt);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testH_itShouldThrowExceptionOnRechargeAmountContainsAlphabet_rechargeAccount() {
        //given
        String rechargeAmt = "AAD";

        //when
        customerService.rechargeAccount(customer, rechargeAmt);

        //then
    }

    @Test
    public void testI_itShouldGiveSuccessFullyUpdatedEmail_updateContact() {
        //given
        String updatedEmail = "test@test.com";
        String updatedMobile = "9875485212";

        //when
        Customer updatedCustomer = customerService.updateContact(customer, updatedEmail, updatedMobile);

        //then
        assertEquals(updatedEmail, updatedCustomer.getEmail());
        assertEquals(updatedMobile, updatedCustomer.getMobile());
    }

    @Test(expected = InvalidInputException.class)
    public void testJ_itShouldGiveExceptionWhenBothHaveWrongFormat_updateContact() {
        //given
        String errorEmail = "test@tes#g.t.com";
        String errorMobile = "9875212";

        //when
        customerService.updateContact(customer, errorEmail, errorMobile);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testK_itShouldGiveExceptionWhenEmailHasWrongFormat_updateContact() {
        //given
        String errorEmail = "@test.com";
        String errorMobile = "9875212214";

        //when
        customerService.updateContact(customer, errorEmail, errorMobile);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testK_itShouldGiveExceptionWhenOnlyMobileHasWrongFormat_updateContact() {
        //given
        String errorEmail = "test@test.com";
        String errorMobile = "9872214";

        //when
        customerService.updateContact(customer, errorEmail, errorMobile);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testK_itShouldGiveExceptionWhenBothAreNull_updateContact() {
        //given

        //when
        customerService.updateContact(customer, null, null);

        //then
    }

    @Test
    public void testL_itShouldGiveSuccess_sendNotificationOverMail() {
        //given

        //when
        String successMsg = customerService.sendNotificationOverMail(customer);

        //then
        assertEquals(USER_MSG_MAIL_SENT, successMsg);
    }

    @Test(expected = InvalidInputException.class)
    public void testL_itShouldGiveExceptionWhenEmailNotRegistered_sendNotificationOverMail() {
        //given
        customer.setEmail(null);

        //when
        customerService.sendNotificationOverMail(customer);

        //then
    }

    @Test
    public void testM_itShouldGiveSuccess_sendNotificationOverSMS() {
        //given

        //when
        String successMsg = customerService.sendNotificationOverSMS(customer);

        //then
        assertEquals(USER_MSG_SMS_SENT, successMsg);
    }

    @Test(expected = InvalidInputException.class)
    public void testN_itShouldGiveExceptionWhenEmailNotRegistered_sendNotificationOverSMS() {
        //given
        customer.setMobile(null);

        //when
        customerService.sendNotificationOverSMS(customer);

        //then
    }

    @Test
    public void testO_itShouldGiveSuccess_subscribeSpecialService() {
        //given
        String subscriptionName = "LearnEnglish";
        customer.setBalance(500D);

        //when
        Subscription subscription = customerService.subscribeSpecialService(customer, subscriptionName);

        //then
        assertNotNull(subscription);
    }

    @Test(expected = InvalidInputException.class)
    public void testP_itShouldGiveExceptionWhenSubNameIsIncorrect_subscribeSpecialService() {
        //given
        String subscriptionName = "LearnJava";
        customer.setBalance(500D);

        //when
        customerService.subscribeSpecialService(customer, subscriptionName);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testQ_itShouldGiveExceptionWhenSubNameIsNull_subscribeSpecialService() {
        //given
        customer.setBalance(500D);

        //when
        customerService.subscribeSpecialService(customer, null);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testR_itShouldGiveExceptionWhenAccBalIsInsufficient_subscribeSpecialService() {
        //given
        String subscriptionName = "LearnEnglish";

        //when
        customerService.subscribeSpecialService(customer, subscriptionName);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testS_itShouldGiveExceptionWhenSubscriptionIsAlreadyInCurrentPlan_subscribeSpecialService() {
        //given
        String subscriptionName = "LearnEnglish";
        customer.setSubscriptions(Arrays.asList(new Subscription(1, "LearnEnglish Subscription", "LearnEnglish", 200D)));

        //when
        customerService.subscribeSpecialService(customer, subscriptionName);

        //then
    }

    @Test
    public void testT_itShouldGiveSuccessWithNoDiscountForTwoMonths_subscribePackage() {
        //given
        customer.setBalance(600D);
        String packNeeded = "G";
        String months = "2";

        //when
        Discount discount = customerService.subscribePackage(customer, packNeeded, months);

        //then
        assertNotNull(discount);
        assertTrue(discount instanceof Discount);
        assertEquals("200.0", discount.getAmount().toString());
        assertEquals("0.0", discount.getDiscountApplied().toString());
        assertEquals("200.0", discount.getPriceAfterDiscount().toString());
    }

    @Test
    public void testU_itShouldGiveSuccessWithDiscountForThreeOrMonths_subscribePackage() {
        //given
        customer.setBalance(INITIAL_BALANCE + 600D);
        String packNeeded = "G";
        String months = "3";

        //when
        Discount discount = customerService.subscribePackage(customer, packNeeded, months);

        //then
        assertNotNull(discount);
        assertTrue(discount instanceof Discount);
        assertEquals("300.0", discount.getAmount().toString());
        assertEquals("30.0", discount.getDiscountApplied().toString());
        assertEquals("270.0", discount.getPriceAfterDiscount().toString());
    }

    @Test(expected = InvalidInputException.class)
    public void testU_itShouldGiveExceptionWhenWrongPackSelected_subscribePackage() {
        //given
        customer.setBalance(INITIAL_BALANCE + 600D);
        String packNeeded = "F";
        String months = "3";

        //when
        customerService.subscribePackage(customer, packNeeded, months);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testV_itShouldGiveExceptionWhenWrongMonthValueSelected_subscribePackage() {
        //given
        customer.setBalance(INITIAL_BALANCE + 600D);
        String packNeeded = "S";
        String months = "-1";

        //when
        customerService.subscribePackage(customer, packNeeded, months);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testV_itShouldGiveExceptionWhenInsufficientBalanceEncountered_subscribePackage() {
        //given
        String packNeeded = "G";
        String months = "2";

        //when
        customerService.subscribePackage(customer, packNeeded, months);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testW_itShouldGiveExceptionWhenSamePackageIsAlreadySubscribed_subscribePackage() {
        //given
        String packNeeded = "S";
        String months = "2";
        customer.setPackageOpted(new Package(1, "Silver Pack", "S", 50D, new ArrayList<>()));

        //when
        customerService.subscribePackage(customer, packNeeded, months);

        //then
    }

    @Test
    public void testX_itShouldGiveSuccessIfChannelSubscribed_subscribeChannels() {
        //given
        String inputChannels = "ZEE,SONY";

        //when
        List<Channel> channelList = customerService.subscribeChannels(customer, inputChannels);

        //then
        assertNotNull(channelList);
        assertEquals(2, channelList.size());
        assertEquals("ZEE", channelList.get(0).getName());
        assertEquals("SONY", channelList.get(1).getName());
    }

    @Test(expected = InvalidInputException.class)
    public void testY_itShouldGiveExceptionIfInputChannelAlreadyInPackage_subscribeChannels() {
        //given
        String inputChannels = "Zee, Discovery";
        ChannelDao channelDao = new ChannelDaoImpl();
        customer.setPackageOpted(new Package(1, "Silver Pack", "S", 50D, channelDao.getChannelsByPackageId(1)));

        //when
        customerService.subscribeChannels(customer, inputChannels);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testZ_itShouldGiveExceptionIfInputChannelAlreadySubscribedInPast_subscribeChannels() {
        //given
        String inputChannels = "Zee, Discovery";
        customer.setChannels(Arrays.asList(new Channel(4, "Discovery", 10D, Arrays.asList(2))));

        //when
        customerService.subscribeChannels(customer, inputChannels);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testZ1_itShouldGiveExceptionIfInputChannelFormatIsInvalid_subscribeChannels() {
        //given
        String inputChannels = "Zee# Discovery";

        //when
        customerService.subscribeChannels(customer, inputChannels);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testZ2_itShouldGiveExceptionIfInputChannelIsNotAvailableInSystem_subscribeChannels() {
        //given
        String inputChannels = "Zee, Sahara";

        //when
        customerService.subscribeChannels(customer, inputChannels);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testZ3_itShouldGiveExceptionIfInputChannelIsNotAvailableInSystem_subscribeChannels() {
        //given
        String inputChannels = "Zee, Sahara";

        //when
        customerService.subscribeChannels(customer, inputChannels);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testZ4_itShouldGiveExceptionIfBalanceIsInsufficient_subscribeChannels() {
        //given
        String inputChannels = "Zee, Discovery";
        customer.setBalance(10D);

        //when
        customerService.subscribeChannels(customer, inputChannels);

        //then
    }



}
