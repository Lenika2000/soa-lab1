package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private double height; //Значение поля должно быть больше 0
    private java.time.LocalDateTime birthday;

    public Human(double height, LocalDateTime birthday) {
        this.height = height;
        this.birthday = birthday;
    }

    public Human() {
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
}

