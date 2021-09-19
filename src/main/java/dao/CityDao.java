package dao;

import model.City;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.DateBuilder;
import util.HibernateUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

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

    public Optional<City> getCityById(Long id) {
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
        return Optional.ofNullable(city);
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

    public boolean deleteCity(Long id) {
        Transaction transaction = null;
        boolean successful = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            City city = session.find(City.class, id);
            if (city != null) {
                session.delete(city);
                session.flush();
                successful = true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return successful;
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

    public List<City> getFilteredCities(Map<String, String[]> params) {
        Transaction transaction = null;
        List<City> cities = null;
        boolean isJoin = false;
        StringBuilder queryStr = new StringBuilder("from City c");
        if (params.containsKey("x1") || params.containsKey("y1") || params.containsKey("height1") || params.containsKey("start-birthday-date")) {
            isJoin = true;
            if (params.containsKey("x1") || params.containsKey("y1")) {
                queryStr.append(" join c.coordinates cor");
            }
            if (params.containsKey("height1") || params.containsKey("start-birthday-date")) {
                queryStr.append(" join c.governor g");
            }
            queryStr.append(" where");
            if (params.containsKey("x1")) {
                queryStr.append(" cor.x BETWEEN '").append(params.get("x1")[0]).append("' AND '").append(params.get("x2")[0]).append("' AND");
            }
            if (params.containsKey("y1")) {
                queryStr.append(" cor.y BETWEEN '").append(params.get("y1")[0]).append("' AND '").append(params.get("y2")[0]).append("' AND");
            }
            if (params.containsKey("height1")) {
                queryStr.append(" g.height BETWEEN '").append(params.get("height1")[0]).append("' AND '").append(params.get("height2")[0]).append("' AND");
            }
            if (params.containsKey("start-birthday-date")) {
                LocalDateTime startBirthdayDate = DateBuilder.getLocalDateFromDateAndTime(params.get("start-birthday-date")[0], params.get("start-birthday-time")[0]);
                LocalDateTime endBirthdayDate = DateBuilder.getLocalDateFromDateAndTime(params.get("end-birthday-date")[0], params.get("end-birthday-time")[0]);
                queryStr.append(" g.birthday BETWEEN '").append(Timestamp.valueOf(startBirthdayDate)).append("' AND '").append(Timestamp.valueOf(endBirthdayDate)).append("' AND");
            }
        }

        if (!queryStr.toString().contains("where")) {
            queryStr.append(" where");
        }
        if (params.containsKey("name")) {
            queryStr.append(" c.name LIKE '%").append(params.get("name")[0]).append("%' AND");
        }
        if (params.containsKey("start-creation-date")) {
            LocalDateTime startCreationDate = DateBuilder.getLocalDateFromDateAndTime(params.get("start-creation-date")[0], params.get("start-creation-time")[0]);
            LocalDateTime endCreationDate = DateBuilder.getLocalDateFromDateAndTime(params.get("end-creation-date")[0], params.get("end-creation-time")[0]);
            queryStr.append(" c.creationDate BETWEEN '").append(Timestamp.valueOf(startCreationDate)).append("' AND '").append(Timestamp.valueOf(endCreationDate)).append("' AND");
        }

        ArrayList<String> paramsNames = new ArrayList<>(Arrays.asList("area", "population", "metersAboveSeaLevel", "timezone"));
        for (String paramName : paramsNames) {
            if (params.containsKey(paramName + "1")) {
                queryStr.append(" c." + paramName + " BETWEEN '").append(params.get(paramName + "1")[0]).append("' AND '").append(params.get(paramName + "2")[0]).append("' AND");
            }
        }

        ArrayList<String> checkboxParamsNames = new ArrayList<>(Arrays.asList("government", "standardOfLiving"));
        for (String paramName : checkboxParamsNames) {
            if (params.containsKey(paramName)) {
                queryStr.append(" c." + paramName + " IN (");
                String[] checkboxValues = params.get(paramName);
                for (int i=0; i<checkboxValues.length; i++) {
                    queryStr.append("'" + checkboxValues[i]+ "', ");
                }
                queryStr = new StringBuilder(queryStr.substring(0, queryStr.length() - 2));
                queryStr.append(") AND");
            }
        }

        if (queryStr.toString().endsWith("AND")) {
            queryStr = new StringBuilder(queryStr.substring(0, queryStr.length() - 4));
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (isJoin) {
                List<Object[]> citiesListWithExtraColumns = session.createQuery(queryStr.toString()).getResultList();
                cities = new ArrayList<>();
                for (Object[] cityWithExtraColumns : citiesListWithExtraColumns) {
                    cities.add((City) cityWithExtraColumns[0]);
                }
            } else {
                cities = session.createQuery(queryStr.toString()).getResultList();
            }
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cities;
    }

    public List<City> findCitiesMetersAboveSeeLevelMore(int metersAboveSeaLevel) {
        Transaction transaction = null;
        List<City> cities = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<City> query = session.createQuery("from City c where c.metersAboveSeaLevel > :metersAboveSeaLevel");
            query.setParameter("metersAboveSeaLevel", metersAboveSeaLevel);
            cities = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return cities;
    }

    public List<Integer> getUniqueMetersAboveSeeLevel() {
        Transaction transaction = null;
        List<Integer> metersAboveSeeLevel = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Integer> query = session.createQuery("SELECT DISTINCT metersAboveSeaLevel FROM  City");
            metersAboveSeeLevel = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return metersAboveSeeLevel;
    }


}
