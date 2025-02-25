package com.sprk.model;

public class Listings {

	int id;
	
	String title;
	
	String descriptions;
	
	String imageUrl;
	
	double price;

	public Listings() {
		
		// default constructor
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Listings(int id, String title, String descriptions, String imageUrl, double price) {
		super();
		this.id = id;
		this.title = title;
		this.descriptions = descriptions;
		this.imageUrl = imageUrl;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Listings [id=" + id + ", title=" + title + ", descriptions=" + descriptions + ", imageUrl=" + imageUrl
				+ ", price=" + price + "]";
	}
	
	
}