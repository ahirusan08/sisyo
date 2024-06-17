//遠藤
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
@Table(name="rentals")
@Data
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "book_id")
	private Integer bookId;
	@Column(name = "rental_date")
	private LocalDateTime rentalDate;
	@Column(name = "limit_date")
	private LocalDateTime limitDate;
	@Column(name = "return_date")
	private LocalDateTime returnDate;
	
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
	
	public Rental() {
		
	}

	public Rental(Integer userId, Integer bookId) {
		this.userId = userId;
		this.bookId = bookId;
	
		
	}
	
	
	
	
	
	
	
	
	

}
