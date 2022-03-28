package com.edgeapps.personalcabinet;

import com.edgeapps.personalcabinet.organization.Organization;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestSseElementType;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.SQLException;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/organisations")
public class OrganisationResource {
    @Inject
    private PgPool client;
    @Path("/register-organisation")
    @POST
    public Uni<Response> createOrg(Organization organization) throws SQLException {

        return Organization.create(client, organization)
                .onItem()
                .transform(id -> URI.create("/organisation/" + id))
                .onItem()
                .transform(uri  -> Response.ok(organization).status(CREATED).build());
    }

    @Path("/change-organisation")
    @POST
    public Uni<Response> changeOrg(Organization organization) throws SQLException {



        return Organization.changeOrg(client, organization)
                .onItem()
                .transform(id -> URI.create("/organisations" + organization.getId()))
                .onItem()
                .transform(uri -> Response.created(uri).build());
    }

    @POST
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON)
    @RestSseElementType(MediaType.APPLICATION_JSON)
    public Uni<Response> getOrg(Organization organization) {
        return Organization.checkOrganization(client, organization).onItem().transform(organization1 -> organization1 != null ? Response.ok(organization1) : Response.status(RestResponse.Status.NOT_FOUND))
                .onItem().transform(Response.ResponseBuilder::build);
    }
    //
}
