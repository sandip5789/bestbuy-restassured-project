package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest extends TestBase {
    static ValidatableResponse response;

    @Test
    public static void storeExtraction() {
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
        // Print Starting Test Statement
        System.out.println("------------------StartingTest---------------------------");
        // 1. Extract the limit
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);
        // 2. Extract the total
        int total = response.extract().path("total");
        System.out.println("The value of total is : " + total);
        // 3. Extract the name of 5th store
        String nameOf5thStore = response.extract().path("data[4].name");
        System.out.println("The value of 5th storename is : " + nameOf5thStore);
        // 4. Extract the names of all the store
        List<String> allStoreNames = response.extract().path("data.name");
        System.out.println("The value of all store name are : " + allStoreNames);
        // 5. Extract the storeId of all the store
        List<Integer> allStoreId = response.extract().path("data.id");
        System.out.println("The value of all store name are : " + allStoreId);
        // 6. Print the size of the data list
        List<Integer> dataList = response.extract().path("data.findAll{it}.list");
        System.out.println("Print the size of all data list =" + dataList.size());
        // 7. Get all the value of the store where store name = St Cloud
        List<HashMap<String, Object>> storeInfo = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("Get all the value of the store name = st cloud = " + storeInfo);
        // 8. Get the address of the store where store name = Rochester
        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("Get the address of the store where store name Rochester " + address);
        // 9. Get all the services of 8th store
        List<String> services = response.extract().path("data[7].services");
        System.out.println("Get all the services of 8 store " + services);
        // 10. Get storeservices of the store where service name = Windows Store
        List<HashMap<String, ?>> storeServices = response.extract().path("data.collectMany { store -> store.services.findAll { it.name == 'Windows Store'}.collect { it.storeservices}}");
//        List<HashMap<String, Object>> storeServices = response.extract().path("data.findAll{it.name == 'Windows Store'}.storeservices");
        System.out.println("Get storeservices of the store where service name = Windows Store " + storeServices);
        // 11. Get all the storeId of all the store
        List<Integer> storeId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("Get all the storeId of all the store " + storeId);
        // 12. Get id of all the store
        List<String> allId = response.extract().path("data.id");
        System.out.println("Get all the id of all the store " + allId);
        // 13. Find the store names Where state = ND
        List<String> stateName = response.extract().path("data.findAll{it.state== 'ND'}.name");
        System.out.println("Find the store names Where state = ND " + stateName);
        // 14. Find the Total number of services for the store where store name = Rochester
        List<Integer> servicesRochester = response.extract().path("data.find{it.name== 'Rochester'}.services");
        System.out.println("Find the Total number of services for the store where store name = Rochester = " + servicesRochester.size());
        // 15. Find the createdAt for all services whose name = “Windows Store”
        List<HashMap<String, ?>> createdAtName = response.extract().path("data.collectMany { store -> store.services.findAll { it.name == 'Windows Store'}.collect { it.createdAt}}");
        System.out.println("Find the createdAt for all services whose name = Windows Store " + createdAtName);
        // 16. Find the name of all services Where store name = “Fargo”
        List<String> servicesName = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
        System.out.println("Find the name of all services Where store name = “Fargo” " + servicesName);
        // 17. Find the zip of all the store
        List<Integer> zipStore = response.extract().path("data.zip");
        System.out.println("Find the zip of all the store " + zipStore);
        // 18 .Find the zip of store name = Roseville
        List<Integer> zipRoseville = response.extract().path("data.findAll{it.name== 'Roseville'}.zip");
        System.out.println("Find the zip of store name = Roseville " + zipRoseville);
        // 19. Find the storeservices details of the service name = Magnolia Home Theater
        List<HashMap<String, ?>> storeService = response.extract().path("data.collectMany { store -> store.services.findAll { it.name == 'Magnolia Home Theater'}.collect { it.storeservices}}");
        System.out.println("Find the storeservices details of the service name = Magnolia Home Theater " + storeService);
        // 20. Find the lat of all the stores
        List<Integer> lat = response.extract().path("data.lat");
        System.out.println("Find the lat of all the stores " + lat);
        // Print End Of Test Statement
        System.out.println("------------------End of Test---------------------------");

    }
}