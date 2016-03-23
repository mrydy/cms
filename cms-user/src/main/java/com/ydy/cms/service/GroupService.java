package com.ydy.cms.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.yandinyon.basic.model.Pager;

import com.ydy.cms.dao.IGroupDao;
import com.ydy.cms.dao.IUserDao;
import com.ydy.cms.model.CMSException;
import com.ydy.cms.model.Group;
import com.ydy.cms.model.User;

@Service("groupService")
public class GroupService implements IGroupService {
	
	private IUserDao userDao;
	private IGroupDao groupDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IGroupDao getGroupDao() {
		return groupDao;
	}

	@Inject
	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void add(Group group) {
		groupDao.add(group);
	}

	@Override
	public void delete(int id) {
		List<User> users = userDao.listGroupUsers(id);
		if(users!=null&&users.size()>0) throw new CMSException("用户组中用户不为空，不能删除对象");
		groupDao.delete(id);
	}

	@Override
	public Group load(int id) {
		return groupDao.load(id);
	}

	@Override
	public void update(Group group) {
		groupDao.update(group);
	}

	@Override
	public List<Group> listGroup() {
		return groupDao.listGroup();
	}

	@Override
	public Pager<Group> findGroup() {
		return groupDao.findGroup();
	}

	@Override
	public void deleteGroupUsers(int gid) {
		groupDao.deleteGroupUsers(gid);
	}

}
