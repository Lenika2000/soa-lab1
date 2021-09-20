package validators;


import model.typesForXml.JaxbCity;

import javax.xml.bind.ValidationException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class CityValidator {

    private final HumanValidator humanValidator;
    private final CoordinatesValidator coordinatesValidator;

    public CityValidator() {
        humanValidator = new HumanValidator();
        coordinatesValidator = new CoordinatesValidator();
    }

    public List<String> validate(JaxbCity city) throws IllegalAccessException, ValidationException {
        List<String> errorList = new ArrayList<>();
        for (Field f : JaxbCity.class.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(city) == null) {
                errorList.add((String.format("City %s is not specified<br>", f.getName())));
            }
        }
        if (city.getName() != null &&city.getName().isEmpty()) {
            errorList.add("City name should not be empty");
        }
        if (city.getArea() <= 0) {
            errorList.add("City area should be bigger than 0");
        }
        if (city.getPopulation() <= 0) {
            errorList.add("City population should be bigger than 0");
        }
        if (city.getTimezone() != null && (city.getTimezone() <= -13 || city.getTimezone() > 15)) {
            errorList.add("City timezone should be between -12 and 15");
        }
        errorList.addAll(humanValidator.validate(city.getGovernor()));
        errorList.addAll(coordinatesValidator.validate(city.getCoordinates()));
        if (errorList.size() > 0) {
            throw new ValidationException(String.join(",<br>", errorList).replace("<br>,", ","));
        }
        return errorList;
    }
}
