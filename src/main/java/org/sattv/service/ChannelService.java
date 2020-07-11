package org.sattv.service;

import org.sattv.beans.Channel;

import java.util.List;

/**
 * This service layer abstraction is responsible
 * for the channel operations
 *
 * @author kapilb
 * @since 1.0.0.RELEASE
 */
public interface ChannelService {
    List<Channel> getAllChannels();

    List<Channel> getChannelsByChannelNames(List<String> channelNames);
}
