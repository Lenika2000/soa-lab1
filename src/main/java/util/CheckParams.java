package util;

public class CheckParams {
    public static boolean checkParamForNullOrEmpty (String param) {
        return  (param != null || !param.isEmpty());
    }
}
