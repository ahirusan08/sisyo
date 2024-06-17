//おがわ
package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;
	private String author;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "update_at")
	private LocalDateTime updateAt;

	@Column(name = "update_by")
	private Integer updateBy;

	@Column(name = "version_no")
	private Integer versionNo;

	@Column(name = "delete_flag")
	private Integer deleteFlag;

	public Book() {

	}

	public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}

	public Book(Integer id, String title, String author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
	}

}
