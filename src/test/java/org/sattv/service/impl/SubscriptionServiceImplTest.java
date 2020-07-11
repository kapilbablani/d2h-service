package org.sattv.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.sattv.beans.Subscription;
import org.sattv.exception.InvalidInputException;
import org.sattv.service.SubscriptionService;

import java.util.List;

import static org.junit.Assert.*;

public class SubscriptionServiceImplTest {

    private SubscriptionService subscriptionService = new SubscriptionServiceImpl();

    @Test
    public void testA_itShouldGiveSuccess_getAllSubscription() {
        //given
        int expectSubscriptionCount = 2;
        //when
        List<Subscription> subscriptions = subscriptionService.getAllSubscription();
        //then
        assertEquals(expectSubscriptionCount, subscriptions.size());
        assertNotNull(subscriptions);
        assertEquals("LearnEnglish Subscription", subscriptions.get(0).getName());
    }

    @Test
    public void testB_itShouldGiveSuccessMatchingObjectReturned_getAllSubscription() {
        //given

        //when
        List<Subscription> subscriptions = subscriptionService.getAllSubscription();
        //then
        assertTrue(subscriptions instanceof List);
    }

    @Test
    public void testC_itShouldGiveSuccess_getSubscriptionByName() {
        //given
        String subscriptionName = "LearnCooking";

        //when
        Subscription subscription = subscriptionService.getSubscriptionByName(subscriptionName);

        //then
        assertNotNull(subscription);
        assertEquals(subscriptionName, subscription.getAbbreviation());
        assertEquals("LearnCooking Subscription", subscription.getName());
    }

    @Test(expected = InvalidInputException.class)
    public void testD_itShouldGiveErrorIfSubscriptionNameIsNull_getSubscriptionByName() {
        //given

        //when
        subscriptionService.getSubscriptionByName(null);

        //then
    }

    @Test(expected = InvalidInputException.class)
    public void testE_itShouldGiveErrorIfSubscriptionNameIsIncorrect_getSubscriptionByName() {
        //given
        //this will subscription abbreviation, used by UI/User
        String subscriptionName = "LearnJava";

        //when
        subscriptionService.getSubscriptionByName(subscriptionName);

        //then
    }
}
