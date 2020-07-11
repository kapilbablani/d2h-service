package org.sattv.service;

import org.sattv.beans.Subscription;

import java.util.List;

/**
 * This service layer abstraction is responsible
 * for the susbcription operations
 *
 * @author kapilb
 * @since 1.0.0.RELEASE
 */
public interface SubscriptionService {
    List<Subscription> getAllSubscription();

    Subscription getSubscriptionByName(String name);
}
