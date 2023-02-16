package edu.uniandes.miso.service;

import edu.uniandes.miso.dto.InputProductDto;
import edu.uniandes.miso.dto.ResponseService;
import edu.uniandes.miso.entity.Product;
import edu.uniandes.miso.repository.ProductRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class ProductServiceTest {

    @InjectMock
    ProductRepository repository;
    @Inject
    ProductService backendService;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setIdSport(1L);
        product.setIdProduct(1L);
        product.setIdUserCreator(3L);
        product.setDescription("des");
        product.setName("name");
    }

    @Test
    void dto(){
        product.getName();
        product.getDescription();
        product.getIdSport();

        ResponseService responseService = new ResponseService();
        responseService.setResult(new Object());
        responseService.setSuccess(true);
        responseService.setIdSport(1L);



        responseService.setMessage("msg");
        assertNotNull(responseService.toString());
        assertNotNull(product.getIdSport());
        assertNotNull(product.toString());
    }

    @Test
    void create() {
        Mockito.when(repository.save(product)).thenReturn(product);

        InputProductDto input = new InputProductDto();
        input.setDescription("des");
        input.setName("name");
        input.setIdSport(1L);
        input.setIdUserCreator(3L);
        Response response = backendService.create(input);
        assertNotNull(input.toString());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    @Test
    void createFail() {
        Mockito.when(repository.save(product)).thenReturn(product);
        InputProductDto input = new InputProductDto();
        input.setDescription("des");
        input.setIdSport(1L);
        Response response = backendService.create(input);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void get() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));
        Response response = backendService.get(1L);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void getFail() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));
        Response response = backendService.get(9L);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    void delete() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));
        Response response = backendService.delete(1L);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void deleteFail() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));
        Response response = backendService.delete(5L);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    void getAll() {
        List<Product> list = new ArrayList<>();
        list.add(product);
        Mockito.when(repository.findAll()).thenReturn(list);
        Response response = backendService.getAll();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    @Test
    void deleteOk() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));
        Response response = backendService.delete(1L);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void putOk() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(repository.save(product)).thenReturn(product);
        InputProductDto inputServiceDto = new InputProductDto();
        inputServiceDto.setName("name");
        inputServiceDto.setDescription("category");
        inputServiceDto.setIdSport(1L);
        inputServiceDto.setIdUserCreator(5L);
        Response response = backendService.put(1L,inputServiceDto);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    @Test
    void putFail() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(repository.save(product)).thenReturn(product);
        InputProductDto inputServiceDto = new InputProductDto();
        inputServiceDto.setName("");
        inputServiceDto.setDescription("category");
        inputServiceDto.setIdUserCreator(1L);
        Response response = backendService.put(5L,inputServiceDto);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}