package com.ydy.cms.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ydy.cms.dao.IGroupDao;
import com.ydy.cms.dao.IRoleDao;
import com.ydy.cms.dao.IUserDao;
import com.ydy.cms.model.CMSException;
import com.ydy.cms.model.Group;
import com.ydy.cms.model.Role;
import com.ydy.cms.model.User;

@Service
public class UserService implements IUserService {
	
	private IUserDao userDao;
	private IGroupDao groupDao;
	private IRoleDao roleDao;

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

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	@Inject
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public void add(User user, Integer[] rids, Integer[] gids) {
		User u = userDao.loadByUsername(user.getUsername());
		if(u!=null){
			throw new CMSException("添加的用户对象已经存在，不能添加");
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User user, Integer[] rids, Integer[] gids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStatus(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void findUser() {
		// TODO Auto-generated method stub

	}

	@Override
	public User load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> listUserRoles(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> listUserGroups(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
