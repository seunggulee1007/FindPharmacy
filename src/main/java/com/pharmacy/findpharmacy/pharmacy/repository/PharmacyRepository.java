package com.pharmacy.findpharmacy.pharmacy.repository;

import com.pharmacy.findpharmacy.pharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

}
