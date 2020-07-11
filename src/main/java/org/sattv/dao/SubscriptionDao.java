package org.sattv.dao;

import org.sattv.beans.Subscription;

import java.util.List;

/**
 * This abstraction layer is responsible to
 * deal with the subscription if any d2h operator provides
 * Ex. -> LearnCooking
 *
 * @authod kapilb
 * @since 1.0.0.RELEASE
 */
public interface SubscriptionDao {
    /**
     * This method is responsible to fetch all the subscription
     * information into the system
     * @return List<Subscription>
     */
    List<Subscription> getAllSubscription();

    /**
     * This is used to fetch the subscription details
     * based on the subscription name provided as input
     * @param name
     * @return Subscirption
     */
    Subscription getSubscriptionByName(String name);
}
