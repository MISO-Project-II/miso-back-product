package edu.uniandes.miso.service;

import edu.uniandes.miso.repository.ProductRepository;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("backend")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BackendService {
    @Inject
    Logger log;

    @Inject
    ProductRepository repository;

    @POST
    @Path(("listByIdproduct"))
    public Response getListById(List<Long> list) {
        return Response.ok(repository.findAllById(list)).build();
    }

    @GET
    @Path("{idUserCreator}")
    public Response getListByUserId(@PathParam("idUserCreator") Long idUser) {
        log.info("Another resource consuming this method");
        return Response.ok(repository.findByIdUserCreator(idUser)).build();
    }
}
