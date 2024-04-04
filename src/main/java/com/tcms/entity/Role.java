package com.tcms.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "role", schema = "user_schema")
public class Role {
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "role_id") 
	private Long role_id;
    private String name;
}
