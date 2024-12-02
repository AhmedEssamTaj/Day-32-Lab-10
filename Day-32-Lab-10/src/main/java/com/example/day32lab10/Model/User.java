package com.example.day32lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.web.bind.annotation.RequestBody;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 5, message = "name must be more then 4 ")
    @Column(columnDefinition = "varchar(25) not null")
    @Pattern(regexp = "^[a-z |A - Z]+$", message = "name must only contain letters")
    @Check(constraints = "CHAR_LENGTH(name) > 4 AND name REGEXP '^[a-zA-Z]+$'")
    private String name;

    @Email(message = "email must follow a valid format")
    @Column(columnDefinition = "varchar(25) unique")
    private String email;

    @NotEmpty(message = "password cannot be null")
    @Column(columnDefinition = "varchar(25) not null")
    private String password;

    @NotNull(message = "age cannot be null")
    @Min(value = 21, message = "age must be more then 21")
    @Column(columnDefinition = "int not null")
    @Check(constraints = "age > 21")
    private Integer age;

    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$", message = "role must be either job seeker or employer")
    @Column(columnDefinition = "varchar(10) not null")
    @Check(constraints = "role IN ('JOB_SEEKER', 'EMPLOYER')")
    private String role;


}
