package org.sattv.service.impl;

import org.sattv.beans.Channel;
import org.sattv.dao.ChannelDao;
import org.sattv.dao.impl.ChannelDaoImpl;
import org.sattv.service.ChannelService;

import java.util.List;

import static org.sattv.utility.PropertyUtil.validateChannelList;

public class ChannelServiceImpl implements ChannelService {

    private ChannelDao channelDao = new ChannelDaoImpl();

    @Override
    public List<Channel> getAllChannels() {
        return channelDao.getAllChannels();
    }

    @Override
    public List<Channel> getChannelsByChannelNames(List<String> channelNames) {
        validateChannelList(channelNames);
        return channelDao.getChannelsByChannelNames(channelNames);
    }
}
