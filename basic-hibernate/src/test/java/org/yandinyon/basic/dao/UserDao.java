package org.yandinyon.basic.dao;

import org.springframework.stereotype.Repository;
import org.yandinyon.basic.model.User;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {
	
}
