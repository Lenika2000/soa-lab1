package dao;

import model.Human;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class HumanDao {
    public HumanDao() {
    }

    public void addHuman(Human human) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(human);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
