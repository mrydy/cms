package com.ydy.cms.dao;

import org.springframework.stereotype.Repository;
import org.yandinyon.basic.dao.BaseDao;

import com.ydy.cms.model.Group;

@Repository("groupDao")
public class GroupDao extends BaseDao<Group> implements IGroupDao {


}
