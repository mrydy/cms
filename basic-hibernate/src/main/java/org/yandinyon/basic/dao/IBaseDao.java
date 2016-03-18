package org.yandinyon.basic.dao;


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
	
	
	
}






