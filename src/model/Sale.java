package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Sale {
	String client;
	ArrayList<Product> products;
	double amount;

	public Sale(String client, ArrayList<Product> products, double amount) {
		super();
		this.client = client;
		this.products = products;
		this.amount = amount;
	}

	public String getClient() {
		return client;
	}
        
	public void setClient(String client) {
		this.client = client;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
            
               String mensaje = "Sale [client=" + client + ", Products: ";
               
               for(Product product : products){
                    mensaje += product.toString();              
               }
               
               mensaje += ", amount=" + amount + "]";
               
               return mensaje;
	}

       
}