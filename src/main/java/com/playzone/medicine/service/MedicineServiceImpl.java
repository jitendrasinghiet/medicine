package com.playzone.medicine.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.playzone.medicine.entity.Medicine;
import com.playzone.medicine.exception.MedicineNotFoundException;
import com.playzone.medicine.repository.MedicineRepository;
import com.playzone.medicine.search.SpecificationBuilder;

@Service
public class MedicineServiceImpl implements MedicineService{
	
	@Autowired
	private MedicineRepository medicineRepository;

	@Override
	public List<Medicine> getAll() {
		return medicineRepository.findAll();
	}

	@Override
	public Medicine getByID(Long id) {
		Optional<Medicine> medicine = medicineRepository.findById(id);
		if (!medicine.isPresent())
			throw new MedicineNotFoundException(HttpStatus.NOT_FOUND.name(),"id-" + id);
		return medicine.get();
	}

	@Override
	public Medicine create(Medicine p) {
		return medicineRepository.save(p);
	}

	@Override
	public void update(Medicine p, Long id) {
		getByID(id);//check exists
		p.setId(id);		
		medicineRepository.save(p);		
	}

	@Override
	public void delete(Long id) {
		medicineRepository.deleteById(id);		
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
	        return medicineRepository.findAll(spec);
	}

	@Override
	public List<Medicine> getAllByPrice(Integer price, Pageable p) {
		// TODO Auto-generated method stub
		return medicineRepository.findAllByPrice(price, p);
	}
	
	@Override
	public Page<Medicine> getAllByPage(Pageable p) {
		return medicineRepository.findAll(p);
	}

}
