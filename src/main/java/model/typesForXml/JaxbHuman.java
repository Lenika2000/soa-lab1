package model.typesForXml;

import model.Human;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement(name = "governor")
public class JaxbHuman {
    @XmlElement
    private double height; //Значение поля должно быть больше 0
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private java.time.LocalDateTime birthday;

    public JaxbHuman(double height, LocalDateTime birthday) {
        this.height = height;
        this.birthday = birthday;
    }

    public JaxbHuman() {
    }

    public Human toHuman() {
        return new Human(0, height, birthday);
    }

    public double getHeight() {
        return height;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }
}
