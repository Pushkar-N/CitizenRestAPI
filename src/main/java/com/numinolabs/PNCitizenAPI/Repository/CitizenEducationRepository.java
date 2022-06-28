package com.numinolabs.PNCitizenAPI.Repository;

import com.numinolabs.PNCitizenAPI.Model.CitizenEducation;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface CitizenEducationRepository extends JpaRepository<CitizenEducation, Long> {

    public CitizenEducation findByCitizenId(Long id);

    @Transactional
    void deleteByCitizenId(Long id);
}
