package validators;

import model.typesForXml.JaxbCoordinates;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class CoordinatesValidator {
    public List<String> validate(JaxbCoordinates coordinates) throws IllegalAccessException {
        List<String> errorList = new ArrayList<>();
        if (coordinates == null) {
            return errorList;
        }
        for (Field f : JaxbCoordinates.class.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(coordinates) == null) {
                errorList.add(String.format("Coordinates %s is not specified<br>", f.getName()));
            }
        }
        if (coordinates.getY() != null && coordinates.getY() >= 498) {
            errorList.add("Coordinates y should be less than 498<br>");
        }
        if (coordinates.getX() != null && coordinates.getX() <= -60) {
            errorList.add("Coordinates y should be bigger than -60<br>");
        }
        return errorList;
    }
}
