package model;

import org.simpleframework.xml.ElementList;

import java.util.List;

public class MetersAboveSeaLevel {
    @ElementList
    private List<Integer> meters = null;

    public List<Integer> getMeters() {
        return meters;
    }

    public void setMeters(List<Integer> cities) {
        this.meters = cities;
    }
}
