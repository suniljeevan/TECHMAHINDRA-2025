package com.ttms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttms.model.Section;
import com.ttms.repository.SectionRepository;

@Service
public class SectionServiceImpl implements SectionService{
	
	@Autowired
	private SectionRepository sectionRepo;
	
	public String upsert(Section section) {
		sectionRepo.save(section);
		return "Success";
	}
	
	public Section getById(Integer secid) {
		Optional<Section> findById= sectionRepo.findById(secid);
		
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
	
	public List<Section> getAllSections() {
		return sectionRepo.findAll();
	}
	
	public String deleteById(Integer secid) {
		if(sectionRepo.existsById(secid)) {
			sectionRepo.deleteById(secid);
			return "Deletion success";
		} else {
			return "No Data found";
		}
	}
}