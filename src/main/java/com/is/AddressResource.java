package com.is;

import com.is.organization.LegalAddress;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Tuple;
import com.is.organization.LegalAddress;
import com.is.organization.Organization;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.RestSseElementType;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.Response.ok;

@Path("/hello")
public class AddressResource {
    @Inject
    io.vertx.mutiny.pgclient.PgPool client;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
// Each element will be sent as JSON
   @RestSseElementType(MediaType.APPLICATION_JSON)
    public Uni<List<LegalAddress>> get() {
        System.out.println(LegalAddress.findAll(client).map(address -> address));
        return LegalAddress.findAll(client).map(addresses -> addresses);
        //



             /*   .map(address ->)
                .collect().asList()
                .await().indefinitely();

                .map(data -> (data));*/
    }




}