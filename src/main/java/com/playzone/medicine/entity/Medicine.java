package com.playzone.medicine.entity;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.playzone.medicine.audit.AuditListener;

import lombok.Data;

@Entity @EnableJpaAuditing
@EntityListeners(AuditListener.class)
@Table(schema = "medicines")
@Data
public class Medicine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(max = 50)
	private String name;
	
	@NotBlank
	@Size(max = 70)
	private String composition;
	
	@NotBlank
	@Size(max = 100)
	private String manufacturer;
	
	private Integer price;
	
	@JsonIgnore
	private String operation;
	@JsonIgnore
	private String timestamp;
	
	//JPA hooks for audit
	
	@PrePersist
    public void onPrePersist() {audit("NEW");}
      
    @PreUpdate
    public void onPreUpdate() {audit("UPD");}
      
    @PreRemove
    public void onPreRemove() {audit("DEL");}
    
    private void audit(String operation) {
        setOperation(operation);
        setTimestamp(ZonedDateTime.now().toString());
    }
	
}
