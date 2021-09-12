package com.project.shop.order.dto;

import com.project.shop.order.entity.ProductsOrder;

public class ProductsOrderedDTO {

	String buyerid;
	String prodid;
	String sellerid;
	Integer quantity;

	public ProductsOrderedDTO() {
		super();
	}

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public String getSellerid() {
		return sellerid;
	}

	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	public String getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}

	public static ProductsOrderedDTO valueOf(ProductsOrder prod) {
		ProductsOrderedDTO productOrderDTO = new ProductsOrderedDTO();
		
		productOrderDTO.setBuyerid(prod.getBuyerid());
		productOrderDTO.setProdid(prod.getProdid());
		productOrderDTO.setQuantity(prod.getQuantity());
		productOrderDTO.setSellerid(prod.getSellerid());
		return productOrderDTO;
		
	}

	@Override
	public String toString() {
		return "ProductsorderedDTO [buyerid=" + buyerid + ", prodid=" + prodid + ", sellerid=" + sellerid
				+ ", quantity=" + quantity + "]";
	}
	
}
