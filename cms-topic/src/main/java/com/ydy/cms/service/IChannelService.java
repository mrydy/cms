package com.ydy.cms.service;

import java.util.List;

import com.ydy.cms.model.Channel;
import com.ydy.cms.model.ChannelTree;

public interface IChannelService {

	public void add(Channel channel,Integer pid);
	public void update(Channel channel);
	public void delete(int id);
	
	public void clearTopic(int id);
	
	public Channel load(int id);
	public List<Channel> listByParentId(Integer pid);
	
	
	public List<ChannelTree> generateTree();
	public List<ChannelTree> generateTreeByParent(Integer pid);
}
