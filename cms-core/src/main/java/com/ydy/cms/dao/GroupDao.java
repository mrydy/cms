package com.ydy.cms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.yandinyon.basic.dao.BaseDao;
import org.yandinyon.basic.model.Pager;

import com.ydy.cms.model.Group;

@Repository("groupDao")
public class GroupDao extends BaseDao<Group> implements IGroupDao {

	@Override
	public List<Group> listGroup() {
		return this.list("from Group");
	}

	@Override
	public Pager<Group> findGroup() {
		return this.find("from Group");
	}

	@Override
	public void deleteGroupUsers(int gid) {
		this.updateByHql("delete UserGroup ug where ug.group.id=?", gid);
	}


}
