package edu.uniandes.miso.service;

import edu.uniandes.miso.dto.InputProductDto;
import edu.uniandes.miso.dto.Reply;
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
import javax.ws.rs.PUT;
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

	@POST
	public Response create(InputProductDto input) {
		if (StringUtils.isNotEmpty(input.getName())) {
			Product product = new Product();
			product.setName(input.getName());
			product.setDescription(input.getDescription());
			product.setIdUserCreator(input.getIdUserCreator());
			product.setIdSport(input.getIdSport());
			return Reply.ok(repository.save(product));
		}
		return Reply.notFound(null);
	}

	@GET
	@Path("{id}")
	public Response get(@PathParam("id") Long idProduct) {
		Optional<Product> product = repository.findById(idProduct);
		if(product.isPresent()) {
			return Reply.ok(product.get());
		}
		return Reply.notFound(null);
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long idProduct) {
		Optional<Product> product = repository.findById(idProduct);
		if(product.isPresent()) {
			repository.deleteById(product.get().getIdProduct());
            return Reply.ok(null);
		}
		return Reply.notFound(null);
	}

	@PUT
	@Path("{id}")
	public Response put(@PathParam("id") Long idService, InputProductDto input) {
		Optional<Product> product = repository.findById(idService);
        if(product.isPresent()) {
            product.get().setName(input.getName());
            product.get().setDescription(input.getDescription());
            product.get().setIdSport(input.getIdSport());
			product.get().setIdUserCreator(input.getIdUserCreator());
            return Reply.ok(repository.save(product.get()));
        }
		return Reply.notFound(null);
	}

	@GET
	public Response getAll() {
		return Reply.ok(repository.findAll());
	}
}
