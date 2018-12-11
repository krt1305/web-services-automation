package com.service.jsonAssertLib;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class jsonAssertEx {

    @Test
    public void assertJson() throws JSONException {

        //used to assert expected , actual json values
        String actualValue="{id:1,rollNo:1}";
        String expectedValue="{id:1,rollNo:1}";

        JSONAssert.assertEquals(expectedValue,actualValue, JSONCompareMode.STRICT_ORDER);


         actualValue="{id:1,rollNo:1,badgeNo:1}";
         expectedValue="{id:1,rollNo:1}";

        JSONAssert.assertEquals(expectedValue,actualValue, JSONCompareMode.LENIENT);

        actualValue="{id:1,rollNo:1}";
        expectedValue="{rollNo:1,id:1}";

        JSONAssert.assertEquals(expectedValue,actualValue, JSONCompareMode.STRICT);

    }
}
