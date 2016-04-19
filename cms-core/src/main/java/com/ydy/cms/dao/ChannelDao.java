package com.ydy.cms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.yandinyon.basic.dao.BaseDao;

import com.ydy.cms.model.Channel;
import com.ydy.cms.model.ChannelTree;

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

	@Override
	public List<ChannelTree> generateTree() {
		String sql = "select id,name,pid from t_channel";
		List<ChannelTree> cts = this.listBySQL(sql, ChannelTree.class, false);
		cts.add(new ChannelTree(0,"系统栏目管理",-1));
		for(ChannelTree ct:cts){
			if(ct.getPid()==null){
				ct.setPid(0);
			}
		}
		return cts;
	}

	@Override
	public List<ChannelTree> generateTreeByParent(Integer pid) {
		if(pid==null||pid==0){
			return this.listBySQL("select id,name,pid from t_channel where pid is null", ChannelTree.class, false);
		}else{
			return this.listBySQL("select id,name,pid from t_channel where pid="+pid, ChannelTree.class, false);
		}
	}

}
