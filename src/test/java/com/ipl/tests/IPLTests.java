package com.ipl.tests;

import com.ipl.pageActions.PointsTablePageActions;
import com.ipl.models.TeamRow;
import com.ipl.testDataProviders.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class IPLTests extends BaseTest {

    @Test(description="Verify that user should be able to find points of a given team",
            dataProvider = "teamNamesDataProvider",
            dataProviderClass = TestDataProvider.class
        )
    public void findPointsOfATeamTest(String teamName){
        PointsTablePageActions pageActions = new PointsTablePageActions(driver);
        String actualPointsStr = pageActions.printPointsOfAGivenTeamName(teamName);

        // 1. Assert points text is not null or empty
        Assert.assertNotNull(actualPointsStr, "Points string should not be null for " + teamName);
        Assert.assertFalse(actualPointsStr.isEmpty(), "Points string should not be empty for " + teamName);

        // 2. Assert points value is a valid numeric integer
        int actualPoints = Integer.parseInt(actualPointsStr);

        // 3. Assert points fall within valid IPL boundaries (0 to 28 points max in a 14-game league)
        Assert.assertTrue(actualPoints >= 0 && actualPoints <= 28,
                "Extracted points " + actualPoints + " are outside valid boundary range for " + teamName);

    }

    @Test(description = "Verify rows are sorted on the basis of points")
    public void verifyRowsAreSorted(){
        PointsTablePageActions pointsTablePageActions = new PointsTablePageActions(driver);
        Assert.assertEquals(pointsTablePageActions.actualPointsList(),pointsTablePageActions.expectedSortedPointsList());
    }

    @Test(description = "Verify IPL points table rows are sorted descending by Points and Net Run Rate (NRR)")
    public void verifyPointsTableSorting() {
        PointsTablePageActions pageActions = new PointsTablePageActions(driver);

        // 1. Fetch actual rows directly from the UI
        List<TeamRow> actualRows = pageActions.getActualTableRows();

        // 2. Derive expected sorted order using compareTo (Pts descending, NRR descending)
        List<TeamRow> expectedRows = pageActions.getExpectedSortedRows(actualRows);

        // 3. Assert UI sequence matches expected sorted sequence
        Assert.assertEquals(actualRows, expectedRows,
                "The points table is not correctly sorted by Points and NRR tie-breaker!");
    }

}
