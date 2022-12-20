package com.example.demmooo.service;

import com.example.demmooo.dto.ScanDTO;
import com.example.demmooo.model.ScanEntity;
import com.example.demmooo.repository.ScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNucleiService
{
    @Autowired
    private ScanRepository scanRepository;

    public void createScan(ScanDTO scanDTO)
    {
        ScanEntity scanEntity = new ScanEntity();
        scanEntity.setScanName(scanDTO.getScanName());
        scanEntity.setTarget(scanDTO.getTarget());

        scanRepository.save(scanEntity);
    }
}
