package pages;

import helpers.GUIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private final GUIActions guiActions;
    private final WebDriver driver;
    private final By signOut = By.className("logout");
    private final By searchElement = By.id("search_query_top");
    private final By searchButton = By.name("submit_search");
    private final By searchResult = By.className("product-container");


    public HomePage(WebDriver driver) {
        guiActions = new GUIActions(driver);
        this.driver = driver;

    }
    public String getSignOutText() {

        return guiActions.getTextFrom(signOut);
    }

    public HomePage SearchForProduct (String searchQuery){
        guiActions.clickOn(searchElement);
        guiActions.sendTextTo(searchElement,searchQuery);
        guiActions.clickOn(searchButton);

        return new HomePage(driver);

    }

    public List<String> getSearchResults(){
        var results = driver.findElements(searchResult);

        return results.stream().map(WebElement::getText).toList();
    }







}