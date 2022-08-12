package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HotelsSearchResult;

import java.time.Duration;
import java.util.ArrayList;

import static stepdefinition.SharedSD.getDriver;

public class HotelsSD {

    HotelsSearchResult hotelsSearchResult = new HotelsSearchResult();

    @Given("I am on default locations search result screen")
    public void i_am_on_default_locations_search_result_screen() {

    }


    @Then("I verify {string} is within search result")
    public void i_verify_is_within_search_result(String expectedHotel) {

        System.out.println("expectedHotel = "+expectedHotel);

        ArrayList<String> hotelsList = hotelsSearchResult.getHotelsList();

        for (int i=0;i<hotelsList.size();i++)
        {
            System.out.println(hotelsList.get(i));
        }

        boolean result = hotelsList.contains(expectedHotel);

        Assert.assertTrue(result,"given hotel is not in the list");
    }


    @When("^I select option for stars as (.+)$")
    public void i_select_option_for_stars_as(String stars)  // "5 stars"
    {

        hotelsSearchResult.clickStarRating(stars.split(" ")[0]);
    }

    @Then("^I verify system displays only (.+) hotels on search result$")
    public void i_verify_system_displays_only_hotels_on_search_result(String stars) throws Throwable {

        getDriver().navigate().refresh();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        int totalRatings = hotelsSearchResult.getTotalRatings();
        int totalStars = hotelsSearchResult.getTotalStars();

        System.out.println("totalRatings="+totalRatings); // 25
        System.out.println("totalStars="+totalStars); // 125

        int actualStars = totalStars / totalRatings ;
        int expectedStars = Integer.parseInt(stars.split(" ")[0]);

        System.out.println("actualStars="+actualStars);
        System.out.println("expectedStars="+expectedStars);


        Assert.assertEquals(actualStars,expectedStars,"wrong stars");

    }

    @Then("I verify system displays all hotels within {string} Km radius from a beach")
    public void iVerifySystemDisplaysAllHotelsWithinKmRadiusFromABeach(String distanceStr) {

        ArrayList<Double> beachDistanceList = hotelsSearchResult.getBeachDistanceList();

        System.out.println(beachDistanceList);

        int expectedDist = Integer.parseInt(distanceStr);

        boolean flag = true;

        ArrayList<Double> greaterDistances = new ArrayList<>();

        for (int i=0;i<beachDistanceList.size();i++)
        {
            if (beachDistanceList.get(i)>expectedDist) {
                flag = false;

                greaterDistances.add((beachDistanceList.get(i)));

            }


        }

        Assert.assertTrue(flag,"some distances are grater than:"+distanceStr+"Km"
                +"\nThese are the greater distances:"+greaterDistances);

    }
}
