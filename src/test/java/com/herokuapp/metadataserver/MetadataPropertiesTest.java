package com.herokuapp.metadataserver;

import com.herokuapp.metadataserver.utils.Endpoint;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.herokuapp.metadataserver.utils.BodyBuilder.json;
import static com.herokuapp.metadataserver.utils.Configuration.getRequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MetadataPropertiesTest extends BaseTest {

    private List<String> subjects = Arrays.asList(faker.crypto().sha256(), faker.crypto().sha256());
    private List<String> properties = Arrays.asList("name", "description", "url");

    @BeforeMethod
    public void createSubjectWithProperties() {
        given(getRequestSpecification())
                .body(json()
                        .with("subjects", subjects)
                        .with("properties", properties)
                        .build())
                .log().all()
                .when()
                .post(Endpoint.METADATA_QUERY.format())
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void metadataPropertiesTest() {
        given(getRequestSpecification())
                .when()
                .get(Endpoint.METADATA_PROPERTY_NAME.format(subjects.get(0)))
                .then()
                .statusCode(200)
                .body(allOf(startsWith("{"), endsWith("}"))) // body should be json
                .body("value", anything())
                .body("anSignatures", array(
                        hasProperty("publicKey"),
                        hasProperty("signature")));
    }
}
