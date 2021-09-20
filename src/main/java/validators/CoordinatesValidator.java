package validators;

import model.Error;
import model.typesForXml.JaxbCoordinates;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class CoordinatesValidator {
    public List<Error> validate(JaxbCoordinates coordinates) throws IllegalAccessException {
        List<Error> errorList = new ArrayList<>();
        if (coordinates == null) {
            return errorList;
        }
        for (Field f : JaxbCoordinates.class.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(coordinates) == null) {
                errorList.add(new Error(700, f.getName(), String.format("Coordinates %s is not specified", f.getName())));
            }
        }
        if (coordinates.getY() != null && coordinates.getY() >= 498) {
            errorList.add(new Error(701, "y", "Coordinates y should be less than 498"));
        }
        if (coordinates.getX() != null && coordinates.getX() <= -60) {
            errorList.add(new Error(701, "x", "Coordinates y should be bigger than -60"));
        }
        return errorList;
    }
}
