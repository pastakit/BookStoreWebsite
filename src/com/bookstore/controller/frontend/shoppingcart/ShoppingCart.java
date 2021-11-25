package com.bookstore.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.bookstore.entity.Book;

public class ShoppingCart {
	private Map<Book,Integer> cart = new HashMap<>();
	
	public void addItem(Book book) {
		if (cart.containsKey(book)) {
			Integer quantity = cart.get(book) + 1;
			cart.put(book, quantity);
		}
		else {
			cart.put(book, 1);
		}
	}
	
	public void removeItem(Book book) {
		this.cart.remove(book);
	}
	
	public int getTotalQuantity() {
		int result = 0;
		
		for(Entry<Book, Integer> item: cart.entrySet()) {
			result += item.getValue();
		}
		
		return result;
	}
	
	public double getTotalAmount() {
		double result = 0.0f;
		
		for(Entry<Book, Integer> item: cart.entrySet()) {
			result += item.getKey().getPrice() * item.getValue();
		}
		
		return result;
	}
	
	public int getTotalItems() {
		return cart.size();
	}
	
	public void clear() {
		cart.clear();
	}
	
	
	public void updateCart(int[] bookIds, int [] quantities) {
		for(int i =0;i< bookIds.length;i++) {
			Book book = new Book(bookIds[i]);
			cart.put(book, quantities[i]);
		}
	}
	
	public Map<Book, Integer> getItems(){
		return this.cart;
	}
	
}
