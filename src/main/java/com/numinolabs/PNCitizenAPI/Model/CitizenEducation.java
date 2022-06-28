package com.numinolabs.PNCitizenAPI.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitizenEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "citizen_education_id")
    private Long id;

    @NotNull
    @Column(name = "citizen_id" , nullable = false)
    private long citizenId;

    @NotNull
    @Column(name = "education_board", nullable = false)
    @Size(max = 255)
    private String educationBoard;

    @NotNull
    @Column(name = "education_level", nullable = false)
    @Size(max = 255)
    private String educationLevel;

    @NotNull
    @Column(name = "education_specialization", nullable = false)
    @Size(max = 255)
    private String educationSpecialization;

    @NotNull
    @Column(name = "year_of_passing", nullable = false)
    @Size(max = 255)
    private Integer yearOfPassing;

    @NotNull
    @Column(name = "college_or_institute", nullable = false)
    @Size(max = 255)
    private String collegeOrInstitute;
}
