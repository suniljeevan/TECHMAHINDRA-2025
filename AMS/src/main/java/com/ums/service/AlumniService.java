package com.ums.service;

import java.util.List;
import com.ums.model.Alumni;

public interface AlumniService {
    Alumni insertAlumni(Alumni a);
    Alumni updateAlumni(Alumni a, int id);
    void deleteAlumni(int id);
    List<Alumni> fetchAllAlumni();
    Alumni fetchAlumniById(int id);
    Alumni fetchAlumniByEmail(String email);
    Alumni fetchAlumniByUsername(String username);
    Alumni authenticate(String username, String password);
}
