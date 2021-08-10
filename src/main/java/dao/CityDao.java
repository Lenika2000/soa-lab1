package dao;

import model.City;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;

public class CityDao {

    public CityDao() {
    }

    public List<City> getAllCities() {
        Transaction transaction = null;
        List<City> cities = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
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

//    public City getCityById(int id) {
//        try {
//            userTransaction.begin();
//            return em.find(id);
//            userTransaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

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

//    public City updateCity(City city) {
//        User existingUser= usersMap.get(user.getId());
//        if (existingUser != null) {
//            existingUser.setName(user.getName());
//            existingUser.setAge(user.getAge());
//        } else {
//            usersMap.put(user.getId(), user);
//        }
//        return usersMap.get(user.getId());
//    }
//
//    public City deleteCity(int id) {
//        User userResponse = usersMap.remove(id);
//        return userResponse;
//    }

}
