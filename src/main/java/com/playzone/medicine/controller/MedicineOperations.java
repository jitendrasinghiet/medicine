package com.playzone.medicine.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.playzone.medicine.entity.Medicine;

@RequestMapping("/medicine")
public interface MedicineOperations {

	@GetMapping("/search")
    public List<Medicine> search(@RequestParam(value = "query") String search);

	@GetMapping("")
	public List<Medicine> retrieveAllMedicines();
	
	@GetMapping("/page/{num}/{size}") 
	public Page<Medicine> retrieveAllMedicines(@PathVariable int num, @PathVariable int size);

	@GetMapping("/{id}")
	public Medicine retrieveMedicine(@PathVariable long id);

	@DeleteMapping("/{id}")
	public void deleteMedicine(@PathVariable long id);

	@PostMapping("")
	public ResponseEntity<Object> createMedicine(@RequestBody Medicine medicine);
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateMedicine(@RequestBody Medicine medicine, @PathVariable long id);
}
