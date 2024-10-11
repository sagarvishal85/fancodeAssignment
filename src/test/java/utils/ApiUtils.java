package utils;

import endpoints.Routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtils {

 public static Response getUsers() {
     return RestAssured.get(Routes.getUsersURL);
 }

 public static Response getTodos() {
     return RestAssured.get(Routes.getTodosURL);
 }
}
