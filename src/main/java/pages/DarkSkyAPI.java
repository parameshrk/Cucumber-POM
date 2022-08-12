package pages;

import org.openqa.selenium.By;

public class DarkSkyAPI extends DarkskyHome{

    By lnkLogin = By.xpath("//a[@class='button']");

    public void clickLinkLogin()
    {
        clickOn(lnkLogin);
    }
}

