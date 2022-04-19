package com.edgeapps.personalcabinet;

import com.edgeapps.personalcabinet.organization.Organisation;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestSseElementType;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/organisations")
public class OrganisationResource {
    @Inject
    private PgPool client;

    @Path("/register-organisation")
    @POST
    public Uni<Response> createOrg(Organisation organisation) throws SQLException {

        return Organisation.create(client, organisation)
                .onItem().transform(id -> Response.ok(id).status(CREATED).build());
    }

    @POST
    @Path("/change-organisation")
    @Produces(MediaType.APPLICATION_JSON)
    @RestSseElementType(MediaType.APPLICATION_JSON)
    public Uni<Response> changeOrg(Organisation organisation) throws SQLException {



        return Organisation.changeOrg(client, organisation)
                .onItem().transform(totalAffected -> totalAffected != 0 ? Response.ok(Response.status(RestResponse.Status.OK)) : Response.status(RestResponse.Status.NOT_FOUND))
                .onItem().transform(Response.ResponseBuilder::build);

    }






    @GET
    @Path("/getOrg")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Organisation> getOrg() {
        return Organisation.checkOrganization(client);
    }
}
