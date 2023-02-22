package utils;

import domain.Manufacture;
import domain.Phone;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;


public class HibernateUtils {
	//	muốn hằng số tạo ra 1 lần dùng chung static final
	private static final SessionFactory FACTORY;//tạo getFactory
	//khối tĩnh này chạy 1 lần
	static {
		Configuration conf=new Configuration();
		conf.configure("hibernate.cfg.xml");
		conf.addAnnotatedClass(Phone.class);
		conf.addAnnotatedClass(Manufacture.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
		FACTORY =conf.buildSessionFactory(registry);
	}
	public static SessionFactory getFactory() {
		return FACTORY;
	}

}
