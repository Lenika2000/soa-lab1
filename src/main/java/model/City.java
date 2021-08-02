package model;

import java.time.ZonedDateTime;

public class City {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float area; //Значение поля должно быть больше 0
    private int population; //Значение поля должно быть больше 0
    private int metersAboveSeaLevel;
    private Double timezone; //Значение поля должно быть больше -13, Максимальное значение поля: 15
    private Government government; //Поле не может быть null
    private StandardOfLiving standardOfLiving; //Поле может быть null
    private Human governor; //Поле может быть null
}
