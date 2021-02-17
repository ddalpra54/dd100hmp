package org.dalpra.acme.dd100hmp.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.dalpra.acme.dd100hmp.entity.Airport;
import org.dalpra.acme.dd100hmp.service.AirportService;

import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.Response.ok;



@Path("/airport")
@RequestScoped
public class AirportResource {

	private final AirportService airporService;

	@Context
	UriInfo uriInfo;

	@Context
	ResourceContext resourceContext;

	@Inject
	public AirportResource(AirportService airporService) {
		this.airporService = airporService;
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAirports() {
		return ok(this.airporService.getAllAirports()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAirportFromId(@PathParam("id") Long id) {
		return ok(this.airporService.getAirportFromId(id)).build();
	}
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Airport airport) {
		return ok(this.airporService.save(airport)).build();
	}

	@DELETE
	@Path("/delete/{id}/")
	public Response deleteOrderById(@PathParam("id") Long id) {

		try {
			this.airporService.deleteByid(id);
		} catch (Exception e) {
			return Response.status(Response.Status.OK).entity("Delete failed").build();
		}
		return Response.status(Response.Status.OK).entity("Deleted successfully").build();
	}

}