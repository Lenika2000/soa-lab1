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
        System.out.print(action);
        try {
            switch (action) {
                case "/cities":
                    if (request.getParameterMap().isEmpty()) {
                        getCities(request, response);
                    } else {
                        getCityById(request, response);
                    }
                    break;
                case "/cities/filter":
                    filterCities(request, response);
                    break;
                case "/cities/filter/name":
                    filterCitiesByName(request, response);
                    break;
                case "/cities/filter/meters-above-sea-level":
                    filterCitiesByMetersAboveSeaLevel(request, response);
                    break;
                case "/cities/sort":
                    sort(request, response);
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
            cityService.createCity(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            cityService.updateCity(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        cityService.deleteCity(request, response);
    }

    public void getCities(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       cityService.getAllCities(request, response);
    }

    private void getCityById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<City> city = cityDao.getCityById(id);
        if (city.isPresent()) {
            request.setAttribute("city", city.get());
        } else {
            request.setAttribute("msg", "Not found city with id=" + id);
        }
        request.getRequestDispatcher("/jsp/get-by-id.jsp").forward(request, response);
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

    private void sort(HttpServletRequest request, HttpServletResponse response) throws Exception {
        cityService.sort(request, response);
    }

}
