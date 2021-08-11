package dao;

import model.Coordinates;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class CoordinatesDao {

    public CoordinatesDao() {
    }

    public void addCoordinates(Coordinates coordinates) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(coordinates);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
