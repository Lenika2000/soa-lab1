package servlets;
import dao.CityDao;
import dao.CoordinatesDao;
import dao.HumanDao;
import model.*;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import util.DateBuilder;
import util.Jaxb;

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
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@WebServlet("/")
public class CityTableBean extends HttpServlet {

    private CityDao cityDao = new CityDao();
    private Cities cities = new Cities();
    private CoordinatesDao coordinatesDAO = new CoordinatesDao();
    private HumanDao humanDAO = new HumanDao();

    public CityTableBean() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    addCity(request, response);
                    break;
                case "/delete":
                    deleteCity(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCity(request, response);
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
                default:
                    getCities(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = request.getServletPath();
        System.out.println(action);
//        try {
//            switch (action) {
//                case "/new":
//                    showNewForm(request, response);
//                    break;
//                case "/insert":
//                    addCity(request, response);
//                    break;
//                case "/delete":
//                    deleteCity(request, response);
//                    break;
//                case "/edit":
//                    showEditForm(request, response);
//                    break;
//                case "/update":
//                    updateCity(request, response);
//                    break;
//                case "/showGetByIdForm":
//                    showGetByIdForm(request, response);
//                    break;
//                case "/get":
//                    getCityById(request, response);
//                    break;
//                case "/filter":
//                    filterCities(request, response);
//                    break;
//                case "/filterByName":
//                    filterCitiesByName(request, response);
//                    break;
//                default:
//                    getCities(request, response);
//                    break;
//            }
//        } catch (Exception ex) {
//            throw new ServletException(ex);
//        }
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
        City existingCity = cityDao.getCityById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/city-form.jsp");
        request.setAttribute("city", existingCity);
        dispatcher.forward(request, response);
    }

    private void showGetByIdForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/get-by-id.jsp");
        dispatcher.forward(request, response);
    }

    private void addCity(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = request.getParameter("name");
        Integer x = new Integer(request.getParameter("x"));
        Long y = new Long(request.getParameter("y"));
        float area = Float.parseFloat(request.getParameter("area"));
        int population = Integer.parseInt(request.getParameter("population"));
        int metersAboveSeaLevel = Integer.parseInt(request.getParameter("metersAboveSeaLevel"));
        Double timezone = new Double(request.getParameter("timezone"));
        Government government = Government.valueOf(request.getParameter("government"));
        StandardOfLiving standardOfLiving = StandardOfLiving.valueOf(request.getParameter("standardOfLiving"));
        double height = Double.parseDouble(request.getParameter("height"));
        LocalDateTime birthday = DateBuilder.getLocalDateFromDateAndTime(request.getParameter("birthday-date"), request.getParameter("birthday-time"));

        Coordinates newCoordinates = new Coordinates(x,y);
        Human governor = new Human(height, birthday);
        coordinatesDAO.addCoordinates(newCoordinates);
        humanDAO.addHuman(governor);
        City newCity = new City(name, newCoordinates, ZonedDateTime.now(), area, population, metersAboveSeaLevel, timezone, government, standardOfLiving, governor);
        cityDao.addCity(newCity);
        // если все прошло удачно
        getCities(request, response);
    }

    private void getCityById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        City city = cityDao.getCityById(id);
        if (city != null) {
            request.setAttribute("city", city);
        } else {
            request.setAttribute("msg", "Not found city with id="+id);
        }
        showGetByIdForm(request, response);
    }

    private void deleteCity(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        cityDao.deleteCity(id);
        getCities(request, response);
    }

    private void updateCity(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        // todo вынести отдельно, когда появятся проверки
        String name = request.getParameter("name");
        Integer x = new Integer(request.getParameter("x"));
        Long y = new Long(request.getParameter("y"));
        float area = Float.parseFloat(request.getParameter("area"));
        int population = Integer.parseInt(request.getParameter("population"));
        int metersAboveSeaLevel = Integer.parseInt(request.getParameter("metersAboveSeaLevel"));
        Double timezone = new Double(request.getParameter("timezone"));
        Government government = Government.valueOf(request.getParameter("government"));
        StandardOfLiving standardOfLiving = StandardOfLiving.valueOf(request.getParameter("standardOfLiving"));
        double height = Double.parseDouble(request.getParameter("height"));
        LocalDateTime birthday = DateBuilder.getLocalDateFromDateAndTime(request.getParameter("birthday-date"), request.getParameter("birthday-time"));
        Coordinates newCoordinates = new Coordinates(x,y);
        Human governor = new Human(height, birthday);
        coordinatesDAO.addCoordinates(newCoordinates);
        humanDAO.addHuman(governor);
        City updatedCity = new City(id, name, newCoordinates, ZonedDateTime.now(), area, population, metersAboveSeaLevel, timezone, government, standardOfLiving, governor);
        cityDao.updateCity(updatedCity);
        getCities(request, response);
    }

    private void filterCities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String[]> queryMap = request.getParameterMap();
        List<City> filteredCities = cityDao.getFilteredCities(queryMap);
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        cities.setCities(filteredCities);
        try (PrintWriter out = response.getWriter()) {
            Writer writer = new StringWriter();
            Serializer serializer = new Persister();
            serializer.write(cities, writer);
            String xml = writer.toString();
            out.print(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filterCitiesByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        filterCities(request, response);
    }
}
