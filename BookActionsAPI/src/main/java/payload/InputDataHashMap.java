package payload;

import java.util.HashMap;
import java.util.Objects;

public class InputDataHashMap {
    public static HashMap addBookData(String name, String isbn, String aisle, String author){
        HashMap<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", name);
        jsonAsMap.put("isbn", isbn);
        jsonAsMap.put("aisle", aisle);
        jsonAsMap.put("author", author);

        return jsonAsMap;
    }
}
