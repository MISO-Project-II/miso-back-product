package edu.uniandes.miso.service;

import edu.uniandes.miso.dto.InputProductDto;
import edu.uniandes.miso.dto.ResponseService;
import edu.uniandes.miso.entity.Product;
import edu.uniandes.miso.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductService {
	@Inject
	Logger log;
	@Inject
	ProductRepository repository;
	ResponseService responseService = new ResponseService();

	@POST
	public Response create(InputProductDto input) {
		if (StringUtils.isNotEmpty(input.getName())) {
			Product product = new Product();
			product.setName(input.getName());
			product.setDescription(input.getDescription());
			product.setIdSport(input.getIdSport());
			responseService.setSuccess(true);
			responseService.setMessage("Created");
			responseService.setResult(repository.save(product));
			return Response.status(Response.Status.OK).entity(responseService).build();
		}
		responseService.setSuccess(false);
		responseService.setMessage("Fail to created");
		return Response.status(Response.Status.BAD_REQUEST).entity(responseService).build();
	}

	@GET
	@Path("{id}")
	public Response get(@PathParam("id") Long idProduct) {
		Optional<Product> product = repository.findById(idProduct);
		if(product.isPresent()) {
			responseService.setSuccess(true);
			responseService.setMessage("Success");
			responseService.setResult(product.get());
			return Response.status(Response.Status.OK).entity(responseService).build();
		}
		responseService.setSuccess(false);
		responseService.setMessage("Not found");
		return Response.status(Response.Status.NO_CONTENT).entity(responseService).build();
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long idProduct) {
		Optional<Product> product = repository.findById(idProduct);
		if(product.isPresent()) {
			repository.deleteById(product.get().getIdProduct());
            responseService.setSuccess(true);
            responseService.setMessage("Success");
            return Response.status(Response.Status.OK).entity(responseService).build();
		}
		responseService.setSuccess(false);
		responseService.setMessage("Not found");
		return Response.status(Response.Status.NO_CONTENT).entity(responseService).build();
	}

	@GET
	public Response getAll() {
		responseService.setSuccess(true);
		responseService.setMessage("success");
		responseService.setResult(repository.findAll());
		return Response.status(Response.Status.OK).entity(responseService).build();
	}
}
