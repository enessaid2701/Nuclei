package com.example.demmooo.repository;

import com.example.demmooo.model.ScanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanRepository extends CrudRepository<ScanEntity, Long> {
}
