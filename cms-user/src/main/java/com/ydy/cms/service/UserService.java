package com.ydy.cms.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.yandinyon.basic.model.Pager;

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

	private void addUserRole(User user,int rid){
		Role role = roleDao.load(rid);
		if(role==null) throw new CMSException("要添加的用户角色不存在");
		userDao.addUserRole(user, role);
	}
	private void addUserGroup(User user,int gid){
		Group group = groupDao.load(gid);
		if(group==null) throw new CMSException("要添加的用户组不存在");
		userDao.addUserGroup(user, group);
	}
	@Override
	public void add(User user, Integer[] rids, Integer[] gids) {
		User tu = userDao.loadByUsername(user.getUsername());
		if(tu!=null) throw new CMSException("添加的用户对象已经存在，不能添加");
		user.setCreateDate(new Date());
		userDao.add(user);
		//添加角色对象
		for(Integer rid :rids){
			//1检查角色对象是否存在，如果不存在就抛出异常
			//2检查用户角色对象是否存在，如果存在就不添加
			this.addUserRole(user, rid);
		}
		//添加用户组对象
		for(Integer gid : gids){
			this.addUserGroup(user, gid);
		}
	}

	@Override
	public void delete(int id) {
		//TODO  需要进行用户是否有文章的判断
		//1删除用户关联的角色对象
		userDao.deleteUserRoles(id);
		//2删除用户关联的组对象
		userDao.deleteUserGroups(id);
		//3删除用户对象
		userDao.delete(id);

	}

	@Override
	public void update(User user, Integer[] rids, Integer[] gids) {
		//1获取用户已经存在的组id和角色id
		List<Integer> erids = userDao.listUserRoleIds(user.getId());
		List<Integer> egids = userDao.listUserGroupIds(user.getId());
		//2判断，如果erids中不存在就要进行添加
		for(Integer rid :rids){
			if(!erids.contains(rid)){
				//添加角色
				addUserRole(user, rid);
			}
		}
		for(Integer gid :gids){
			if(!egids.contains(gid)){
				addUserGroup(user, gid);
			}
		}
		//3、进行删除
		for(Integer erid :erids){
			if(!ArrayUtils.contains(rids, erid)){
				userDao.deleteUserRole(user.getId(), erid);
			}
		}
		for(Integer egid :egids){
			if(!ArrayUtils.contains(gids, egid)){
				userDao.deleteUserGroup(user.getId(), egid);
			}
		}
	}

	@Override
	public void updateStatus(int id) {
		User u = userDao.load(id);
		if(u==null) throw new CMSException("修改状态的用户不存在");
		if(u.getStatus()==0) u.setStatus(1);
		else u.setStatus(0);
		userDao.update(u);

	}

	@Override
	public Pager<User> findUser() {
		return userDao.findUser();

	}

	@Override
	public User load(int id) {
		return userDao.load(id);
	}

	@Override
	public List<Role> listUserRoles(int id) {
		return userDao.listUserRoles(id);
	}

	@Override
	public List<Group> listUserGroups(int id) {
		return userDao.listUserGroups(id);
	}

	@Override
	public List<Integer> listUserRoleIds(int id) {
		return userDao.listUserRoleIds(id);
	}

	@Override
	public List<Integer> listUserGroupIds(int id) {
		return userDao.listUserGroupIds(id);
	}

	@Override
	public List<User> listGroupUsers(int gid) {
		return userDao.listGroupUsers(gid);
	}

	@Override
	public List<User> listRoleUsers(int rid) {
		return userDao.listRoleUsers(rid);
	}

}
