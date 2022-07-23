package com.herokuapp.metadataserver;

import com.herokuapp.metadataserver.utils.Endpoint;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static com.herokuapp.metadataserver.utils.BodyBuilder.json;
import static com.herokuapp.metadataserver.utils.Configuration.getRequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MetadataQueryTest extends BaseTest {


    @Test
    public void metadataSubjectsQueryTest() {
        List<String> subjects = Arrays.asList(faker.crypto().sha256(), faker.crypto().sha256());

        given(getRequestSpecification())
                .body(json()
                        .with("subjects", subjects)
                        .build())
                .when()
                .post(Endpoint.METADATA_QUERY.format())
                .then()
                .statusCode(200)
                .body("subject", arrayContaining(subjects));
    }

    @Test
    public void metadataPropertiesQueryTest() {
        List<String> subjects = Arrays.asList(faker.crypto().sha256(), faker.crypto().sha256());
        List<String> properties = Arrays.asList("name", "description", "url");

        given(getRequestSpecification())
                .body(json()
                        .with("subjects", subjects)
                        .with("properties", properties)
                        .build())
                .when()
                .post(Endpoint.METADATA_QUERY.format())
                .then()
                .statusCode(200)
                .body("properties", arrayContaining(properties));
    }

    @DataProvider
    public Object[][] badRequestCombinations() {
        return new Object[][]{
                {
                        json()
                                .with("topics", Arrays.asList(faker.crypto().sha256(), faker.crypto().sha256()))
                                .build(),
                        Arrays.asList("Property: 'topics' is not supported.")},
                {
                        json()
                                .with("subjects", "string")
                                .with("properties", "string")
                                .build(),
                        Arrays.asList(
                                "Property: 'subject' should be an array of strings.",
                                "Property: 'properties' should be an array of strings."
                        )
                }
        };
    }

    @Test(dataProvider = "badRequestCombinations")
    public void metadataBadBodyQueryTest(Map<String, Object> badBody, List<String> expectedMessages) {
        given(getRequestSpecification())
                .body(badBody)
                .when()
                .post(Endpoint.METADATA_QUERY.format())
                .then()
                .statusCode(400)
                .body("messages", arrayContainingInAnyOrder(expectedMessages));
    }

}