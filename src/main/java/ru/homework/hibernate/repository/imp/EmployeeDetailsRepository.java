package ru.homework.hibernate.repository.imp;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.homework.hibernate.config.HibernateUtil;
import ru.homework.hibernate.model.pojo.EmployeeDetails;
import ru.homework.hibernate.model.pojo.Project;
import ru.homework.hibernate.repository.IRepository;

import java.util.List;

@Repository
public class EmployeeDetailsRepository implements IRepository<EmployeeDetails> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public EmployeeDetails getById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(EmployeeDetails.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<EmployeeDetails> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from EmployeeDetails", EmployeeDetails.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public EmployeeDetails create(EmployeeDetails employeeDetails) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(employeeDetails);
            transaction.commit();

            return session.get(EmployeeDetails.class, id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public EmployeeDetails update(EmployeeDetails employeeDetails) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(employeeDetails);
            transaction.commit();

            return session.get(EmployeeDetails.class, employeeDetails.getId());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public boolean delete(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            EmployeeDetails employeeDetails = session.get(EmployeeDetails.class, id);
            if (employeeDetails != null) {
                session.delete(employeeDetails);
            }
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

            return false;
        }
    }
}
