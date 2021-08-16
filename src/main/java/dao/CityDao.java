package dao;

import model.City;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;
import java.util.Map;

public class CityDao {

    public CityDao() {
    }

    public List<City> getAllCities() {
        Transaction transaction = null;
        List<City> cities = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            cities = session.createQuery("from City").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cities;
    }

    public City getCityById(Long id) {
        Transaction transaction = null;
        City city = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            city = session.get(City.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return city;
    }

    public void addCity(City city) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCity(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            City city = session.find(City.class, id);
            if (city != null) {
                session.delete(city);
                session.flush();
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateCity(City city) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<City> getFilteredCities(Map<String,String[]> params) {
        Transaction transaction = null;
        List<City> cities = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            String query = "from City c";
//            if (!params.isEmpty()) {
//                query += " where ";
//            }
//            params.forEach((k, v) -> {
//                query WHERE column_name BETWEEN value1 AND value2;
//            });
//
//            Query query = session.createQuery("from City c where ");
//            query.setParameter("id", id);
//            cities = session.createQuery("from City").getResultList();
//            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cities;
    }

}
