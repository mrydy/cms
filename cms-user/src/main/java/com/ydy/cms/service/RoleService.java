package com.ydy.cms.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ydy.cms.dao.IRoleDao;
import com.ydy.cms.dao.IUserDao;
import com.ydy.cms.model.CMSException;
import com.ydy.cms.model.Role;
import com.ydy.cms.model.User;

@Service("roleService")
public class RoleService implements IRoleService {

	private IUserDao userDao;
	private IRoleDao roleDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}

	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	@Inject
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public void add(Role role) {
		roleDao.add(role);
	}

	@Override
	public void delete(int id) {
		List<User> users = userDao.listRoleUsers(id);
		if(users!=null&&users.size()>0) throw new CMSException("角色关联用户不为空，不能删除角色！！");
		roleDao.delete(id);
	}

	@Override
	public Role load(int id) {
		return roleDao.load(id);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public List<Role> listRole() {
		return roleDao.listRole();
	}

	@Override
	public void deleteRoleUsers(int rid) {
		roleDao.deleteRoleUsers(rid);
	}

}
