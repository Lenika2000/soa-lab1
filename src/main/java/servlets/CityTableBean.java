package servlets;

import dao.CityDao;
import dao.CoordinatesDao;
import dao.HumanDao;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/")
public class CityTableBean extends HttpServlet {

    private CityDao cityDao = new CityDao();
    private CoordinatesDao coordinatesDAO = new CoordinatesDao();
    private HumanDao humanDAO = new HumanDao();

    public CityTableBean() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.print(action);
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
//                case "/edit":
//                    showEditForm(request, response);
//                    break;
//                case "/update":
//                    updateUser(request, response);
//                    break;
                default:
                    getCities(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    public void getCities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<City> cities = cityDao.getAllCities();
        request.setAttribute("cities", cities);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cities-table.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("city-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
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
        String birthdayDateStr =  request.getParameter("birthday-date"); //1986-04-08
        String birthdayTimeStr=  request.getParameter("birthday-time"); //12:30
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime birthday = LocalDateTime.parse(birthdayDateStr + " "+ birthdayTimeStr, formatter);
        Coordinates newCoordinates = new Coordinates(x,y);
        Human governor = new Human(height, birthday);
        coordinatesDAO.addCoordinates(newCoordinates);
        humanDAO.addHuman(governor);
        City newCity = new City(name, newCoordinates, ZonedDateTime.now(), area, population, metersAboveSeaLevel, timezone, government, standardOfLiving, governor);
        cityDao.addCity(newCity);
        // если все прошло удачно
        getCities(request, response);
    }

    private void deleteCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        cityDao.deleteCity(id);
        getCities(request, response);
    }
}
