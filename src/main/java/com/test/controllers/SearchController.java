package com.test.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.beans.Product;
import com.test.repository.ProductRepository;


@Controller
public class SearchController {
	/**
	 * @Autowired invoke the class ProductRepository is dependency injected
	 * 
	 * @RequestParam expect an input of the user from the view, in this 
	 * case the user's product search (e.g water, protein, etc) and it is assigned
	 * to the variable "search"
	 * 
	 * @Model any info of the object can be displayed to the view 
	 *
	 */
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/search")
	public Callable<String> search(@RequestParam("search") String search, Model model, HttpServletRequest request) {
		System.out.println("in search controller");
		System.out.println("Search criteria: "+search);
		System.out.println("Async supported: "+request.isAsyncSupported());
		System.out.println("Thread from the servlet container: "+ Thread.currentThread().getName());
		return()->{
			Thread.sleep(3000);
			System.out.println("Thread from the servlet container: "+ Thread.currentThread().getName());
			List<Product> products = new ArrayList<>();
			products = productRepository.searchByName(search);
			model.addAttribute("products", products);
			return "search";
		};
	}
	
	
}
