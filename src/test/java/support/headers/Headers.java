package support.headers;

import org.apache.groovy.util.Maps;

import java.util.Map;

public class Headers {

    public static Map<String, Object> getHeaders() {
        return Maps.of("Content-type","application/json; charset=UTF-8");
    }
}