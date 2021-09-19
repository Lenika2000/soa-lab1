package servlets;

import dao.CityDao;
import model.*;
import model.typesForXml.Cities;
import model.typesForXml.JaxbCity;
import model.typesForXml.MetersAboveSeaLevel;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import validators.CityValidator;
import util.Jaxb;

import javax.persistence.EntityNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static util.ServletUtil.getBody;

@WebServlet("/")
public class CityTableBean extends HttpServlet {

    private CityDao cityDao = new CityDao();
    private Cities cities = new Cities();
    private CityValidator cityValidator = new CityValidator();
    private MetersAboveSeaLevel metersAboveSeaLevel = new MetersAboveSeaLevel();;

    public CityTableBean() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/showGetByIdForm":
                    showGetByIdForm(request, response);
                    break;
                case "/get":
                    getCityById(request, response);
                    break;
                case "/filter":
                    filterCities(request, response);
                    break;
                case "/filterByName":
                    filterCitiesByName(request, response);
                    break;
                case "/findCitiesMetersAboveSeaLevelMore":
                    filterCitiesByMetersAboveSeaLevel(request, response);
                    break;
                case "/getUniqueValuesOfMetersAboveSeaLevel":
                    getUniqueMetersAboveSeeLevel(request, response);
                    break;
                default:
                    getCities(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String body = getBody(request);
            JaxbCity city = Jaxb.fromStr(body, JaxbCity.class);
            cityValidator.validate(city);
            cityDao.addCity(city.toCity());
            response.setStatus(200);
        } catch (Exception e ) {
            PrintWriter out = response.getWriter();
            response.setStatus(400);
            out.print(e.getMessage());
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String body = getBody(request);
            JaxbCity cityData = Jaxb.fromStr(body, JaxbCity.class);
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
        } catch (Exception e ) {
            PrintWriter out = response.getWriter();
            response.setStatus(400);
            out.print(e.getMessage());
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        if (cityDao.deleteCity(id)) {
            response.setStatus(200);
        } else {
            throw new EntityNotFoundException("Cannot find human with id " + id);
        }
    }

    public void getCities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<City> cities = cityDao.getAllCities();
        request.setAttribute("cities", cities);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/main-page.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/city-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<City> existingCity = cityDao.getCityById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/city-form.jsp");
        request.setAttribute("city", existingCity.get());
        dispatcher.forward(request, response);
    }

    private void showGetByIdForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/get-by-id.jsp");
        dispatcher.forward(request, response);
    }

    private void getCityById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<City> city = cityDao.getCityById(id);
        if (city != null) {
            request.setAttribute("city", city);
        } else {
            request.setAttribute("msg", "Not found city with id=" + id);
        }
        showGetByIdForm(request, response);
    }

    private void filterCities(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> queryMap = request.getParameterMap();
        List<City> filteredCities = cityDao.getFilteredCities(queryMap);
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        cities.setCities(filteredCities);
        try (PrintWriter out = response.getWriter()) {
            Writer writer = new StringWriter();
            response.setStatus(200);
            Serializer serializer = new Persister();
            serializer.write(cities, writer);
            String xml = writer.toString();
            out.print(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filterCitiesByName(HttpServletRequest request, HttpServletResponse response) {
        filterCities(request, response);
    }

    private void filterCitiesByMetersAboveSeaLevel(HttpServletRequest request, HttpServletResponse response) {
        int metersAboveSeaLevel = Integer.parseInt(request.getParameter("metersAboveSeaLevel"));
        List<City> filteredCities = cityDao.findCitiesMetersAboveSeeLevelMore(metersAboveSeaLevel);
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        cities.setCities(filteredCities);
        try (PrintWriter out = response.getWriter()) {
            Writer writer = new StringWriter();
            response.setStatus(200);
            Serializer serializer = new Persister();
            serializer.write(cities, writer);
            String xml = writer.toString();
            out.print(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getUniqueMetersAboveSeeLevel(HttpServletRequest request, HttpServletResponse response) {
        List<Integer> uniqueMetersAboveSeeLevel = cityDao.getUniqueMetersAboveSeeLevel();
        metersAboveSeaLevel.setMeters(uniqueMetersAboveSeeLevel);
        try (PrintWriter out = response.getWriter()) {
            Writer writer = new StringWriter();
            response.setStatus(200);
            Serializer serializer = new Persister();
            serializer.write(metersAboveSeaLevel, writer);
            String xml = writer.toString();
            out.print(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
