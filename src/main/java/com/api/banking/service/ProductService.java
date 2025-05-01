package com.api.banking.service;

import java.util.List;

import com.api.banking.entity.Product;
import com.api.banking.repository.ProductRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductService {

	@Inject
	ProductRepository repo;
	
	public List<Product> getAllProduct()
	{
		return repo.listAll();
	}
	
	public Product getProductById(int id)
	{
		long id_to_find = id;
		return repo.findById(id_to_find);
	}
	
	@Transactional
	public void addProduct(Product p)
	{
		repo.persist(p);
	}
}
