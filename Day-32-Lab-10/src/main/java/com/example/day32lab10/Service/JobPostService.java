package com.example.day32lab10.Service;

import com.example.day32lab10.Model.JobPost;
import com.example.day32lab10.Repository.JobPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPost() {
        return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost) {
        jobPostRepository.save(jobPost);
    }

    public boolean updateJobPost(Integer id, JobPost jobPost) {
        for (JobPost jp : jobPostRepository.findAll()) {
            if (jp.getId().equals(id)) {
                jp.setTitle(jobPost.getTitle());
                jp.setDescription(jobPost.getDescription());
                jp.setLocation(jobPost.getLocation());
                jp.setSalary(jobPost.getSalary());
                jp.setPosting_date(jobPost.getPosting_date());
                jobPostRepository.save(jp);
                return true;
            }
        }
        return false;
    }

    public boolean deleteJobPost(Integer id) {
        for (JobPost jp : jobPostRepository.findAll()) {
            if (jp.getId().equals(id)) {
                jobPostRepository.delete(jp);
                return true;
            }
        }
        return false;
    }

}
