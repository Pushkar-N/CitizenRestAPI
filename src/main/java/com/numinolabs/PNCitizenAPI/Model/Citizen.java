package com.numinolabs.PNCitizenAPI.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "citizen_id")
    private long id;

    @Column(name = "full_name", length = 255, nullable = false)
    @NotNull
    private String fullName;

    @Column(name = "aadhar_id", length = 16, nullable = false)
    @Size(min = 16, max = 16,message = "Aadhar Id must be 16 digits")
    @NotNull(message = "Aadhar Id is Mandatory")
    private String aadharId;

    @Column(name = "date_of_birth",nullable = false)
    @NotNull
    private Date dateOfBirth;


    @Column(name = "state",length = 255, nullable = false)
    @NotNull
    @Size(max = 255)
    private String state;

    @Column(name = "pincode",length = 10, nullable = false)
    @Size(min = 2, max = 10)
    @NotNull
    private String pincode;

    @Column(name = "gender",nullable = false)
    @NotNull
    private String gender;

    @Column(name = "email")
    @Email(message = "Please enter a valid email address")
    private String email;

    @Column(name = "primary_phone",nullable = false,length = 15)
    @NotNull
    @Size(max = 15)
    private Long primaryPhone;

    @Column(name = "other_phone",length = 15)
    @Size(max = 15)
    private Integer otherPhone;

    @Column(name = "address",nullable = false )
    @NotNull
    private String address;
}
