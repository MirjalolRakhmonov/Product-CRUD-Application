package com.mirjalolcode.springdemo.dao;

import java.util.List;

import com.mirjalolcode.springdemo.entity.Product;

public interface ProductDAO {
	public List<Product> getProducts();
}