package com.edgeapps.personalcabinet;

import com.edgeapps.personalcabinet.banks.Banks;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/banks")
public class BanksResource {
    @Inject
    private PgPool client;

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Banks> getBanks() {
        return Banks.findAll(client);
    }

}
