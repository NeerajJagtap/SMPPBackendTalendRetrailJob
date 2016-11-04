package com.vuclip.smpp.talend.retrail;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.vuclip.smpp.talend.retrail.logger.LoggingBean;
import com.vuclip.smpp.talend.retrail.orm.dto.SmppData;

public class SMPPTalendRetrailStarter {

	private static final Logger smpptTalendRetrail = LogManager.getLogger("smpptTalendRetrail");

	private static final Logger smppTalendRetrailRec = LogManager.getLogger("smppTalendRetrailRec");

	public static void main(String args[]) {
		if (smpptTalendRetrail.isDebugEnabled()) {
			smpptTalendRetrail.debug("SMPPTalendRetrailStarter Started");
		}
		SessionFactory sessionFactory = null;
		Session session = null;
		Query query = null;
		String selectHQL = "Select s from SmppData s where s.talendResponse <> '200' and s.respStatus = '202' and reties < 5 and (dnMessage <> '' or dnMessage is not null))";
		try {
			sessionFactory = createSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			// Delete Recs before currentTime - 2hours
			query = session.createQuery(selectHQL);
			List<SmppData> list = query.list();
			for (SmppData smppData : list) {
				URL url = new URL(smppData.getDlrURL());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				int responseCode = conn.getResponseCode();
				// Update number of retries
				smppData.setTalendResponse(Integer.valueOf(responseCode).toString());
				smppData.setReties(smppData.getReties() + 1);
				session.update(smppData);
				if (smppTalendRetrailRec.isInfoEnabled()) {
					smppTalendRetrailRec.info(new LoggingBean(new Date(), smppData).getLogFormat());
				}
			}
			session.getTransaction().commit();
			System.out.println("Size : " + list.size());

			if (smpptTalendRetrail.isDebugEnabled()) {
				smpptTalendRetrail.debug("SMPPTalendRetrailStarter End with no of rows tried : " + list.size());
			}
		} catch (Exception e) {
			String message = e.getMessage();
			if (smpptTalendRetrail.isDebugEnabled()) {
				smpptTalendRetrail.debug("SMPPTalendRetrailStarter End with Exception : " + message);
			}
			if (smppTalendRetrailRec.isInfoEnabled()) {
				smppTalendRetrailRec.info("Exception while reading " + message);
			}
		} finally {
			if (null != session && null != sessionFactory) {
				if (session.isOpen()) {
					session.close();
				}
				sessionFactory.close();
			}
		}
	}

	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate-config.xml");
		final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		// To close this running thread
		configuration.setSessionFactoryObserver(new SessionFactoryObserver() {

			private static final long serialVersionUID = 1L;

			public void sessionFactoryCreated(SessionFactory factory) {
				// do nothing
			}

			public void sessionFactoryClosed(SessionFactory factory) {
				((StandardServiceRegistryImpl) serviceRegistry).destroy();
			}
		});
		return configuration.buildSessionFactory(serviceRegistry);
	}
}
