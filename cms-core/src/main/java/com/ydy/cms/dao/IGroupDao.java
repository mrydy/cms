package com.ydy.cms.dao;

import java.util.List;

import org.yandinyon.basic.dao.IBaseDao;
import org.yandinyon.basic.model.Pager;

import com.ydy.cms.model.Group;

public interface IGroupDao extends IBaseDao<Group>{
	public List<Group> listGroup();
	public Pager<Group> findGroup();
	public void deleteGroupUsers(int gid);
}
