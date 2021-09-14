package model;

import org.simpleframework.xml.ElementList;
import java.util.List;

public class Cities {
    @ElementList
    private List<City> cities = null;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
