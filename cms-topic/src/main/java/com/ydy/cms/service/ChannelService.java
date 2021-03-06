package com.ydy.cms.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.yandinyon.basic.model.SystemContext;

import com.ydy.cms.dao.IChannelDao;
import com.ydy.cms.model.CMSException;
import com.ydy.cms.model.Channel;
import com.ydy.cms.model.ChannelTree;

@Service("channelService")
public class ChannelService implements IChannelService {
	
	private IChannelDao channelDao;

	public IChannelDao getChannelDao() {
		return channelDao;
	}
	@Inject
	public void setChannelDao(IChannelDao channelDao) {
		this.channelDao = channelDao;
	}

	
	@Override
	public void add(Channel channel, Integer pid) {
		Integer orders = channelDao.getMaxOrderByParent(pid);
		if(pid!=null&&pid>0){
			Channel pc = channelDao.load(pid);
			if(pc==null) throw new CMSException("要添加的栏目的父类对象不正确");
			channel.setParent(pc);
		}
		channel.setOrders(orders+1);
		channelDao.add(channel);
	}

	@Override
	public void update(Channel channel) {
		channelDao.update(channel);
	}

	@Override
	public void delete(int id) {
		// 1、判断是否存在子栏目
		List<Channel> cs = channelDao.listByParent(id);
		if(cs!=null&&cs.size()>0) throw new CMSException("要删除的子栏目存在子栏目不能删除");
		// TODO 2、判断是否存在文章
		// TODO 3、删除和组的关联关系
		
		channelDao.delete(id);

	}

	@Override
	public void clearTopic(int id) {
		// TODO 实现了文章模块之后再考虑

	}

	@Override
	public Channel load(int id) {
		return channelDao.load(id);
	}

	@Override
	public List<Channel> listByParentId(Integer pid) {
		String sort = SystemContext.getSort();
		if(sort==null||"".equals(sort.trim())){
			SystemContext.setSort("orders");
			SystemContext.setOrder("asc");
		}
		return channelDao.listByParent(pid);
	}
	@Override
	public List<ChannelTree> generateTree() {
		return channelDao.generateTree();
	}
	@Override
	public List<ChannelTree> generateTreeByParent(Integer pid) {
		return channelDao.generateTreeByParent(pid);
	}

}
