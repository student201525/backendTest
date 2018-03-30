package ru.csu.iit.backend.tests;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.csu.iit.backend.models.DatasetModel;
import ru.csu.iit.backend.models.DatasetRowModel;
import ru.csu.iit.backend.services.DatasetsService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DatasetDataTest extends BaseTest{
    private Integer datasetId;
    private DatasetsService datasetsService = new DatasetsService(properties);

    @Test(groups = "test")
    public void getDatasetDataQuery()
    {
        getDatasetId("CategoryId eq 14 and Caption eq 'Мосты'");
        getDatasetData(3, datasetId,"Name", "Location");
    }



    private void getDatasetData(int topCount, int id, String... fields )
    {
        RequestSpecification requestSpecification = datasetsService.request()
                .top(topCount)
                .getFields(fields)
                .build();

        DatasetRowModel[] datasets = datasetsService.executeWithId(requestSpecification, id);

        int index=1;
        for (DatasetRowModel dataset:datasets
             ) {
            assertThat(dataset.getCells().getName(), notNullValue());
            assertThat(dataset.getCells().getLocation(), notNullValue());
            assertThat(dataset.getId(), notNullValue());
            Assert.assertEquals(dataset.getNumber(), index++);
        }

        Assert.assertEquals(datasets.length, topCount);
    }



    public void getDatasetId(String filter)
    {

        RequestSpecification requestSpecification = datasetsService.request()
                .filter(filter)
                .getFields("Id")
                .build();

        DatasetModel[] datasets = datasetsService.execute(requestSpecification);

        Assert.assertEquals(datasets.length, 1);

        datasetId=datasets[0].getId();

    }
}
