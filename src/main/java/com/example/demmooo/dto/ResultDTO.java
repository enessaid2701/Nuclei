package com.example.demmooo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultDTO
{
    private String name;
    private String type;
    private String desc;
    private String severity;
    private String CWE_ID;
}
