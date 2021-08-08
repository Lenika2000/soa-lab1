import dao.CityDao;
import model.City;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {

    private CityDao cityDao = new CityDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Database Demo";
        String docType = "<!DOCTYPE html>";

        writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
        writer.println("<h1>Cities</h1>");
        writer.println("<br/>");
        cityDao.addCity(new City());
//            List<City> cities = cityDao.getAllCities();
//            System.out.println (cities);
//            for ( City city : cities) {
//                writer.println("Name: " + city.getName());
//            }
        writer.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

}
