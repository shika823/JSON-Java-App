/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataquery;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Karl Shipley
 */
public class DataQuery {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JSONException {

        String[] first = {"Jack", "John", "James", "Tim", "Oli", "Jan", "Tom", "Candice", "Tony", "Gwen"};
        String[] last = {"Jackson", "Johnson", "Stevenson", "Shipley", "Robinson", "Jansen", "Thompson", "Charles", "Jamieson", "Gwenson"};
        String[] nation = {"Australia", "New Zealand", "England", "US"};
        CreateData cd = new CreateData(first, last, nation);

        Scanner myObj = new Scanner(System.in);
        System.out.println("Please enter number of rows of data to generate");

        try {
            Integer amount = myObj.nextInt();
            System.out.println("You Entered: " + amount);
            System.out.println();
            JSONArray data = cd.data(amount);

            System.out.println("Oldest Person in dataset:" + cd.highestAge(data));
            System.out.println();
            System.out.println("Grouped by country with count:");
            System.out.println(cd.groupCountry(data));

            System.out.println("Oldest person from country: " + cd.countryOldest(data, "Australia"));
            System.out.println();
            System.out.println("  Country grouped by age groups:");

            System.out.println(cd.groupAges(data, "US"));
           } catch (InputMismatchException a) {
            System.out.println("You must enter a number");
        }

    }

}
