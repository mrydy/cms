package com.ydy.cms.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.ydy.cms.model.Channel;
import com.ydy.cms.test.util.AbstractDbUnitTestCase;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestChannelDao extends AbstractDbUnitTestCase{
	@Inject
	private SessionFactory sessionFactory;
	@Inject
	private IChannelDao channelDao;
	
	@Before
	public void setUp() throws SQLException, IOException, DatabaseUnitException {
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
		this.backupAllTable();
		IDataSet ds = createDateSet("topic");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
	}
	
	@Test
	public void testListByParent() {
		List<Channel> cs = channelDao.listByParent(null);
		Assert.assertNotNull(cs);
		Assert.assertEquals(cs.size(),4);
	}

	@Test
	public void testGetMaxOrderByParent() {
		Integer max = channelDao.getMaxOrderByParent(1);
		Assert.assertEquals(max, new Integer(4));
		max = channelDao.getMaxOrderByParent(2);
		Assert.assertEquals(max, new Integer(0));
	}
	
	
	@After
	public void tearDown() throws Exception {
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession(); 
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
		this.resumeTable();
	}
}
