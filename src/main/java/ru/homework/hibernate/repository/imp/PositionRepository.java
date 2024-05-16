package ru.homework.hibernate.repository.imp;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.homework.hibernate.config.HibernateUtil;
import ru.homework.hibernate.model.pojo.Position;
import ru.homework.hibernate.repository.IRepository;

import java.util.List;

@Repository
public class PositionRepository implements IRepository<Position> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Position getById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Position.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Position> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Position", Position.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Position create(Position position) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(position);
            transaction.commit();

            return session.get(Position.class, id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public Position update(Position position) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(position);
            transaction.commit();

            return session.get(Position.class, position.getId());
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
            Position position = session.get(Position.class, id);
            if (position != null) {
                session.delete(position);
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
