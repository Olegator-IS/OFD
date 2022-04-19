package com.edgeapps.personalcabinet;

import com.edgeapps.personalcabinet.handbook.Handbook;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/handbook")

public class HandbookResource {
    @Inject
    private PgPool client;

    @GET
    @Path("/region")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Handbook> getRegion() {
        return Handbook.getRegion(client);
    }
}

