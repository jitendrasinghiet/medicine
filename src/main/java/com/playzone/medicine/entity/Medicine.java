package com.playzone.medicine.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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
	private String name;
	private String composition;
	private String manufacturer;
	private Integer price;
	
	private String operation;
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
