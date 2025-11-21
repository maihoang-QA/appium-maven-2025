package test_data;

import com.google.gson.Gson;

public class TestGson {
    public static void main(String[] args) {
        //Convert from JSON to Object
        String jsonObject = "{\n" +
                "  \"username\": \"mai@gmail.com\",\n" +
                "  \"password\": \"12345678\"\n" +
                "}";

        //Json > Object
        Gson gson = new Gson();
        LoginCreds loginCreds = gson.fromJson(jsonObject, LoginCreds.class);
        System.out.println(loginCreds);

        //Object > Json
        System.out.println(gson.toJson(loginCreds));

    }
}
