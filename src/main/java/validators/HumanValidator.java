package validators;
import model.Error;
import model.typesForXml.JaxbHuman;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HumanValidator {
    public List<Error> validate(JaxbHuman human) throws IllegalAccessException {
        List<Error> errorList = new ArrayList<>();
        if (human == null) {
            return errorList;
        }
        for (Field f : JaxbHuman.class.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(human) == null) {
                errorList.add(new Error(700, f.getName(), String.format("Human %s is not specified", f.getName())));
            }
        }
        if (human.getHeight() <= 0) {
            errorList.add(new Error(701, "human", "Human height should be bigger than 0"));
        }
        return errorList;
    }
}
