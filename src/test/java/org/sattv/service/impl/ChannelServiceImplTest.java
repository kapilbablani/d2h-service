package org.sattv.service.impl;

import static org.junit.Assert.*;
import org.junit.Test;
import org.sattv.beans.Channel;
import org.sattv.exception.InvalidInputException;
import org.sattv.service.ChannelService;

import java.util.Arrays;
import java.util.List;

public class ChannelServiceImplTest {

    private ChannelService channelService = new ChannelServiceImpl();

    @Test
    public void testA_itShouldGiveSuccess_getAllChannels() {
        //given
        int expectChannelCount = 5;
        //when
        List<Channel> channels = channelService.getAllChannels();
        //then
        assertEquals(expectChannelCount, channels.size());
        assertNotNull(channels);
        assertEquals("ZEE", channels.get(0).getName());
    }

    @Test
    public void testB_itShouldGiveSuccessMatchingObjectReturned_getAllChannels() {
        //given

        //when
        List<Channel> channels = channelService.getAllChannels();
        //then
        assertTrue(channels instanceof List);
    }

    @Test
    public void testC_itShouldGiveSuccess_getChannelsByChannelNames() {
        //given
        List<String> inputChannels = Arrays.asList("ZEE", "Discovery");

        //when
        List<Channel> channels = channelService.getChannelsByChannelNames(inputChannels);

        //then
        assertNotNull(channels);
        assertEquals(2, channels.size());
        assertEquals("ZEE", channels.get(0).getName());
    }

    @Test(expected = InvalidInputException.class)
    public void testD_itShouldGiveInvalidInputException_getChannelsByChannelNames() {
        //given

        //when
        channelService.getChannelsByChannelNames(null);
    }

    @Test(expected = InvalidInputException.class)
    public void testE_itShouldGiveExceptionIfAnyChannelIsAbsent_getChannelsByChannelNames() {
        //given
        List<String> inputChannels = Arrays.asList("ZEE", "Sahara TV");

        //when
        channelService.getChannelsByChannelNames(inputChannels);
    }


}
