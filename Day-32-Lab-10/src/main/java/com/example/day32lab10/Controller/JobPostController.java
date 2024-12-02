package com.example.day32lab10.Controller;

import com.example.day32lab10.ApiResponse.ApiResponse;
import com.example.day32lab10.Model.JobPost;
import com.example.day32lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-post")
@AllArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/get-all")
    public ResponseEntity getAllJobPosts() {
        return ResponseEntity.status(200).body(jobPostService.getAllJobPost());
    }

    @PostMapping("/add")
    public ResponseEntity addJobPost(@RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("Job Post Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id, @RequestBody @Valid JobPost jobPost, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (jobPostService.updateJobPost(id, jobPost)) {
            return ResponseEntity.status(200).body(new ApiResponse("Job Post Updated Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("no job post found with this id"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id) {
        if (jobPostService.deleteJobPost(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Job Post Deleted Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("no job post found with this id"));
    }


}
