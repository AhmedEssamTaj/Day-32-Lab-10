package com.example.day32lab10.Controller;

import com.example.day32lab10.ApiResponse.ApiResponse;
import com.example.day32lab10.Model.JobApplication;
import com.example.day32lab10.Service.JobApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-application")
@AllArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping("/get-all")
    public ResponseEntity getAllJobApplications() {
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplication());
    }

    @PostMapping("/apply/{userId}/{jobId}")
    public ResponseEntity applyToJob(@PathVariable Integer userId, @PathVariable Integer jobId) {

        int result = jobApplicationService.applyToJob(userId, jobId);
        if (result == 1) {
            return ResponseEntity.status(200).body(new ApiResponse("applied to job successfully"));
        }
        if (result == -1) {
            return ResponseEntity.status(400).body(new ApiResponse("job post does not exist"));
        }
        if (result == 0) {
            return ResponseEntity.status(400).body(new ApiResponse("user does not exist"));
        }
        if (result == -2) {
            return ResponseEntity.status(400).body(new ApiResponse("user is employer application failed"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("something went wrong"));

    }

    @DeleteMapping("/delete/{jobApplicationId}")
    public ResponseEntity deleteJobApplication (@PathVariable Integer jobApplicationId) {
        boolean result = jobApplicationService.deleteJobApplication(jobApplicationId);
        if (result) {
            return ResponseEntity.status(200).body(new ApiResponse("job application successfully deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("job application does not exist"));
    }
}
