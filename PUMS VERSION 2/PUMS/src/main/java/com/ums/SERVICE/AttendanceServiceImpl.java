package com.ums.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ums.MODEL.Attendance;
import com.ums.REPOSITORY.AttendanceRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	 @Autowired
	    private AttendanceRepository repo;
	    public List<Attendance> getAll() { return repo.findAll(); }
	    public void save(Attendance a) { repo.save(a); }
	    public Attendance get(Long id) { return repo.findById(id).orElse(null); }
	    public void delete(Long id) { repo.deleteById(id); }
}
