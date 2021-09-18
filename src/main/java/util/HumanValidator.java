package util;
import model.JaxbHuman;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HumanValidator {
    public List<String> validate(JaxbHuman human) throws IllegalAccessException {
        List<String> errorList = new ArrayList<>();
        if (human == null) {
            return errorList;
        }
        for (Field f : JaxbHuman.class.getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(human) == null) {
                errorList.add(String.format("Human %s is not specified<br>", f.getName()));
            }
        }
        if (human.getHeight() <= 0) {
            errorList.add("Human height should be bigger than 0<br>");
        }
        return errorList;
    }
}
