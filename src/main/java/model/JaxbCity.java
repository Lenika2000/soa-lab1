package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.ZonedDateTime;

@XmlRootElement(name = "city")
public class JaxbCity implements Serializable {
    @XmlElement
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @XmlElement
    private String name; //Поле не может быть null, Строка не может быть пустой
    @XmlElement
    private JaxbCoordinates coordinates; //Поле не может быть null
    @XmlElement
    private float area; //Значение поля должно быть больше 0
    @XmlElement
    private int population; //Значение поля должно быть больше 0
    @XmlElement
    private int metersAboveSeaLevel;
    @XmlElement
    private Double timezone; //Значение поля должно быть больше -13, Максимальное значение поля: 15
    @XmlElement
    private Government government; //Поле не может быть null
    @XmlElement
    private StandardOfLiving standardOfLiving; //Поле может быть null
    @XmlElement(name = "governor")
    private JaxbHuman governor; //Поле может быть null

    public City toCity() {
        return new City(
                id,
                name,
                coordinates.toCoordinates(),
                ZonedDateTime.now(),
                area,
                population,
                metersAboveSeaLevel,
                timezone,
                government,
                standardOfLiving,
                governor.toHuman()
        );
    }

    public JaxbCity(Long id, String name, JaxbCoordinates coordinates, float area, int population, int metersAboveSeaLevel, Double timezone, Government government, StandardOfLiving standardOfLiving, JaxbHuman governor) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.timezone = timezone;
        this.government = government;
        this.standardOfLiving = standardOfLiving;
        this.governor = governor;
    }

    public JaxbCity() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public JaxbCoordinates getCoordinates() {
        return coordinates;
    }

    public float getArea() {
        return area;
    }

    public int getPopulation() {
        return population;
    }

    public int getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public Double getTimezone() {
        return timezone;
    }

    public Government getGovernment() {
        return government;
    }

    public StandardOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }

    public JaxbHuman getGovernor() {
        return governor;
    }
}

