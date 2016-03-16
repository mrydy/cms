/**
 * 
 */
package org.yandinyon.basic.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.yandinyon.basic.model.Pager;
import org.yandinyon.basic.model.SystemContext;

/**
 * @author ydy
 *
 */
@SuppressWarnings("unchecked")
public class BaseDao<T> implements IBaseDao<T> {
	
	
	private SessionFactory sessionFactory;
	
	/**
	 * 创建一个Class的对象来获取泛型的class
	 */
	private Class<T> clz;
	public Class<T> getClz(){
		if(clz==null){
			//获取泛型的Class对象
			clz=((Class<T>)(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Inject
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession(){
		return sessionFactory.openSession();
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public T add(T t) {
		getSession().save(t);
		return t;
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#delete(int)
	 */
	@Override
	public void delete(int id) {
		getSession().delete(this.load(id));

	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public void update(T t) {
		getSession().update(t);

	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#load(int)
	 */
	
	@Override
	public T load(int id) {
		return (T)getSession().load(getClz(), id);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#list(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<T> list(String hql, Object[] args) {
		return this.list(hql, args, null);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#list(java.lang.String, java.lang.Object)
	 */
	@Override
	public List<T> list(String hql, Object arg) {
		return this.list(hql, new Object[]{arg});
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#list(java.lang.String)
	 */
	@Override
	public List<T> list(String hql) {
		return this.list(hql, null);
	}

	private String initSort(String hql){
		String sort = SystemContext.getSort();
		String order = SystemContext.getOrder();
		if(sort!=null&&!"".equals(sort)){
			hql += " order by " +sort;
			if(!"desc".equals(order)) hql += " asc";
			else hql += " desc";
		}
		return hql;
	}
	
	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query query,Map<String, Object> alias){
		if(alias != null){//先检查基于别名的条件，不然会报错
			Set<String> keys = alias.keySet();
			for(String key : keys){
				Object val = alias.get(key);
				if(val instanceof Collection) query.setParameterList(key, (Collection)val);//查询条件是列表
				else query.setParameter(key, val);
			}
		}
	}
	
	private void setParameter(Query query,Object[] args){
		if(args!=null&&args.length>0){
			int index =0;
			for(Object arg : args){
				query.setParameter(index++, arg);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#list(java.lang.String, java.lang.Object[], java.util.Map)
	 */
	@Override
	public List<T> list(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);             //初始化排序
		Query query  = getSession().createQuery(hql);
		setAliasParameter(query, alias); //设置别名参数
		setParameter(query, args);       //设置普通参数
		return query.list();
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#list(java.lang.String, java.util.Map)
	 */
	@Override
	public List<T> listByAlias(String hql, Map<String, Object> alias) {
		return this.list(hql, null, alias);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#find(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Pager<T> find(String hql, Object[] args) {
		return this.find(hql, args, null);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#find(java.lang.String, java.lang.Object)
	 */
	@Override
	public Pager<T> find(String hql, Object arg) {
		return this.find(hql, new Object[]{arg});
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#find(java.lang.String)
	 */
	@Override
	public Pager<T> find(String hql) {
		return this.find(hql, null);
	}

	@SuppressWarnings("rawtypes")
	private void setPagers(Query query ,Pager pages){
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset==null||pageOffset<0) pageOffset =0;
		if(pageSize == null || pageSize<0) pageSize =15;
		pages.setOffset(pageOffset);
		pages.setSize(pageSize);
		query.setFirstResult(pageOffset).setMaxResults(pageSize);
	}
	
	private String getCountHql(String hql,boolean isHql){
		String end = hql.substring(hql.indexOf("from"));
		String count = "select count(*) "+end;
		if(isHql) count.replaceAll("fetch", "");
		return count;
	}
	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#find(java.lang.String, java.lang.Object[], java.util.Map)
	 */
	@Override
	public Pager<T> find(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);     //初始化排序
		String countSql = getCountHql(hql,true);
		Query cquery = getSession().createQuery(countSql);
		Query query = getSession().createQuery(hql);
		//设置别名参数
		setAliasParameter(query, alias);
		setAliasParameter(cquery, alias);
		//设置参数
		setParameter(query, args);
		setParameter(cquery, args);
		Pager<T> pages = new Pager<T>();
		setPagers(query,pages);       
		List<T> datas = query.list();
		pages.setDatas(datas);
		long total = (Long)cquery.uniqueResult();
		pages.setTotal(total);;
		return pages;
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#find(java.lang.String, java.util.Map)
	 */
	@Override
	public Pager<T> findByAlias(String hql, Map<String, Object> alias) {
		return this.find(hql, null, alias);
	}

	
	@Override
	public Object queryObject(String hql, Object[] args,Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.uniqueResult();
	}
	@Override
	public Object queryObjectByAlias(String hql, Map<String, Object> alias) {
		return this.queryObject(hql, null, alias);
	}
	
	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#queryObject(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Object queryObject(String hql, Object[] args) {
		return this.queryObject(hql, args, null);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#queryObject(java.lang.String, java.lang.Object)
	 */
	@Override
	public Object queryObject(String hql, Object arg) {
		return this.queryObject(hql, new Object[]{arg});
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#queryObject(java.lang.String)
	 */
	@Override
	public Object queryObject(String hql) {
		return this.queryObject(hql, null);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#updateByHql(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void updateByHql(String hql, Object[] args) {
		Query query = getSession().createQuery(hql);
		setParameter(query, args);
		query.executeUpdate();

	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#updateByHql(java.lang.String, java.lang.Object)
	 */
	@Override
	public void updateByHql(String hql, Object arg) {
		this.updateByHql(hql, new Object[]{arg});
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#updateByHql(java.lang.String)
	 */
	@Override
	public void updateByHql(String hql) {
		this.updateByHql(hql, null);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#listBySQL(java.lang.String, java.lang.Object[], java.lang.Class, boolean)
	 */
	@Override
	public List<Object> listBySQL(String sql, Object[] args, Class<Object> clz,
			boolean isEntity) {
		return this.listBySQL(sql, args, null, clz, isEntity);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#listBySQL(java.lang.String, java.lang.Object, java.lang.Class, boolean)
	 */
	@Override
	public List<Object> listBySQL(String sql, Object arg, Class<Object> clz,
			boolean isEntity) {
		return this.listBySQL(sql, new Object[]{arg}, clz, isEntity);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#listBySQL(java.lang.String, java.lang.Class, boolean)
	 */
	@Override
	public List<Object> listBySQL(String sql, Class<Object> clz, boolean isEntity) {
		return this.listBySQL(sql, null, clz, isEntity);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#listBySQL(java.lang.String, java.lang.Object[], java.util.Map, java.lang.Class, boolean)
	 */
	@Override
	public List<Object> listBySQL(String sql, Object[] args,
			Map<String, Object> alias, Class<Object> clz, boolean isEntity) {
		sql = initSort(sql);
		SQLQuery sq = getSession().createSQLQuery(sql);
		setAliasParameter(sq, alias);
		setParameter(sq, args);
		if(isEntity) sq.addEntity(clz);
		else sq.setResultTransformer(Transformers.aliasToBean(clz));
		return sq.list();
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#listBySQL(java.lang.String, java.util.Map, java.lang.Class, boolean)
	 */
	@Override
	public List<Object> listByAliasSQL(String sql, Map<String, Object> alias,
			Class<Object> clz, boolean isEntity) {
		return this.listBySQL(sql, null, alias, clz, isEntity);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#findBySQL(java.lang.String, java.lang.Object[], java.lang.Class, boolean)
	 */
	@Override
	public Pager<Object> findBySQL(String sql, Object[] args, Class<Object> clz,
			boolean isEntity) {
		return this.findBySQL(sql, args, null, clz, isEntity);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#findBySQL(java.lang.String, java.lang.Object, java.lang.Class, boolean)
	 */
	@Override
	public Pager<Object> findBySQL(String sql, Object arg, Class<Object> clz,
			boolean isEntity) {
		return this.findBySQL(sql, new Object[]{arg}, clz, isEntity);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#findBySQL(java.lang.String, java.lang.Class, boolean)
	 */
	@Override
	public Pager<Object> findBySQL(String sql, Class<Object> clz, boolean isEntity) {
		return this.findBySQL(sql, null, clz, isEntity);
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#findBySQL(java.lang.String, java.lang.Object[], java.util.Map, java.lang.Class, boolean)
	 */
	@Override
	public Pager<Object> findBySQL(String sql, Object[] args,
			Map<String, Object> alias, Class<Object> clz, boolean isEntity) {
		String countSql = getCountHql(sql,false);
		countSql = initSort(countSql);
		sql = initSort(sql);
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		SQLQuery countQuery = getSession().createSQLQuery(countSql);
		setAliasParameter(sqlQuery, alias);
		setAliasParameter(countQuery, alias);
		setParameter(sqlQuery, args);
		setParameter(countQuery, args);
		Pager<Object> pages = new Pager<Object>();
		setPagers(sqlQuery, pages);
		if(isEntity){
			sqlQuery.addEntity(clz);
		}else{
			sqlQuery.setResultTransformer(Transformers.aliasToBean(clz));
		}
		List<Object> datas = sqlQuery.list();
		pages.setDatas(datas);
		long total = (Long)countQuery.uniqueResult();
		pages.setTotal(total);
		return pages;
	}

	/* (non-Javadoc)
	 * @see org.yandinyon.basic.dao.IBaseDao#findBySQL(java.lang.String, java.util.Map, java.lang.Class, boolean)
	 */
	@Override
	public Pager<Object> findByAliasSQL(String sql, Map<String, Object> alias,
			Class<Object> clz, boolean isEntity) {
		return this.findBySQL(sql, null, alias, clz, isEntity);
	}

}
