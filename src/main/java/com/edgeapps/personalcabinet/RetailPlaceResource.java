package com.edgeapps.personalcabinet;

import com.edgeapps.personalcabinet.retailplace.RetailPlace;
import com.edgeapps.personalcabinet.retailplace.RetailPlaceAddress;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.tuples.Tuple2;
import io.vertx.mutiny.pgclient.PgPool;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/retailplace")
public class RetailPlaceResource {
    @Inject
    private PgPool client;

    @Path("/create")

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> create(RetailPlace retailPlace) {
        return RetailPlace.create(client, retailPlace).onItem().transform(id -> Response.ok(id).status(CREATED).build());
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Tuple2<RetailPlace, RetailPlaceAddress>> get(@PathParam("id") long id) {
        return Uni.combine().all().unis(RetailPlace.get(client, id), RetailPlaceAddress.get(client, id)).asTuple();
        //return RetailPlace.get(client, id);
    }
}
