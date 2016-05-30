package org.piyush.dao;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DaoFactory {

	private static DaoFactory df;
	private CartItemDao ciDao = null;
	private CartDao cDao = null;
	private OrderDao oDao = null;
	static Log log = LogFactory.getLog(DaoFactory.class);
	String classname = this.getClass().getName();
	private DaoFactory(){
	}
	
	public static DaoFactory getInstance(){
		if (df == null)
			df = new DaoFactory();
		return df;
	}
	
	public CartItemDao getCartItemDao(){
		if (ciDao == null){
			Properties properties = new Properties() ;
			try{
				properties.load(this.getClass().getResourceAsStream("/application.properties"));		
				String className = properties.getProperty("dao.CartItemDaoName");
				if (className!=null){
					ciDao = (CartItemDao)Class.forName(className).newInstance();
					log.info("Using " + className + " to get CartItemInfo...");
				}else{
					log.info("property not found, using default implementation");
					System.out.println("property not found, using default implementation");
					ciDao = new CartItemDaoMemImpl();
				}
			}catch (Exception e){ 
				log.info(e.getMessage());
				e.printStackTrace();
				ciDao =  new CartItemDaoMemImpl();
				System.out.println("Exception, using default implementation");
				return ciDao;
			}
		}
		return ciDao;
	}
	
	public CartDao getCartDao(){
		if (cDao == null){
			Properties properties = new Properties() ;
			try{
				properties.load(this.getClass().getResourceAsStream("/application.properties"));		
				String className = properties.getProperty("dao.CartDaoName");
				if (className!=null){
					cDao = (CartDao)Class.forName(className).newInstance();
					log.info("Using " + className + " to get ProductInfo...");
				}else{
					log.info("property not found, using default implementation");
					System.out.println("property not found, using default implementation");
					cDao = new CartDaoMemImpl();
				}
			}catch (Exception e){ 
				log.info(e.getMessage());
				e.printStackTrace();
				cDao =  new CartDaoMemImpl();
				System.out.println("Exception, using default implementation");
				return cDao;
			}
		}
		return cDao;
	}
	
	public OrderDao getOrderDao(){
		if (oDao == null){
			Properties properties = new Properties() ;
			try{
				properties.load(this.getClass().getResourceAsStream("/application.properties"));		
				String className = properties.getProperty("dao.OrderDaoName");
				if (className!=null){
					oDao = (OrderDao)Class.forName(className).newInstance();
					log.info("Using " + className + " to get OrderInfo...");
				}else{
					log.info("property not found, using default implementation");
					System.out.println("property not found, using default implementation");
					oDao = new OrderDaoMemImpl();
				}
			}catch (Exception e){ 
				log.info(e.getMessage());
				e.printStackTrace();
				oDao =  new OrderDaoMemImpl();
				System.out.println("Exception, using default implementation");
				return oDao;
			}
		}
		return oDao;
	}
}

