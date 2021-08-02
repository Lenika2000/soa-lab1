import model.City;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityTableBean implements Serializable {

    private List<City> cities;

    public CityTableBean() {
        cities = new ArrayList<>();
    }

    public void addPoint(City city) {
        cities.add(city);
    }

    public List getPoints() {
        List<City> reversed = new ArrayList(cities);
        Collections.reverse(reversed);
        return reversed;
    }
}
