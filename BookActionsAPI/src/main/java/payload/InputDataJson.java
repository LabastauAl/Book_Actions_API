package payload;

public class InputDataJson {
    public static String addBookData(String name, String isbn, String aisle, String author){
        return "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"isbn\": \"" + isbn + "\",\n" +
                "    \"aisle\": \"" + aisle + "\",\n" +
                "    \"author\": \"" + author + "\"\n" +
                "}";
    }

    public static String deleteBookData(String id){
        String deleteJson = "{\n" +
                "    \"ID\": \"" + id + "\"\n" +
                "}";
        return deleteJson;
    }

}
