package com.ipl.pages;

import org.openqa.selenium.By;

public class PointsTablePage {
    public static By points = By.xpath("//table[contains(@data-testid,'ipl-points-table-wide')]//td[contains(@class,'font-bold')]");
    public static By tableRows = By.xpath("//table[contains(@data-testid,'ipl-points-table-wide')]//tbody//tr");
    public static By teamName = By.xpath("//table[contains(@data-testid,'ipl-points-table-wide')]//th//a");
    public static By netRunRate = By.xpath("//td[contains(@class,'font-bold')]/following-sibling::td[1]");

    public static By getPointsByTeamName(String teamName){
        String xpathExpression = String.format("(//tr[.//a[@aria-label='%s']]//td[contains(@class,'font-bold')])[2]", teamName);
        //Below commented way is also correct:
        // String xpathExpression = "(//tr[.//a[@aria-label='" + teamName + "']]//td[contains(@class,'font-bold')])[2]";
        return By.xpath(xpathExpression);
    }

}
