package utility;

import java.io.BufferedReader;
import java.io.IOException;

public class BodyParser {
    public static String parseBodyToJson(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp)
                    .append("\n");
        }
        return sb.toString();
    }
}
