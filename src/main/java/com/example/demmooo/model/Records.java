package com.example.demmooo.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Records extends BaseEntity
{
    private int vuln_ID;
    private String name;
    private String type;
    private String desc;
    private String severity;
    private String CWE_ID;

}

