package by.upmebel.upmecutfile.util;

import by.upmebel.upmecutfile.constant.MathOperation;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class HoleCoordinateUtil {

    private final String regex = "([LlBbHh][+\\-*/]\\d+)|(\\d+[+\\-*/][LlBbHh])";
    private final Pattern pattern = Pattern.compile(regex);

    public Map<MathOperation, Long> getOperations(String coordinatePattern){
        Matcher matcher = pattern.matcher(coordinatePattern);

        if(!matcher.matches()){
            //todo – Нормальное исключение
            throw new UnsupportedOperationException();
        }


    }
}
