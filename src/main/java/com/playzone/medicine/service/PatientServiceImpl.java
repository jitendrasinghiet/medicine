package com.playzone.medicine.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.playzone.medicine.entity.Medicine;
import com.playzone.medicine.exception.MedicineNotFoundException;
import com.playzone.medicine.repository.MedicineRepository;
import com.playzone.medicine.search.SpecificationBuilder;

@Service
public class PatientServiceImpl implements MedicineService{
	
	@Autowired
	private MedicineRepository patientRepository;

	@Override
	public List<Medicine> getAll() {
		return patientRepository.findAll();
	}

	@Override
	public Medicine getByID(Long id) {
		Optional<Medicine> Patient = patientRepository.findById(id);
		if (!Patient.isPresent())
			throw new MedicineNotFoundException(HttpStatus.NOT_FOUND.name(),"id-" + id);
		return Patient.get();
	}

	@Override
	public Medicine create(Medicine p) {
		return patientRepository.save(p);
	}

	@Override
	public void update(Medicine p, Long id) {
		getByID(id);//check exists
		p.setId(id);		
		patientRepository.save(p);		
	}

	@Override
	public void delete(Long id) {
		patientRepository.deleteById(id);		
	}

	@Override
	public List<Medicine> search(String query) {
		 SpecificationBuilder builder = new SpecificationBuilder();
	        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
	        Matcher matcher = pattern.matcher(query + ",");
	        while (matcher.find()) {
	            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
	        }
	        
	        Specification<Medicine> spec = builder.build();
	        return patientRepository.findAll(spec);
	}	

}
