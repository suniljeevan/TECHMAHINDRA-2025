package com.ttms.service;

import java.util.List;

import com.ttms.model.Section;

public interface SectionService {
	public String upsert(Section section);
	
	public Section getById(Integer secid);
	
	public List<Section> getAllSections();
	
	public String deleteById(Integer secid);
}
