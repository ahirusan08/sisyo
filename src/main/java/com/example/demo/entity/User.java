//担当者：菊水
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
@Table(name="users")
@Data

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String password;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	//コンストラクタ要注意
	
	public User () {
		
	}

	public User(Integer id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	
}
