package org.yandinyon.basic.dao;

import java.util.List;
import java.util.Map;

import org.yandinyon.basic.model.Pager;

/**
 * 公共的DAO处理对象，这个对象包含了Hibernate的所有基本操作以及对SQL的操作
 * @author ydy
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	/**
	 * 新增一个对象
	 * @param t
	 * @return
	 */
	public T add(T t);
	
	/**
	 * 根据id删除一个对象
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 更新对象
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * 根据id加载对象
	 * @param id
	 * @return
	 */
	public T load(int id);
	
	/**
	 * 不分页列表对象
	 * @param hql 查询对象的hql
	 * @param args 查询参数
	 * @return 一组不分页的列表对象
	 */
	public List<T> list(String hql,Object[] args);
	public List<T> list(String hql,Object arg);
	public List<T> list(String hql);
	/**
	 * 基于：别名和查询参数混合  的不分页列表对象
	 * @param hql
	 * @param args 
	 * @param alias 别名
	 * @return
	 */
	public List<T> list(String hql,Object[] args,Map<String, Object> alias);
	public List<T> listByAlias(String hql,Map<String, Object> alias);
	
	/**
	 * 分页列表对象
	 * @param hql 查询对象的hql
	 * @param args 查询参数
	 * @return 一组分页的列表对象
	 */
	public Pager<T> find(String hql,Object[] args);
	public Pager<T> find(String hql,Object arg);
	public Pager<T> find(String hql);
	/**
	 * 基于：别名和查询参数混合  的分页列表对象
	 * @param hql
	 * @param args 
	 * @param alias 别名
	 * @return
	 */
	public Pager<T> find(String hql,Object[] args,Map<String, Object> alias);
	public Pager<T> findByAlias(String hql,Map<String, Object> alias);
	
	/**
	 * 根据hql查询一组对象
	 * @param hql
	 * @param args
	 * @return
	 */
	public Object queryObject(String hql,Object[] args);
	public Object queryObject(String hql,Object arg);
	public Object queryObject(String hql);
	public Object queryObject(String hql,Object[] args,Map<String, Object> alias);
	public Object queryObjectByAlias(String hql,Map<String, Object> alias);
	
	/**
	 * 根据hql更新一个对象
	 * @param hql
	 * @param args
	 */
	public void updateByHql(String hql,Object[] args);
	public void updateByHql(String hql,Object arg);
	public void updateByHql(String hql);
	
	/**
	 * 根据SQL查询对象，不包含关联对象
	 * @param sql 查询的sql语句
	 * @param args 查询条件
	 * @param clz 查询的实体对象
	 * @param isEntity  该对象是否是Hibernate所管理的实体，如果不是需要使用setResultTransform查询
	 * @return 一组对象
	 */
	public <N extends Object>List<N> listBySQL(String sql,Object[] args,Class<?> clz,boolean isEntity);
	public <N extends Object>List<N> listBySQL(String sql,Object arg,Class<?> clz,boolean isEntity);
	public <N extends Object>List<N> listBySQL(String sql,Class<?> clz,boolean isEntity);
	public <N extends Object>List<N> listBySQL(String sql,Object[] args,Map<String, Object> alias,Class<?> clz,boolean isEntity);
	public <N extends Object>List<N> listByAliasSQL(String sql,Map<String, Object> alias,Class<?> clz,boolean isEntity);
	
	/**
	 * 根据SQL查询对象，不包含关联对象
	 * @param sql 查询的sql语句
	 * @param args 查询条件
	 * @param clz 查询的实体对象
	 * @param isEntity  该对象是否是Hibernate所管理的实体，如果不是需要使用setResultTransform查询
	 * @return 一组对象
	 */
	public <N extends Object>Pager<N> findBySQL(String sql,Object[] args,Class<?> clz,boolean isEntity);
	public <N extends Object>Pager<N> findBySQL(String sql,Object arg,Class<?> clz,boolean isEntity);
	public <N extends Object>Pager<N> findBySQL(String sql,Class<?> clz,boolean isEntity);
	public <N extends Object>Pager<N> findBySQL(String sql,Object[] args,Map<String, Object> alias,Class<?> clz,boolean isEntity);
	public <N extends Object>Pager<N> findByAliasSQL(String sql,Map<String, Object> alias,Class<?> clz,boolean isEntity);
	
	
}






