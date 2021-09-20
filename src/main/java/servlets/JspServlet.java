package servlets;

import dao.CityDao;
import model.City;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/pages/*")
public class JspServlet extends HttpServlet {

    private final CityDao cityDao;

    public JspServlet() {
        this.cityDao = new CityDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/add-city-form":
                    showNewForm(request, response);
                    break;
                case "/edit-form":
                    showEditForm(request, response);
                    break;
                case "/get-by-id-form":
                    showGetByIdForm(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/city-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Optional<City> existingCity = cityDao.getCityById(id);
        request.setAttribute("city", existingCity.get());
        request.getRequestDispatcher("/jsp/city-form.jsp").forward(request, response);
    }

    private void showGetByIdForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/get-by-id.jsp").forward(request, response);
    }
}
