package com.ums.SERVICE;

import java.util.List;
import java.util.Optional;

import com.ums.MODEL.Faculty;

public interface FacultyService {
    public List<Faculty> getAll() ;
    public void save(Faculty faculty) ;
    public Faculty get(Long id) ;
    public void delete(Long id) ;
}
