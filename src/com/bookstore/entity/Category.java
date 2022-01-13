package com.bookstore.entity;
// Generated Oct 8, 2021 10:15:00 AM by Hibernate Tools 5.2.10.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Category generated by hbm2java
 */
@Entity
//@Table(name = "category", catalog = "bookstoredb")
@Table(name = "category")
@NamedQueries({
	@NamedQuery(name="Category.findAll", query ="SELECT c FROM Category c ORDER BY c.name"),
	@NamedQuery(name="Category.countAll", query = "select count(*) from Category c"),
	@NamedQuery(name = "Category.findByName", query ="select c from Category c where c.name= :name"),
})
public class Category implements java.io.Serializable {

	private Integer categoryId;
	private String name;
	private Set<Book> books = new HashSet<Book>(0);

	public Category() {
	}

	
	public Category(String name) {
		this.name = name;
	}
	
	public Category(Integer id, String name) {
		this(name);
		this.categoryId = id;
		
	}

	public Category(String name, Set<Book> books) {
		this.name = name;
		this.books = books;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "category_id", unique = true, nullable = false)
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
