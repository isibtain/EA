package service;

import java.util.List;

import model.Product;

public interface IProductService {
	public Product getProduct(int productNumber);
	
	public int getNumberInStock(int productNumber);

}
