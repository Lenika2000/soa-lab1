package servlets;

import dao.CityDao;
import model.*;
import service.CityService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet("/")
public class CitiesServlet extends HttpServlet {

    private final CityDao cityDao;
    private CityService cityService;

    public CitiesServlet() {
        this.cityDao = new CityDao();
        this.cityService = new CityService();
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
        cityService.createCity(request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        cityService.updateCity(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        cityService.deleteCity(request, response);
    }

    public void getCities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       cityService.getAllCities(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("jsp/city-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<City> existingCity = cityDao.getCityById(id);
        request.setAttribute("city", existingCity.get());
        request.getRequestDispatcher("jsp/city-form.jsp").forward(request, response);
    }

    private void showGetByIdForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("jsp/get-by-id.jsp").forward(request, response);
    }

    private void getCityById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<City> city = cityDao.getCityById(id);
        if (city.isPresent()) {
            request.setAttribute("city", city.get());
        } else {
            request.setAttribute("msg", "Not found city with id=" + id);
        }
        showGetByIdForm(request, response);
    }

    private void filterCities(HttpServletRequest request, HttpServletResponse response) throws Exception {
        cityService.filterCities(request, response);
    }

    private void filterCitiesByName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        filterCities(request, response);
    }

    private void filterCitiesByMetersAboveSeaLevel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        cityService.filterCitiesByMetersAboveSeaLevel(request, response);
    }

    private void getUniqueMetersAboveSeeLevel(HttpServletRequest request, HttpServletResponse response) throws Exception {
       cityService.getUniqueMetersAboveSeeLevel(request, response);
    }
}
