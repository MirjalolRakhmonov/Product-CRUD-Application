package com.mirjalolcode.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mirjalolcode.springdemo.dao.ProductDAO;
import com.mirjalolcode.springdemo.entity.Product;
import com.mirjalolcode.springdemo.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	// product service needs to be injected
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public String listProducts(Model theModel) {
		// get product from the dao
		List<Product>theProducts=productService.getProducts();
		
		// add products to the model
		theModel.addAttribute("products", theProducts);
		
		return "list-products";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		// create model attribute to bind form data
				Product theProduct=new Product();

				theModel.addAttribute("product", theProduct);
				
		return "product-form"; 
	}
	
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("product") Product theProduct) {
		// save the customer using our service
		productService.saveProduct(theProduct);
		
		return "redirect:/product/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("productId") int theId, Model theModel) {
		// get the product from service
		Product theProduct=productService.getProduct(theId);
		
		// set product as a model attribute to pre-populate the form
		theModel.addAttribute("product", theProduct);
		
		// send over to form
		return "product-form";
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("productId") int theId) {
		// delete product
		productService.deleteProduct(theId);
		
		return "redirect:/product/list";
	}
}
