package pages;

import org.openqa.selenium.By;

import java.util.ArrayList;

public class DarkskyHome extends Base{

    By currentTempRaw = By.xpath("//span[@class='summary swap']");
    By timelineTempRaw = By.xpath("//span[@class='first']//span");
    By rawTimeList = By.xpath("//span[@class='hour']/span");

    By lowTempFront = By.xpath("//span[@class='low-temp-text']"); // 78˚
    By highTempFront = By.xpath("//span[@class='high-temp-text']");// 90˚

    By lowTempToday = By.xpath("//a[@data-day='0']//span[@class='minTemp']"); // 78˚
    By highTempToday = By.xpath("//a[@data-day='0']//span[@class='maxTemp']");// 90˚

    By lnkDarkskyAPI = By.xpath("//a[normalize-space()='Dark Sky API']");

    public void clickDarkSkyAPI()
    {
        clickOn(lnkDarkskyAPI);
    }


    public ArrayList<String> getFrontTempList()
    {
        String lowTemp = getTextFromElement(lowTempFront).split("˚")[0];
        String highTemp = getTextFromElement(highTempFront).split("˚")[0];

        ArrayList<String> tempList = new ArrayList<String>() {{
            add(lowTemp);
            add(highTemp);
        }};

        return tempList;
    }


    public ArrayList<String> getTodaysTimelineTempList()
    {
        String lowTemp = getTextFromElement(lowTempToday).split("˚")[0];
        String highTemp = getTextFromElement(highTempToday).split("˚")[0];

        ArrayList<String> tempList = new ArrayList<String>() {{
            add(lowTemp);
            add(highTemp);
        }};

        return tempList;
    }



    public ArrayList<Integer> getTimeList()
    {
        ArrayList<String> timeRawList = getElementTextList(rawTimeList);
        System.out.println(timeRawList);
        ArrayList<Integer> timeList = new ArrayList<>();

        for (int i=0;i<timeRawList.size();i++)
        {
            String timeRaw =   timeRawList.get(i); // 9pm / 11am
            String timeStr =   timeRaw.substring(0,timeRaw.length()-2); // 9 / 11
            int time = Integer.parseInt(timeStr);
            timeList.add(time);
        }

        System.out.println(timeList);
        return timeList;
    }


    public int getCurrentTemp()
    {
        String tempRaw = getTextFromElement(currentTempRaw);
        // 90˚ Humid and Overcast. --> {"90"," Humid and Overcast."}
        String tempStr = tempRaw.split("˚")[0]; // 90
        return Integer.parseInt(tempStr);
    }

    public int getTimelineTemp()
    {
        String tempRaw = getTextFromElement(timelineTempRaw);
        // 90°--> {"90"}
        String tempStr = tempRaw.split("°")[0]; // 90
        return Integer.parseInt(tempStr);
    }


}