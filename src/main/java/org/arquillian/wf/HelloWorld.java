package org.arquillian.wf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorld {

    @GET
    public Response getMessage() {
        String output = "Hello World!!!";
        return Response.status(200).entity(output).build();
    }
}
