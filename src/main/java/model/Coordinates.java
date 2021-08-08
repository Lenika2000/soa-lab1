package model;

import javax.persistence.*;

@Entity
@Table
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private Integer x; //Значение поля должно быть больше -60, Поле не может быть null
    private Long y; //Максимальное значение поля: 498, Поле не может быть null

    public Coordinates() {

    }

    public Coordinates(Integer x, Long y) {
        this.x = x;
        this.y = y;
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

