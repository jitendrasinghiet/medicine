package com.playzone.medicine.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.playzone.medicine.entity.Medicine;
import com.playzone.medicine.service.MedicineService;

@RestController
public class MedicineController implements MedicineOperations{

	@Autowired
	private MedicineService medicineService;
		
	@Override
    public List<Medicine> search(@RequestParam(value = "query") String search) {
       return medicineService.search(search);
    }

	@Override
	public List<Medicine> retrieveAllMedicines() {
		return medicineService.getAll();
	}

	@Override
	public Medicine retrieveMedicine(@PathVariable long id) {
		return medicineService.getByID(id);
	}

	@Override
	public void deleteMedicine(@PathVariable long id) {
		medicineService.delete(id);
	}

	@Override
	public ResponseEntity<Object> createMedicine(@RequestBody Medicine medicine) {
		Medicine medicineSaved = medicineService.create(medicine);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(medicineSaved.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@Override
	public ResponseEntity<Object> updateMedicine(@RequestBody Medicine medicine, @PathVariable long id) {
		medicineService.update(medicine, id);
		return ResponseEntity.noContent().build();
	}
}