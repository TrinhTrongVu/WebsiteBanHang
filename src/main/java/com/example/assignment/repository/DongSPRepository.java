package com.example.assignment.repository;

import com.example.assignment.entity.DongSP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DongSPRepository extends JpaRepository<DongSP, UUID> {
}
