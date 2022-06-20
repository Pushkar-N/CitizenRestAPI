package com.numinolabs.PNCitizenAPI.Repo;

import com.numinolabs.PNCitizenAPI.Model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepo extends JpaRepository<Citizen,Long> {

}
