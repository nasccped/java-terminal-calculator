package nasccped.jtc.utils;

public class StringUtils {

    private StringBuilder strBld = new StringBuilder();

    private void resetBuilder() {
        strBld.setLength(0);
    }

    public String concat(String... strings) {

        String result;

        for (String str : strings) {
            strBld.append(str);
        }

        result = strBld.toString();
        resetBuilder();

        return result;
    }
}
