package com.ydy.cms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.yandinyon.basic.dao.BaseDao;

import com.ydy.cms.model.Channel;

@Repository("channelDao")
public class ChannelDao extends BaseDao<Channel> implements IChannelDao {


	@Override
	public List<Channel> listByParent(Integer pid) {
		String hql = "select c from Channel c left join fetch c.parent cp where cp.id="+pid+" order by c.orders";
		if(pid==null||pid==0) hql = "select c from Channel c where c.parent is null order by c.orders";
		return this.list(hql);
	}

	@Override
	public int getMaxOrderByParent(Integer pid) {
		String hql = "select max(c.orders) from Channel c where c.parent.id="+pid;
		if(pid==null||pid==0) hql = "select max(c.orders) from Channel c where c.parent is null";
		Object obj = this.queryObject(hql);
		if(obj==null) return 0;
		return (Integer)obj;
	}

}
