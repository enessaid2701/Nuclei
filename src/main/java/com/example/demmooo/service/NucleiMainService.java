package com.example.demmooo.service;

import com.example.demmooo.dto.ScanDTO;
import com.example.demmooo.model.Records;
import com.jcraft.jsch.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NucleiMainService {
    static Connect cnnct = new Connect();
    public void nucleiMain(ScanDTO scanDTO) throws JSchException{
        String target = scanDTO.getTarget();
        NucleiScan nucleiScan = new NucleiScan();
        JsonPartition jsonPartition = new JsonPartition();
        cnnct.connect();

        String scan = "nuclei -u " + target + " -json -o /home/kali/results.txt";
        nucleiScan.executeCommand(scan);

        jsonPartition.JsonPartition();

    }
}
