package ro.stefanprisca.distsystems.app4.ejb.models;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@ManagedBean
public class Book implements Serializable {
	@Transient
	private static final long serialVersionUID = 2426654026445485540L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Publisher pub;
	private String name;
	private String author;
	private int quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	private String price;

	public Publisher getPub() {
		return pub;
	}

	public void setPub(Publisher pub) {
		this.pub = pub;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
