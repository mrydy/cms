package org.yandinyon.basic.dao;

import java.util.List;
import java.util.Map;

import org.yandinyon.basic.model.Pager;
import org.yandinyon.basic.model.User;

public interface IUserDao extends IBaseDao<User> {

	List<User> list(String string, Object[] objects);

	List<User> list(String string, Object[] objects, Map<String, Object> alias);

	Pager<User> find(String string, Object[] objects);

	Pager<User> find(String string, Object[] objects, Map<String, Object> alias);

	List<User> listUserBySQL(String string, Object[] objects, Class<User> class1,
			boolean b);

	List<User> listUserBySQL(String string, Object[] objects,
			Map<String, Object> alias, Class<User> class1, boolean b);

	Pager<User> findUserBySQL(String string, Object[] objects, Class<User> class1,
			boolean b);

	Pager<User> findUserBySQL(String string, Object[] objects,
			Map<String, Object> alias, Class<User> class1, boolean b);

}
