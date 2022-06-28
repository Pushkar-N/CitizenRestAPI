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
    private String fullName;

    @NotNull(message = "Aadhar Id is Mandatory")
    @Size(min = 16, max = 16,message = "Aadhar Id must be 16 digits")
    @Column(name = "aadhar_id", length = 16, nullable = false)
    private String aadharId;

    @Column(name = "date_of_birth",nullable = false)
    private Date dateOfBirth;

    @Size(max = 255)
    @Column(name = "state",length = 255, nullable = false)
    private String state;

    @Size(min = 2, max = 10)
    @Column(name = "pincode",length = 10, nullable = false)
    private String pincode;

    @NotNull
    @Column(name = "gender",nullable = false)
    private String gender;

    @Email(message = "Please enter a valid email address")
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(max = 15)
    @Column(name = "primary_phone",nullable = false,length = 15)
    private Long primaryPhone;

    @Size(max = 15)
    @Column(name = "other_phone",length = 15)
    private Integer otherPhone;

    @NotNull
    @Column(name = "address",nullable = false )
    private String address;
}
