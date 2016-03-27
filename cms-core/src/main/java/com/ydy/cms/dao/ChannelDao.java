package com.ydy.cms.dao;

import java.util.List;

import org.yandinyon.basic.dao.BaseDao;

import com.ydy.cms.model.Channel;

public class ChannelDao extends BaseDao<Channel> implements IChannelDao {


	@Override
	public List<Channel> listByParent(Integer pid) {
		String hql = "select c from Channel c where c.parent.id="+pid;
		if(pid==null) hql = "select c from Channel c where c.parent is null";
		return this.list(hql);
	}

	@Override
	public int getMaxOrderByParent(Integer pid) {
		String hql = "select max(c.orders) where c.parent.id="+pid;
		if(pid==null) hql = "select max(c.orders) from Channel c where c.parent is null";
		return (Integer)this.queryObject(hql);
	}

}
