package com.ydy.cms.dao;

import org.springframework.stereotype.Repository;
import org.yandinyon.basic.dao.BaseDao;

import com.ydy.cms.model.Role;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {

	

}
