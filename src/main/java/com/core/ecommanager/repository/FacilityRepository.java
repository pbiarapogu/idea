package com.core.ecommanager.repository;

import com.core.ecommanager.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility,Long> {
    List<Facility> findAll();
}
