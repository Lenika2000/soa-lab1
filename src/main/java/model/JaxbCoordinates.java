package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coordinates")
public class JaxbCoordinates {
    @XmlElement
    private Integer x; //Значение поля должно быть больше -60, Поле не может быть null
    @XmlElement
    private Long y; //Максимальное значение поля: 498, Поле не может быть null

    public JaxbCoordinates() {
    }

    public Coordinates toCoordinates() {
        return new Coordinates(0, x, y);
    }

    public JaxbCoordinates(Integer x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Long getY() {
        return y;
    }
}
