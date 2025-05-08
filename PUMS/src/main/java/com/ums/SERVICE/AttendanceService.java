package com.ums.SERVICE;

import java.util.List;

import com.ums.MODEL.Attendance;

public interface AttendanceService {
	public List<Attendance> getAll();
	public void save(Attendance a);
	public Attendance get(Long id);
	public void delete(Long id);
	public void deleteByCourseAndDate(Long courseId, String today);
	public List<Attendance> getByCourseAndDate(Long courseId, String today);
}
