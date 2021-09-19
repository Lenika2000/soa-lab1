package service;

import dao.CityDao;
import model.City;
import model.typesForXml.Cities;
import model.typesForXml.JaxbCity;
import model.typesForXml.MetersAboveSeaLevel;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import util.Jaxb;
import validators.CityValidator;
import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static util.ServletUtil.getBody;

public class CityService {

    private final CityDao cityDao;
    private final Cities citiesList;
    private final CityValidator cityValidator;
    private final MetersAboveSeaLevel metersAboveSeaLevelList;

    public CityService() {
        this.cityDao = new CityDao();
        this.cityValidator = new CityValidator();
        this.citiesList = new Cities();
        this.metersAboveSeaLevelList = new MetersAboveSeaLevel();
    }

    public void createCity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String cityBody = getBody(request);
            JaxbCity city = Jaxb.fromStr(cityBody, JaxbCity.class);
            cityValidator.validate(city);
            cityDao.addCity(city.toCity());
            response.setStatus(200);
        } catch (ValidationException ex) {
            response.setStatus(400);
            response.getWriter().print(ex.getMessage());
        } catch (IllegalAccessException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public void updateCity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String cityBody = getBody(request);
            JaxbCity cityData = Jaxb.fromStr(cityBody, JaxbCity.class);
            cityValidator.validate(cityData);
            Optional<City> cityFromBD = cityDao.getCityById(cityData.getId());
            if (cityFromBD.isPresent()) {
                City updatingCity = cityFromBD.get();
                updatingCity.update(cityData);
                cityDao.updateCity(updatingCity);
                response.setStatus(200);
            } else {
                throw new EntityNotFoundException("Cannot update city");
            }
        } catch (ValidationException ex) {
            response.setStatus(400);
            response.getWriter().print(ex.getMessage());
        } catch (IllegalAccessException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public void deleteCity(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        if (cityDao.deleteCity(id)) {
            response.setStatus(200);
        } else {
            throw new EntityNotFoundException("Cannot find city with id " + id);
        }
    }

    public void getAllCities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<City> cities = cityDao.getAllCities();
        request.setAttribute("cities", cities);
        request.getRequestDispatcher("jsp/main-page.jsp").forward(request, response);
    }

    public void filterCities(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> queryMap = request.getParameterMap();
        List<City> filteredCities = cityDao.getFilteredCities(queryMap);
        sendCities(response, filteredCities);
    }

    public void filterCitiesByMetersAboveSeaLevel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int metersAboveSeaLevel = Integer.parseInt(request.getParameter("metersAboveSeaLevel"));
        List<City> filteredCities = cityDao.findCitiesMetersAboveSeeLevelMore(metersAboveSeaLevel);
        sendCities(response, filteredCities);
    }

    private void sendCities(HttpServletResponse response, List<City> filteredCities) throws Exception {
        response.setContentType("text/xml");
        citiesList.setCities(filteredCities);
        response.setStatus(200);
        Writer writer = new StringWriter();
        Serializer serializer = new Persister();
        serializer.write(citiesList, writer);
        String xml = writer.toString();
        response.getWriter().print(xml);
    }

    public void getUniqueMetersAboveSeeLevel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Integer> uniqueMetersAboveSeeLevel = cityDao.getUniqueMetersAboveSeeLevel();
        metersAboveSeaLevelList.setMeters(uniqueMetersAboveSeeLevel);
        response.setStatus(200);
        Writer writer = new StringWriter();
        response.setStatus(200);
        Serializer serializer = new Persister();
        serializer.write(metersAboveSeaLevelList, writer);
        String xml = writer.toString();
        response.getWriter().print(xml);
    }

    public void sort(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sortBy = request.getParameter("sortBy");
        String order = request.getParameter("order");
        List<City> sortedCities = cityDao.sort(sortBy, order);
        sendCities(response, sortedCities);
    }

}
