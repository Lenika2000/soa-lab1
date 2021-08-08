import model.City;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityTableBean implements Serializable {

    private List<City> cities;

    public CityTableBean() {
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public List getCities() {
        List<City> reversed = new ArrayList(cities);
        Collections.reverse(reversed);
        return reversed;
    }
}
