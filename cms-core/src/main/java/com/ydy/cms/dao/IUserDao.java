package com.ydy.cms.dao;

import java.util.List;

import org.yandinyon.basic.dao.IBaseDao;
import org.yandinyon.basic.model.Pager;

import com.ydy.cms.model.Group;
import com.ydy.cms.model.Role;
import com.ydy.cms.model.RoleType;
import com.ydy.cms.model.User;
import com.ydy.cms.model.UserGroup;
import com.ydy.cms.model.UserRole;

public interface IUserDao extends IBaseDao<User> {
	/**
	 * 获取用户的所有角色信息
	 * @param id
	 * @return
	 */
	public List<Role> listUserRoles(int userId);
	
	/**
	 * 查询用户角色的id
	 * @param id
	 * @return
	 */
	public List<Integer> listUserRoleIds(int userId);
	
	/**
	 * 获取用户所有的组信息
	 * @param id
	 * @return
	 */
	public List<Group> listUserGroups(int userId);
	
	/**
	 * 获取用户组id
	 * @param id
	 * @return
	 */
	public List<Integer> listUserGroupIds(int userId);
	
	/**
	 * 根据用户和角色获取用户角色关联对象
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public UserRole loadUserRole(int userId,int roleId);
	
	/**
	 * 根据用户和组获取用户组关联对象
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public UserGroup loadUserGroup(int userId,int groupId);
	
	/**
	 * 根据用户名获取用户对象
	 * @param username
	 * @return
	 */
	public User loadByUsername(String username);
	
	/**
	 * 根据角色id获取用户对象
	 * @param roleId
	 * @return
	 */
	public List<User> listRoleUsers(int roleId);
	
	/**
	 * 根据角色类型获取用户对象
	 * @param roleType
	 * @return
	 */
	public List<User> listRoleUsers(RoleType roleType);
	
	/**
	 * 获取某个组中的用户对象
	 * @param groupId
	 * @return
	 */
	public List<User> listGroupUsers(int groupId);
	
	/**
	 * 添加用户角色对象
	 * @param user
	 * @param role
	 */
	public void addUserRole(User user,Role role);
	
	public void addUserGroup(User user,Group group);
	
	public void deleteUserRoles(int id);
	public void deleteUserGroups(int id);
	
	public Pager<User> findUser();
	
	/**
	 * 删除用户角色对象
	 * @param uid
	 * @param rid
	 */
	public void deleteUserRole(int uid,int rid);
	
	public void deleteUserGroup(int uid,int gid);
}



