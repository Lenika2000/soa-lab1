package validators;


import model.Error;
import model.typesForXml.JaxbCity;

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

    public List<Error> validate(JaxbCity city) throws IllegalAccessException, ValidateFieldsException {
        List<Error> errorList = new ArrayList<>();
        for (Field f : JaxbCity.class.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(city) == null) {
                errorList.add(new Error(700, f.getName(), (String.format("City %s is not specified", f.getName()))));
            }
        }
        if (city.getName() != null &&city.getName().isEmpty()) {
            errorList.add(new Error(701,"name", "City name should not be empty"));
        }
        if (city.getArea() <= 0) {
            errorList.add(new Error(701, "area","City area should be bigger than 0"));
        }
        if (city.getPopulation() <= 0) {
            errorList.add(new Error(701, "population", "City population should be bigger than 0"));
        }
        if (city.getTimezone() != null && (city.getTimezone() <= -13 || city.getTimezone() > 15)) {
            errorList.add(new Error(701, "timezone","City timezone should be between -12 and 15"));
        }
        errorList.addAll(humanValidator.validate(city.getGovernor()));
        errorList.addAll(coordinatesValidator.validate(city.getCoordinates()));
        if (errorList.size() > 0) {
            throw new ValidateFieldsException(errorList);
        }
        return errorList;
    }
}
