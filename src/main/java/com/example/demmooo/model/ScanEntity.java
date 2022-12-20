package com.example.demmooo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class ScanEntity extends BaseEntity
{
    private String target;

    @Column(name = "scan_name")
    private String scanName;
}
