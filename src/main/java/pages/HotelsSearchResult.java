package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;

import static stepdefinition.SharedSD.getDriver;

public class HotelsSearchResult extends Base{

    By hotelList = By.xpath("//div[@data-testid='title']");

    By totalRatings = By.xpath("//div[@data-testid='rating-stars']");

    By totalStars = By.xpath("//div[@data-testid='rating-stars']/span");

    By beachText = By.xpath("//div[@data-testid='property-card']//span[contains(@class,'acb')]");

    public ArrayList<Double> getBeachDistanceList()
    {
        ArrayList<String> beachTextList = getElementTextList(beachText);

        ArrayList<Double> beachDistance = new ArrayList<>();

        for (int i=0;i<beachTextList.size();i++)
        {
            String tempDist = beachTextList.get(i);
            double distance=0;

            try {
                if (tempDist.split(" ")[1].equals("m")) {
                    distance = (double) Integer.parseInt(tempDist.split(" ")[0]) / 1000;
                }

                if (tempDist.split(" ")[1].equals("km")) {
                    distance = Integer.parseInt(tempDist.split(" ")[0]);
                }
            }
            catch (Exception e)
            {
                distance=0;
            }

            beachDistance.add(distance);
        }

        return beachDistance;

    }

    public ArrayList<String> getHotelsList()
    {
        return getElementTextList(hotelList);
    }

    public void clickStarRating(String star) // 4 ,5, 3
    {
        // getDriver().navigate().refresh();
        By starRating = By.xpath("//div[@data-filters-group='class']//input[@value='class="+star+"']");

        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        js.executeScript("arguments[0].click()",webAction(starRating));

        //clickOn(starRating);
    }


    public int getTotalRatings()
    {
        return  getDriver().findElements(totalRatings).size();
    }


    public int getTotalStars()
    {
        return  getDriver().findElements(totalStars).size();
    }
}