package com.ipl.pageActions;

import com.ipl.models.TeamRow;
import com.ipl.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.ipl.pages.PointsTablePage.*;

public class PointsTablePageActions {

    private WebDriver driver;
    private Waits waits;

    public PointsTablePageActions(WebDriver driver){

        this.driver=driver;
        waits = new Waits(driver);
    }

    public String printPointsOfAGivenTeamName(String teamName){
        WebElement points = waits.waitForElementToBeVisisble(getPointsByTeamName(teamName),5);
        return points.getText().trim();
    }

    public List<Integer> actualPointsList(){

        List<WebElement> pointsList = waits.waitForAllElementsToBeVisible(points,5);
        List<Integer> actualList = pointsList.stream()
                .map(ele->Integer.parseInt(ele.getText().trim()))
                .collect(Collectors.toList());

        return actualList;
    }

    public List<Integer> expectedSortedPointsList(){

        List<WebElement> pointsList = waits.waitForAllElementsToBeVisible(points,5);
        List<Integer> expectedSortedList = pointsList.stream()
                .map(ele->Integer.parseInt(ele.getText().trim()))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        return expectedSortedList;
    }




    public List<TeamRow> getActualTableRows(){
        List<WebElement> teamRowsList = waits.waitForAllElementsToBeVisible(tableRows,5);
        List<TeamRow> actualList = new ArrayList<>();

        for(WebElement row : teamRowsList){
            String name = row.findElement(teamName).getAttribute("aria-label").trim();
            int pts = Integer.parseInt(row.findElement(points).getText().trim());
            double nrr = Double.parseDouble(row.findElement(netRunRate).getText().trim());
            actualList.add(new TeamRow(name,pts,nrr));
        }
        return actualList;
    }

    // 2. Sort a copy using TeamRow's compareTo()
    public List<TeamRow> getExpectedSortedRows(List<TeamRow> actualList){
        List<TeamRow> expectedSortedRows = new ArrayList<>(actualList);
        Collections.sort(expectedSortedRows);
        return expectedSortedRows;
    }

}
