package com.example.awstest.repository;

import com.example.awstest.domain.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StageRepository extends JpaRepository<Stage, Long> {
}
