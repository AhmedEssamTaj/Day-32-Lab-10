package com.example.day32lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Check(constraints = "salary > 0")
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title cannot be null")
    @Size(min = 5, message = "title must be more then 4")
    @Column(columnDefinition = "varchar(30) not null")
    @Check(constraints = "CHAR_LENGTH(title) > 4")
    private String title;

    @NotEmpty(message = "descreption cannot be null")
    @Column(columnDefinition = "varchar(450) not null")
    private String description;

    @NotEmpty(message = "location cannot be null")
    @Column(columnDefinition = "varchar(25) not null")
    private String location;

    @NotNull(message = "salary cannot be null")
    @Positive(message = "salary must be positive number")
    @Column(columnDefinition = "int not null")
    private Integer salary;

    @Column(updatable = false)
    @CreationTimestamp
    private String posting_date;
}
