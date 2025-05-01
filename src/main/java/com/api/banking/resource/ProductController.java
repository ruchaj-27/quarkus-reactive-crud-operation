package com.api.banking.resource;

import java.util.List;

import com.api.banking.entity.Product;
import com.api.banking.service.ProductService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {
	
	@Inject
	ProductService service;

	@GET
	@Path("/test")
	public String test()
	{
		return "I am alive";
	}
	
	@GET
	public Response getProductList()
	{
		List<Product> result = service.getAllProduct();
		if(!result.isEmpty())
			return Response.ok(result).build();
		else
			return Response.status(Status.NO_CONTENT).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getProductById(@PathParam("id") int id)
	{
			Product product = service.getProductById(id);
			if(!product.getName().isEmpty())
				return Response.ok(product).build();
			else
				return Response.status(Status.NOT_FOUND).build();
	}
	
	 @POST
	 public Response addProduct(Product p)
	 { 
		 if(!p.getName().isBlank())
		 {
			 service.addProduct(p);
			 return Response.status(Status.CREATED).entity(p).build();
		 }
		 return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	 }
	
}
