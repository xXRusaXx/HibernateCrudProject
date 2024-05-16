package ru.homework.hibernate.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import ru.homework.hibernate.model.pojo.Employee;
import ru.homework.hibernate.model.pojo.EmployeeDetails;
import ru.homework.hibernate.model.pojo.Position;
import ru.homework.hibernate.model.pojo.Project;

import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Properties settings = new Properties();
            settings.load(HibernateUtil.class.getClassLoader().getResourceAsStream("application.properties"));

            settings.setProperty(Environment.DRIVER, settings.getProperty("spring.datasource.driver-class-name"));
            settings.setProperty(Environment.URL, settings.getProperty("spring.datasource.url"));
            settings.setProperty(Environment.USER, settings.getProperty("spring.datasource.username"));
            settings.setProperty(Environment.PASS, settings.getProperty("spring.datasource.password"));
            settings.setProperty(Environment.DIALECT, settings.getProperty("hibernate.dialect"));
            settings.setProperty(Environment.SHOW_SQL, settings.getProperty("hibernate.show-sql"));
            settings.setProperty(Environment.HBM2DDL_AUTO, settings.getProperty("hibernate.ddl-auto"));


            Configuration configuration = new Configuration();
            configuration.setProperties(settings);

            configuration.addAnnotatedClass(EmployeeDetails.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Position.class);
            configuration.addAnnotatedClass(Project.class);

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
