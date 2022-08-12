package stepdefinition;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.DarkskyHome;

import java.util.ArrayList;
import java.util.Collections;

import static stepdefinition.SharedSD.getDriver;

public class DarkskySD {


    DarkskyHome darkskyHome = new DarkskyHome();

    @Given("I am on Darksky Home Page")
    public void i_am_on_darksky_home_page() {

        Assert.assertEquals(getDriver().getTitle(),
                "Dark Sky - Sansad Marg, New Delhi, Delhi",
                "This is not a darksky homepage");
    }

    @Then("I verify current temp is equal to Temperature from Daily Timeline")
    public void i_verify_current_temp_is_equal_to_temperature_from_daily_timeline() {

        int expected = darkskyHome.getCurrentTemp();
        int actual = darkskyHome.getTimelineTemp();

        System.out.println("expected="+expected);
        System.out.println("actual="+actual);

        Assert.assertEquals(actual,expected,"temperature does not match");
    }


    @Then("I verify timeline is displayed with two hours incremented")
    public void iVerifyTimelineIsDisplayedWithTwoHoursIncremented() {

        ArrayList<Integer> timeList = darkskyHome.getTimeList();
        ArrayList<Integer> timeDiffList = new ArrayList<>();

        for (int i=0;i<timeList.size()-1;i++)
        {
            int time1 = timeList.get(i);
            int time2 = timeList.get(i+1);

            int timeDiff=0;

            if(time2>time1)
                timeDiff = time2 - time1;
            if(time1>time2)
                timeDiff = (time2+12) - time1;

            timeDiffList.add(timeDiff);
        }

        System.out.println(timeDiffList);

        /*boolean flag = true;
        for (int i=0;i<timeDiffList.size();i++)
        {
            if (timeDiffList.get(i)!=2)
                flag = false;
        }
         Assert.assertTrue(flag,"some differences are not 2");
        */

        int frequency = Collections.frequency(timeDiffList,2);
        int size = timeDiffList.size();


        boolean result = (frequency == size);


        Assert.assertTrue(result,"some differences are not 2");
    }


    @Then("I verify today's lowest and highest temp is displayed correctly")

    public void iVerifyTodaySLowestAndHighestTempIsDisplayedCorrectly() {

        ArrayList<String> expected = darkskyHome.getFrontTempList();
        ArrayList<String> actual = darkskyHome.getTodaysTimelineTempList();

        System.out.println("expected="+expected);
        System.out.println("actual="+actual);

        Assert.assertEquals(actual,expected,"Temperatures are not equal");
    }
}