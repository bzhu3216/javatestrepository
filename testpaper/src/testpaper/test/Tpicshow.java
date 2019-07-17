package testpaper.test;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testpaper.entity.Picdata;
import testpaper.dao.impl.*;






public class Tpicshow {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void init() {
		// 加载 hibernate.cfg.xml
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure().build();
		try {
			// 根据 hibernate.cfg.xml 配置 ,初始化 SessionFactory
			sessionFactory = new MetadataSources(registry).buildMetadata()
					.buildSessionFactory();
			// 创建 session
			session = sessionFactory.openSession();
			// 通过session开始事务
			transaction = session.beginTransaction();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	/**
	 * HQL查询：简单查询
	 */
	@Test
	public void testHql_1() {
		// 编写HQL语句
		String hql = "from Picdata ac where ac.id=1";
		// 创建Query对象
		Query query = session.createQuery(hql);
		// 执行查询，获得结果
		Picdata aa=(Picdata) query.uniqueResult();
		 byte[] data=aa.getPic();
		System.out.println(aa.getQuestionid());
		
		
		
		
		
	}


/////////////////////////////////

	@After
	public void destroy() {
		// 提交事务
		transaction.commit();
		// 关闭session
		session.close();
		// 关闭sessionFactory
		sessionFactory.close();
	}



}
