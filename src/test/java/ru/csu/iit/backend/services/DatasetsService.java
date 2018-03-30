package ru.csu.iit.backend.services;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ru.csu.iit.backend.builders.DatasetRequestBuilder;
import ru.csu.iit.backend.models.DatasetModel;
import ru.csu.iit.backend.models.DatasetRowModel;

import java.util.Properties;

public class DatasetsService extends BaseService {
    public DatasetsService(Properties properties) {
        super(properties);
    }

    public DatasetRequestBuilder request() {
        return new DatasetRequestBuilder(baseRequest());
    }

    public DatasetModel[] execute(RequestSpecification requestSpecification) {
        return requestSpecification.post("datasets").then()
                .extract()
                .body().as(DatasetModel[].class);
    }

    public Response executeRow(RequestSpecification requestSpecification) {
        return requestSpecification.post("datasets");
    }

    public DatasetRowModel[] executeWithId(RequestSpecification requestSpecification, int datasetId)
    {
        String request= "datasets/" + datasetId + "/rows";
        return requestSpecification.post(request).then().extract().body().as(DatasetRowModel[].class);
    }

    public Response executeRowsCountWithId(RequestSpecification requestSpecification , int datasetId) {
        String request= "datasets/" + datasetId + "/count";
        return requestSpecification.get(request);
    }

        public Response executeRowsWithId(RequestSpecification requestSpecification , int datasetId) {
            String request= "datasets/" + datasetId + "/";
            return requestSpecification.get(request);
    }



}
