package com.is;

import com.is.handbook.Handbook;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import com.is.handbook.Handbook;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/handbook")
public class HandbookResource {
    @Inject
    PgPool client;

    @Path("/region")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Handbook> getRegion(){
        return Handbook.getRegion(client);
    }
}
