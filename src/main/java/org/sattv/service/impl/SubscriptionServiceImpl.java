package org.sattv.service.impl;

import org.sattv.beans.Subscription;
import org.sattv.dao.SubscriptionDao;
import org.sattv.dao.impl.SubscriptionDaoImpl;
import org.sattv.service.SubscriptionService;

import static org.sattv.utility.PropertyUtil.validateSubscriptionName;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionDao subscriptionDao = new SubscriptionDaoImpl();

    @Override
    public List<Subscription> getAllSubscription() {
        return subscriptionDao.getAllSubscription();
    }

    @Override
    public Subscription getSubscriptionByName(String name) {
        validateSubscriptionName(name);
        return subscriptionDao.getSubscriptionByName(name);
    }
}
