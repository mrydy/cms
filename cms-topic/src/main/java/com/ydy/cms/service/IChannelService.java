package com.ydy.cms.service;

import java.util.List;

import com.ydy.cms.model.Channel;

public interface IChannelService {

	public void add(Channel channel,Integer pid);
	public void update(Channel channel);
	public void delete(int id);
	
	public void clearTopic(int id);
	
	public Channel load(int id);
	public List<Channel> listByParentId(Integer pid);
}
