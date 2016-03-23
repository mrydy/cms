package com.ydy.cms.dao;

import java.util.List;

import org.yandinyon.basic.dao.IBaseDao;

import com.ydy.cms.model.Role;

public interface IRoleDao extends IBaseDao<Role> {
	public List<Role> listRole();
	public void deleteRoleUsers(int rid);
}
