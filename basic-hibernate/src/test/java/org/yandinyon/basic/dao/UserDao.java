package org.yandinyon.basic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.yandinyon.basic.model.Pager;
import org.yandinyon.basic.model.User;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {

	@Override
	public List<User> listUserBySQL(String string, Object[] objects,
			Class<User> class1, boolean b) {
		return super.listBySQL(string, objects, class1, b);
	}

	@Override
	public List<User> listUserBySQL(String string, Object[] objects,
			Map<String, Object> alias, Class<User> class1, boolean b) {
		return super.listBySQL(string, objects, alias, class1, b);
	}

	@Override
	public Pager<User> findUserBySQL(String string, Object[] objects,
			Class<User> class1, boolean b) {
		return super.findBySQL(string, objects, class1, b);
	}

	@Override
	public Pager<User> findUserBySQL(String string, Object[] objects,
			Map<String, Object> alias, Class<User> class1, boolean b) {
		return super.findBySQL(string, objects, alias, class1, b);
	}
	
}
