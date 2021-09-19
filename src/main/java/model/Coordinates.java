package model;

import model.typesForXml.JaxbCoordinates;
import util.Jaxb;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table
@XmlRootElement
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @XmlElement
    private Integer x; //Значение поля должно быть больше -60, Поле не может быть null
    @XmlElement
    private Long y; //Максимальное значение поля: 498, Поле не может быть null

    public Coordinates() {

    }

    public Coordinates(int id, Integer x, Long y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void update(JaxbCoordinates data) {
        this.x = data.getX();
        this.y = data.getY();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}

