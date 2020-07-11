package org.sattv.dao.impl;

import org.sattv.beans.Subscription;
import org.sattv.dao.SubscriptionDao;

import java.util.Arrays;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {
    @Override
    public List<Subscription> getAllSubscription() {
        return getAllSubscriptionFromDB();
    }

    @Override
    public Subscription getSubscriptionByName(String name) {
        return getAllSubscriptionFromDB()
                .stream()
                .filter(e -> e.getAbbreviation().equalsIgnoreCase(name))
                .findFirst()
                .get();
    }

    /**
     *  This method is used to fetch all the subscription offered
     *  from the database
     *  TODO: fetch the originals from Database
     *  @return List<Subscription>
     */
    private List<Subscription> getAllSubscriptionFromDB() {
        Subscription english = new Subscription(1, "LearnEnglish Subscription", "LearnEnglish", 200D);
        Subscription cooking = new Subscription(2, "LearnCooking Subscription", "LearnCooking",100D);
        return Arrays.asList(english, cooking);
    }
}
