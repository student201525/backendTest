package ru.csu.iit.backend.tests;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.csu.iit.backend.models.DatasetModel;
import ru.csu.iit.backend.services.DatasetsService;

import java.lang.reflect.Type;

import static java.lang.Integer.parseInt;

public class DatasetRowsCountTest extends  BaseTest{
    private Integer datasetId;
    private DatasetsService datasetsService = new DatasetsService(properties);

    @Test(groups = "test")
    public void getDatasetRowsCountQuery()
    {
        getDatasetId("CategoryId eq 282 and Caption eq 'Органы записи актов " +
            "гражданского состояния (структурные подразделения Управления ЗАГС Москвы)'");
        getDatasetRowsCount(datasetId);
    }

    private void getDatasetRowsCount(int id)
    {
        RequestSpecification requestSpecification = datasetsService.request()
                .build();
        Response count = datasetsService.executeRowsCountWithId(requestSpecification, id);
        Response rows = datasetsService.executeRowsWithId(requestSpecification, id);

        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(rows.body().print()).getAsJsonObject();
        JsonElement element = object.get("ItemsCount");

        Assert.assertEquals(parseInt(count.body().print()), parseInt(element.toString()));

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
