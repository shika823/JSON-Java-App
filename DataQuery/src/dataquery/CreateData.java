/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataquery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Karl Shipley
 */
public class CreateData {

    private String[] fName;
    private String[] lName;
    private String[] country;
    private static int uniqueId = 1000;

    List<JSONObject> engList = new ArrayList<JSONObject>();
    List<JSONObject> ausList = new ArrayList<JSONObject>();
    List<JSONObject> nzList = new ArrayList<JSONObject>();
    List<JSONObject> usList = new ArrayList<JSONObject>();

    public CreateData() {

    }

    public CreateData(String[] fName, String[] lName, String[] country) {
        this.fName = fName;
        this.lName = lName;
        this.country = country;

    }

    public JSONArray data(int dataLen) throws JSONException {

        JSONArray ja = new JSONArray();
        for (int i = 0; i < dataLen; i++) {
            JSONObject obj = new JSONObject();
            Random r = new Random();
            int randomNumber1 = r.nextInt(fName.length);
            int randomNumber2 = r.nextInt(lName.length);
            int randomNumber3 = r.nextInt(country.length);
            int randomNumber4 = r.nextInt(40);
            obj.put("Country", country[randomNumber3]);
            obj.put("ID", getUniqueId());
            obj.put("Last Name", lName[randomNumber2]);
            obj.put("First name", fName[randomNumber1]);
            obj.put("Age", randomNumber4);
            ja.put(obj);
        }
        return ja;
    }

    public JSONObject highestAge(JSONArray data) throws JSONException {
        JSONObject oldest = new JSONObject();
        Integer max = 0;
      
        for (int i = 0; i < data.length(); i++) {

            JSONObject rec = data.getJSONObject(i);
            String name = rec.getString("First name");
            Integer age = rec.getInt("Age");
            
            if (age > max) {
                oldest = rec;
                max = age;
            }
        }

        return oldest;
    }

    public JSONObject countryOldest(JSONArray data, String country) throws JSONException {
       JSONObject oldest = new JSONObject();
        Integer max = 0;
      
        List<JSONObject> groupList = new ArrayList<JSONObject>();
        for (int i = 0; i < data.length(); i++) {

            JSONObject rec = data.getJSONObject(i);

            if (rec.getString("Country") == country) {
                groupList.add(rec);
            }

        }
        for (int j = 0; j < groupList.size(); j++) {
                JSONObject rec1 = groupList.get(j);
            String name = rec1.getString("First name");
            Integer age = rec1.getInt("Age");
            
            if (age > max) {
                oldest = rec1;
                max = age;
            }
        }
            
          return oldest;       

 
    }

    public String groupCountry(JSONArray data) throws JSONException {
        int max = 0;

        //int max = list.get(0);
        String countryGroups = "";
        for (int i = 0; i < data.length(); i++) {

            JSONObject rec = data.getJSONObject(i);
            String country = rec.getString("Country");
            if (country.toString() == "England") {
                engList.add(rec);
            } else if (country.toString() == "Australia") {
                ausList.add(rec);
            } else if (country.toString() == "New Zealand") {
                nzList.add(rec);
            } else {
                usList.add(rec);
            }
        }
        countryGroups +=  ausList.toString() + "Count: " + ausList.size() + "\n";
        countryGroups +=  engList.toString() +"Count: " + engList.size() + "\n";
        countryGroups +=  nzList.toString() + "Count: " + nzList.size() + "\n";
        countryGroups += usList.toString() + "Count: " + usList.size() + "\n";
        

        return countryGroups;
    }

    public String groupAges(JSONArray data, String country) throws JSONException {
        List<JSONObject> groupList = new ArrayList<JSONObject>();
        String displayString = "";
        String firstAge = "";
        String secondAge = "";
        String thirdAge = "";
        String fourthAge = "";

        for (int i = 0; i < data.length(); i++) {
            JSONObject rec = data.getJSONObject(i);

            if (rec.getString("Country") == country) {
                groupList.add(rec);
            }

        }

        for (int j = 0; j < groupList.size(); j++) {
            JSONObject listRec = groupList.get(j);
            Integer age = listRec.getInt("Age");

            if (age >= 0 && age < 10) {

                firstAge += listRec.toString();

            } else if (age >= 10 && age < 21) {

                secondAge += listRec.toString();

            } else if (age > 20 && age < 31) {

                thirdAge += listRec.toString();

            } else if (age > 30 && age <= 40) {

                fourthAge += listRec.toString();

            }
        }

        displayString += firstAge + "\n";
        displayString +=  secondAge+ "\n";
        displayString +=  thirdAge+ "\n";
        displayString += fourthAge;

        return displayString;
    }

    public static int getUniqueId() {
        return uniqueId++;
    }
    
    
      @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
    
    return true;
    }
}
