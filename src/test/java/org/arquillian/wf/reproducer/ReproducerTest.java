package org.arquillian.wf.reproducer;

import io.restassured.builder.RequestSpecBuilder;
import java.io.IOException;
import java.net.URL;
import org.arquillian.wf.HelloWorld;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(Arquillian.class)
public class ReproducerTest {

    @Deployment
    public static WebArchive deploy() {
        return ShrinkWrap.create(WebArchive.class)
            .addPackage(HelloWorld.class.getPackage())
            .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                .importTestDependencies().resolve().withTransitivity()
                .as(JavaArchive.class));
    }

    @ArquillianResource
    private URL url;

    @Test
    public void testEndpointsOnServer() throws IOException {
        String endPoint = url.toExternalForm() + "reproducer/hello";

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(endPoint);

        given()
            .spec(requestSpecBuilder.build())
            .get()
            .then()
            .assertThat()
            .statusCode(200).body(equalTo("Hello World!!!"));
    }

    @Test
    public void testEndpointsOnClients() throws IOException {
        String endPoint = url.toExternalForm() + "reproducer/hello";

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(endPoint);

        given()
            .spec(requestSpecBuilder.build())
            .get()
            .then()
            .assertThat()
            .statusCode(200).body(equalTo("Hello World!!!"));
    }
}
