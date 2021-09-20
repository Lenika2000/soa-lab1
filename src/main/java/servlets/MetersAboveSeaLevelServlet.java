package servlets;

import service.CityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/meters-above-sea-level")
public class MetersAboveSeaLevelServlet extends HttpServlet {

    private CityService cityService;

    public MetersAboveSeaLevelServlet() {
        this.cityService = new CityService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            cityService.getUniqueMetersAboveSeeLevel(request, response);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
