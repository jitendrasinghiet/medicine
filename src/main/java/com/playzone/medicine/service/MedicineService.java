package com.playzone.medicine.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.playzone.medicine.entity.Medicine;

public interface MedicineService {
	
	List<Medicine> search(String query);
	List<Medicine> getAll();
	Medicine getByID(Long id);
	Medicine create(Medicine p);
	void update(Medicine p, Long id);
	void delete(Long id);

	Page<Medicine> getAllByPage(Pageable p);
	List<Medicine> getAllByPrice(Integer price, Pageable p);
	
}
