package com.techm.rpm.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.rpm.MODEL.Faculty;
import com.techm.rpm.MODEL.Project;
import com.techm.rpm.REPOSITORY.ProjectRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository repo;
	
	@Autowired
    private HttpSession session;

	public List<Project> getAllProjects() {
		return repo.findAll();
	}

	public Project saveProject(Project project) {
		return repo.save(project);
	}

	public void deleteProject(String pid) {
		repo.deleteById(pid);
	}

	public Project getProjectById(String pid) {
		return repo.findById(pid).orElse(null);
	}

	public List<Project> fetchFacultyProjects(int facultyId) {
		return repo.findByFaculty_FacultyId(facultyId);
	}

	public long getTotalProjects() {
		return repo.getTotalProjects();
	}

	public long getCompletedProjects() {
		return repo.getCompletedProjects();
	}

	public long getFundedProjects() {
		return repo.getFundedProjects();
	}

	public double getTotalFundedAmount() {
		Double amount = repo.getTotalFundedAmount();
		return amount != null ? amount : 0.0;
	}
	
	public long countCseProjects() {
		return repo.countCseProjects();
	}
	
	public long countEceProjects() {
		return repo.countEceProjects();
	}
	
	public long countEeProjects() {
		return repo.countEeProjects();
	}
	
	public long countMeProjects() {
		return repo.countMeProjects();
	}
	
	public long countCeProjects() {
		return repo.countCeProjects();
	}
	
	public long countInhouseProjects() {
		return repo.countInhouseProjects();
	}
	

    private int getFacultyIdFromSession() {
        Object facultyObj = session.getAttribute("faculty");
        if (facultyObj instanceof Faculty) {
            return ((Faculty) facultyObj).getFacultyId();
        } else {
            throw new IllegalStateException("Faculty not found in session");
        }
    }

    public long getTotalfacultyProjects() {
        return repo.getTotalProjectsByFaculty(getFacultyIdFromSession());
    }

    public long getCompletedfacultyProjects() {
        return repo.getCompletedProjectsByFaculty(getFacultyIdFromSession());
    }

    public long getFundedfacultyProjects() {
        return repo.getFundedProjectsByFaculty(getFacultyIdFromSession());
    }

    public Double getTotalfacultyFundedAmount() {
        return repo.getTotalFundedAmountByFaculty(getFacultyIdFromSession());
    }

    public long countfacultyCseProjects() {
        return repo.countCseProjectsByFaculty(getFacultyIdFromSession());
    }

    public long countfacultyEceProjects() {
        return repo.countEceProjectsByFaculty(getFacultyIdFromSession());
    }

    public long countfacultyEeProjects() {
        return repo.countEeProjectsByFaculty(getFacultyIdFromSession());
    }

    public long countfacultyMeProjects() {
        return repo.countMeProjectsByFaculty(getFacultyIdFromSession());
    }

    public long countfacultyCeProjects() {
        return repo.countCeProjectsByFaculty(getFacultyIdFromSession());
    }

    public long countfacultyInhouseProjects() {
        return repo.countInhouseProjectsByFaculty(getFacultyIdFromSession());
    }
	
	

}
