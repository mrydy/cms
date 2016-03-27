package com.ydy.cms.dao;

import java.util.List;

import org.yandinyon.basic.dao.IBaseDao;

import com.ydy.cms.model.Channel;

public interface IChannelDao extends IBaseDao<Channel>{

	public List<Channel> listByParent(Integer pid);
	public int getMaxOrderByParent(Integer pid);
	
}
