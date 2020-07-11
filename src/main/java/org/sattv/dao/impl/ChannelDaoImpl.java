package org.sattv.dao.impl;

import org.sattv.beans.Channel;
import org.sattv.dao.ChannelDao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChannelDaoImpl implements ChannelDao {

    @Override
    public List<Channel> getAllChannels() {
        return populateChannelsFromDB();
    }

    @Override
    public List<Channel> getChannelsByChannelNames(List<String> channelNames) {
        return populateChannelsFromDB()
                .stream()
                .filter(channel -> channelNames
                        .stream()
                        .anyMatch(ch -> ch.equalsIgnoreCase(channel.getName()))
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Channel> getChannelsByPackageId(Integer packageId) {
        return populateChannelsByPackageIdFromDB(packageId);
    }

    /**
        This method is responsible to fetch all the channels from
        database and each Channel will be aware about its package Id
        along with the channel details like id, price
        TODO: fetch the originals from Database
     */
    private static List<Channel> populateChannelsFromDB() {
        Channel zee = new Channel(1, "ZEE", 10D, Arrays.asList(1, 2));
        Channel sony = new Channel(2, "SONY", 15D, Arrays.asList(1, 2));
        Channel starPlus = new Channel(3, "Star Plus", 20D, Arrays.asList(1, 2));
        Channel discovery = new Channel(4, "Discovery", 10D, Arrays.asList(2));
        Channel natGeo = new Channel(5, "Nat GEO", 20D, Arrays.asList(2));
        return Arrays.asList(zee, sony,starPlus, discovery, natGeo);
    }

    /*
        This method is responsible to fetch all the channels belongs
        to the package and each Channel will be aware about its package Id
        along with the channel details like id, price
     */
    private static List<Channel> populateChannelsByPackageIdFromDB(Integer packageId) {
        return populateChannelsFromDB()
                .stream()
                .filter(e -> e.getPackageId().contains(packageId))
                .collect(Collectors.toList());
    }
}
