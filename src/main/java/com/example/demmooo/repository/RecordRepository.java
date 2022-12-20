package com.example.demmooo.repository;

import com.example.demmooo.model.Records;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends CrudRepository<Records, Long> {
}
