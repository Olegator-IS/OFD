package com.edgeapps.personalcabinet;

import com.edgeapps.personalcabinet.organization.LegalAddress;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.RestSseElementType;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hello")
public class AddressResource {
    @Inject
    private io.vertx.mutiny.pgclient.PgPool client;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
// Each element will be sent as JSON
   @RestSseElementType(MediaType.APPLICATION_JSON)
    public Uni<List<LegalAddress>> get() {
        System.out.println("test");
        System.out.println(LegalAddress.findAll(client).map(address -> address));
        return LegalAddress.findAll(client).map(addresses -> addresses);

        //



             /*   .map(address ->)
                .collect().asList()
                .await().indefinitely();

                .map(data -> (data));*/
    }




}
