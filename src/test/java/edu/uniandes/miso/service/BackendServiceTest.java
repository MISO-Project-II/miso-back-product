package edu.uniandes.miso.service;

import edu.uniandes.miso.entity.Product;
import edu.uniandes.miso.repository.ProductRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class BackendServiceTest {

	@InjectMock
	ProductRepository repository;

	@Inject
	BackendService backendService;

	PodamFactory factory = new PodamFactoryImpl();
	Product product;

	@BeforeEach
	void setUp() {
		product = factory.manufacturePojo(Product.class);
	}

	@Test
	void getListById() {
		List<Product> list = new ArrayList<>();
		list.add(product);
		Mockito.when(repository.findAll()).thenReturn(list);
		Response response = backendService.getListById(new ArrayList<>());
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

	}

	@Test
	void getListByUserId() {
		List<Product> list = new ArrayList<>();
		list.add(product);
		Mockito.when(repository.findByIdUserCreator(1L)).thenReturn(list);
		Response response = backendService.getListByUserId(1L);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
}