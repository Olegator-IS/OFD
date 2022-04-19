package com.edgeapps.personalcabinet;


import com.edgeapps.personalcabinet.organization.OrganisationsForm;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/organisations")
public class OrganisationsFormResource {
    @Inject
    private PgPool client;

    @GET
    @Path("/organisationsForm")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<OrganisationsForm> getPositions() {
        return OrganisationsForm.findAll(client);

    }
}
