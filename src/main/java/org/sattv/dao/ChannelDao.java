package org.sattv.dao;

import org.sattv.beans.Channel;

import java.util.List;

/**
 * This layer of abstraction will be used for the channel providers
 * to have this basic methods to be implemented s to make the
 * channel principle to be followed
 *
 * @author kapilb
 * @version 1.0.0.RELEASE
 */
public interface ChannelDao {
    /**
     * It is responsible to fetch all the available channels
     * @return List<Channel>
     */
    List<Channel> getAllChannels();

    /**
     * This is responsible to return the channel details
     * based on the channel names passed as List object
     * @param channelNames
     * @return List<Channel>
     */
    List<Channel> getChannelsByChannelNames(List<String> channelNames);

    /**
     * This method is responsible to fetch the channel list available as part of
     * the package provided as input by the user
     * @param packageId
     * @return List<Channel>
     */
    List<Channel> getChannelsByPackageId(Integer packageId);

}
