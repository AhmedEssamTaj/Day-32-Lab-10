package com.example.day32lab10.Service;

import com.example.day32lab10.Model.JobApplication;
import com.example.day32lab10.Model.User;
import com.example.day32lab10.Repository.JobApplicationRepository;
import com.example.day32lab10.Repository.JobPostRepository;
import com.example.day32lab10.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;

    public List<JobApplication> getAllJobApplication() {
        return jobApplicationRepository.findAll();
    }

    public int applyToJob(Integer jobPostId, Integer userId) {
        JobApplication jobApplication = new JobApplication();

        boolean userExist = userRepository.existsById(userId); // user exist ?

        boolean jobPostExist = jobPostRepository.existsById(jobPostId);
        if (!userExist) {
            return 0; // user does not exist
        }
        if (!jobPostExist) {
            return -1; // job does not exist
        }

        User user = userRepository.findById(userId).get();
        if (user.getRole().equals("EMPLOYER")) {
            return -2; // user is employer !!
        }

        jobApplication.setJob_id(jobPostId);
        jobApplication.setUser_id(userId);
        jobApplicationRepository.save(jobApplication);
        return 1; // applied to job successfully


    }

    public boolean deleteJobApplication(Integer applicationId) {
        boolean jopApplicationExist = jobApplicationRepository.existsById(applicationId);
        if (!jopApplicationExist) {
            return false;
        }
        jobApplicationRepository.deleteById(applicationId);
        return true;
    }
}
