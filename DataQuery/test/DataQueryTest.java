/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dataquery.CreateData;
import static junit.framework.Assert.assertEquals;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;

import org.skyscreamer.jsonassert.JSONAssert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Karl Shipley
 */
public class DataQueryTest {

    public JSONObject obj;
    public JSONObject obj2;
    public JSONObject obj3;
    public JSONObject obj4;
    public JSONObject obj5;
    public JSONArray ja;
    public CreateData cd;

    public DataQueryTest() {
    }

    @Before
    public void setUp() throws JSONException {

        cd = new CreateData();
        ja = new JSONArray();

        obj = new JSONObject();

        obj.put("Country", "Australia");
        obj.put("ID", 1);
        obj.put("Last Name", "Terry");
        obj.put("First name", "Steve");
        obj.put("Age", 3);

        obj2 = new JSONObject();
        obj2.put("Country", "US");
        obj2.put("ID", 2);
        obj2.put("Last Name", "Stevenson");
        obj2.put("First name", "John");
        obj2.put("Age", 20);

        obj3 = new JSONObject();
        obj3.put("Country", "Australia");
        obj3.put("ID", 3);
        obj3.put("Last Name", "Johnson");
        obj3.put("First name", "Alfie");
        obj3.put("Age", 10);

        obj4 = new JSONObject();
        obj4.put("Country", "England");
        obj4.put("ID", 4);
        obj4.put("Last Name", "Shipley");
        obj4.put("First name", "Jerry");
        obj4.put("Age", 4);

        obj5 = new JSONObject();
        obj5.put("Country", "New Zealand");
        obj5.put("ID", 5);
        obj5.put("Last Name", "Terry");
        obj5.put("First name", "Steve");
        obj5.put("Age", 24);

        ja.put(obj);
        ja.put(obj2);
        ja.put(obj3);
        ja.put(obj4);
        ja.put(obj5);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testData() throws JSONException {

        JSONAssert.assertEquals("{\"Country\":\"US\",\"First name\":\"John\",\"ID\":2,\"Last Name\":\"Stevenson\",\"Age\":20}", obj2, true);

        JSONAssert.assertEquals("[{\"Country\":\"Australia\",\"First name\":\"Steve\",\"ID\":1,\"Last Name\":\"Terry\",\"Age\":3},"
                + "{\"Country\":\"US\",\"First name\":\"John\",\"ID\":2,\"Last Name\":\"Stevenson\",\"Age\":20},"
                + "{\"Country\":\"Australia\",\"First name\":\"Alfie\",\"ID\":3,\"Last Name\":\"Johnson\",\"Age\":10},"
                + "{\"Country\":\"England\",\"First name\":\"Jerry\",\"ID\":4,\"Last Name\":\"Shipley\",\"Age\":4},"
                + "{\"Country\":\"New Zealand\",\"First name\":\"Steve\",\"ID\":5,\"Last Name\":\"Terry\",\"Age\":24}]", ja, true);
    }

    @Test
    public void highestAge() throws JSONException {

        JSONObject highest = cd.highestAge(ja);

        JSONAssert.assertEquals("{\"Country\":\"New Zealand\",\"First name\":\"Steve\",\"ID\":5,\"Last Name\":\"Terry\",\"Age\":24}", highest, true);

    }

    @Test
    public void groupCountry() throws JSONException {

        String groups = cd.groupCountry(ja);

        JSONAssert.assertEquals("[{\"Country\":\"Australia\",\"First name\":\"Steve\",\"ID\":1,\"Last Name\":\"Terry\",\"Age\":3},"
                + "{\"Country\":\"Australia\",\"First name\":\"Alfie\",\"ID\":3,\"Last Name\":\"Johnson\",\"Age\":10}]Count: 2\n"
                + "[{\"Country\":\"England\",\"First name\":\"Jerry\",\"ID\":4,\"Last Name\":\"Shipley\",\"Age\":4}]Count: 1\n"
                + "[{\"Country\":\"New Zealand\",\"First name\":\"Steve\",\"ID\":5,\"Last Name\":\"Terry\",\"Age\":24}]Count: 1\n"
                + "[{\"Country\":\"US\",\"First name\":\"John\",\"ID\":2,\"Last Name\":\"Stevenson\",\"Age\":20}]Count: 1", groups, true);

    }

    @Test
    public void groupAges() throws JSONException {

        String usGroup = cd.groupAges(ja, "US");
        String nzGroup = cd.groupAges(ja, "New Zealand");
        String ausGroup = cd.groupAges(ja, "Australia");
        String engGroup = cd.groupAges(ja, "England");

        JSONAssert.assertEquals("\n{\"Country\":\"US\",\"First name\":\"John\",\"ID\":2,\"Last Name\":\"Stevenson\",\"Age\":20}\n", usGroup, true);
        JSONAssert.assertEquals("{\"Country\":\"New Zealand\",\"First name\":\"Steve\",\"ID\":5,\"Last Name\":\"Terry\",\"Age\":24}", nzGroup, true);
        JSONAssert.assertEquals("{\"Country\":\"Australia\",\"First name\":\"Steve\",\"ID\":1,\"Last Name\":\"Terry\",\"Age\":3}{\"Country\":\"Australia\",\"First name\":\"Alfie\",\"ID\":3,\"Last Name\":\"Johnson\",\"Age\":10}",
                ausGroup, true);
        JSONAssert.assertEquals("{\"Country\":\"England\",\"First name\":\"Jerry\",\"ID\":4,\"Last Name\":\"Shipley\",\"Age\":4}", engGroup, true);

    }

    @Test
    public void averageAge() throws JSONException {
        JSONObject usOldest = cd.countryOldest(ja, "US");
        JSONObject ausOldest = cd.countryOldest(ja, "Australia");
        JSONObject engOldest = cd.countryOldest(ja, "England");
        JSONObject nzOldest = cd.countryOldest(ja, "New Zealand");
        JSONAssert.assertEquals("{\"Country\":\"US\",\"First name\":\"John\",\"ID\":2,\"Last Name\":\"Stevenson\",\"Age\":20}", usOldest, true);
        JSONAssert.assertEquals("{\"Country\":\"Australia\",\"First name\":\"Alfie\",\"ID\":3,\"Last Name\":\"Johnson\",\"Age\":10}", ausOldest, true);
        JSONAssert.assertEquals("{\"Country\":\"England\",\"First name\":\"Jerry\",\"ID\":4,\"Last Name\":\"Shipley\",\"Age\":4}", engOldest, true);
        JSONAssert.assertEquals("{\"Country\":\"New Zealand\",\"First name\":\"Steve\",\"ID\":5,\"Last Name\":\"Terry\",\"Age\":24}", nzOldest, true);

    }
}
