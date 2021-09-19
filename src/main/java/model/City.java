package model;

import model.typesForXml.JaxbCity;
import model.typesForXml.JaxbHuman;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import model.typesForXml.ZonedDateTimeAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Root
@Entity
@Table
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Element
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Element
    private String name; //Поле не может быть null, Строка не может быть пустой
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @Element
    private Coordinates coordinates; //Поле не может быть null
    @Element
    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Element
    private float area; //Значение поля должно быть больше 0
    @Element
    private int population; //Значение поля должно быть больше 0
    @Element
    private int metersAboveSeaLevel;
    @Element
    private Double timezone; //Значение поля должно быть больше -13, Максимальное значение поля: 15
    @Element
    @Enumerated(EnumType.STRING)
    private Government government; //Поле не может быть null
    @Element
    @Enumerated(EnumType.STRING)
    private StandardOfLiving standardOfLiving; //Поле может быть null
    @Element
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Human governor; //Поле может быть null

    public City(String name, Coordinates coordinates, ZonedDateTime creationDate, float area, int population, int metersAboveSeaLevel, Double timezone, Government government, StandardOfLiving standardOfLiving, Human governor) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.timezone = timezone;
        this.government = government;
        this.standardOfLiving = standardOfLiving;
        this.governor = governor;
    }

    public City(Long id, String name, Coordinates coordinates, ZonedDateTime creationDate, float area, int population, int metersAboveSeaLevel, Double timezone, Government government, StandardOfLiving standardOfLiving, Human governor) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.timezone = timezone;
        this.government = government;
        this.standardOfLiving = standardOfLiving;
        this.governor = governor;
    }

    public City() {
    }

    public void update(JaxbCity data) {
        this.name = data.getName();
        this.coordinates.update(data.getCoordinates());
        this.area = data.getArea();
        this.population = data.getPopulation();
        this.metersAboveSeaLevel = data.getMetersAboveSeaLevel();
        this.timezone = data.getTimezone();
        this.government = data.getGovernment();
        this.standardOfLiving = data.getStandardOfLiving();
        this.governor.update(data.getGovernor());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(int metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public Double getTimezone() {
        return timezone;
    }

    public void setTimezone(Double timezone) {
        this.timezone = timezone;
    }

    public Government getGovernment() {
        return government;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public StandardOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }

    public void setStandardOfLiving(StandardOfLiving standardOfLiving) {
        this.standardOfLiving = standardOfLiving;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }
}
