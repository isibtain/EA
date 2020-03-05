package model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.IProductService;
import service.ProductService;
public class HelloApp {
public static void main(String[] args) {
	
	
	
	ApplicationContext context = new
			 ClassPathXmlApplicationContext("springconfig.xml");
			IProductService productService =
			 context.getBean("productService", IProductService.class);
	
			Product product1 = productService.getProduct(423);
			if (product1 != null) {
				System.out.println(product1.toString());
			}
			Product product2 = productService.getProduct(239);
			if (product2 != null) {
				System.out.println(product2.toString());
			}
			
			
			System.out.println("we have " + productService.getNumberInStock(423)
			 + " product(s) with productNumber 423 in stock");
			System.out.println("we have " + productService.getNumberInStock(239)
			 + " product(s) with productNumber 239 in stock"); 

			
			

		}

	
	
		/*
		 * ConfigurableApplicationContext context = new
		 * ClassPathXmlApplicationContext("springconfig.xml"); Greeting greetingService
		 * = context.getBean("greetingService", Greeting.class);
		 * greetingService.sayHello();
		 */ }
