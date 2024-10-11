package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.ApiUtils;

import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class UserSteps {

 private List<Map<String, Object>> users;
 private int totalTodos = 0;
 private int completedTodos = 0;

 @Given("User has the todo tasks")
 public void user_has_the_todo_tasks() {
     Response userResponse = ApiUtils.getUsers();
     users = userResponse.jsonPath().getList("$");
 }

 @When("User belongs to the city FanCode")
 public void user_belongs_to_the_city_FanCode() {
     for (Map<String, Object> user : users) {
    	 Map<String, Object> address = (Map<String, Object>) user.get("address");

         Map<String, Object> geo = (Map<String, Object>) address.get("geo");

         Double lat = Double.parseDouble(geo.get("lat").toString());
         Double lon = Double.parseDouble(geo.get("lng").toString());
         
         if (lat >= -40 && lat <= 5 && lon >= 5 && lon <= 100) {
             Response todoResponse = ApiUtils.getTodos();
             
             List<Map<String, Object>> todos = todoResponse.jsonPath().getList("$");
             
             for (Map<String, Object> todo : todos) {
                 if (todo.get("userId").equals(user.get("id"))) {
                     totalTodos++;
                     
                     if ((Boolean) todo.get("completed")) {
                         completedTodos++;
                     }
                 }
             }
         }
     }
 }

 @Then("User Completed task percentage should be greater than 50%")
 public void user_completed_task_percentage_should_be_greater_than_50() {
     double completionPercentage = ((double) completedTodos / totalTodos) * 100;
     System.out.println(completedTodos+", "+totalTodos);
     Assert.assertTrue("Completion percentage is not greater than 50%", completionPercentage > 50);
 }
}

