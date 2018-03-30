package ru.csu.iit.backend.tests;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import ru.csu.iit.backend.models.DatasetModel;
import ru.csu.iit.backend.services.DatasetsService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ExampleTest extends BaseTest {
    @Test(groups = "test")
    public void dataProjectionQuery() {
        DatasetsService datasetsService = new DatasetsService(properties);
        checkDataProjectionQuery(datasetsService, "Id", "Caption", "SefUrl");
        checkDataProjectionQuery(datasetsService, "Id", "Caption", "SefUrl", "LastUpdateDate");
    }

    private void checkDataProjectionQuery(DatasetsService datasetsService, String... fields) {
        RequestSpecification requestSpecification = datasetsService.request()
                .top(10)
                .contains("Caption", "имена")
                .getFields(fields)
                .build();
        ValidatableResponse response = datasetsService.executeRow(requestSpecification)
                .then()
                .assertThat()
                .body("Caption", everyItem(containsString("имена")));

        for (String field : fields) {
            response.body(field, everyItem(notNullValue()));
        }

        response
                .extract()
                .body().as(ArrayNode.class).forEach(node -> assertThat(node.size(), equalTo(fields.length)));
    }

    @Test(groups = "models")
    public void getDatasetId() {
        DatasetsService datasetsService = new DatasetsService(properties);
        RequestSpecification requestSpecification = datasetsService.request()
                .top(10)
                .contains("Caption", "имена")
                .getFields("Id", "Caption", "SefUrl")
                .build();
        DatasetModel[] datasets = datasetsService.execute(requestSpecification);

        for (DatasetModel dataset : datasets) {
            assertThat(dataset.getCaption(), containsString("имена"));
        }
    }

}
