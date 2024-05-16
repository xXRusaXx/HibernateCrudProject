package ru.homework.hibernate.repository.imp;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.homework.hibernate.config.HibernateUtil;
import ru.homework.hibernate.model.pojo.Position;
import ru.homework.hibernate.model.pojo.Project;
import ru.homework.hibernate.repository.IRepository;

import java.util.List;

@Repository
public class ProjectRepository  implements IRepository<Project> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Project getById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Project.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Project> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Project", Project.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Project create(Project project) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(project);
            transaction.commit();

            return session.get(Project.class, id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public Project update(Project project) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(project);
            transaction.commit();

            return session.get(Project.class, project.getId());
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
            Project project = session.get(Project.class, id);
            if (project != null) {
                session.delete(project);
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
