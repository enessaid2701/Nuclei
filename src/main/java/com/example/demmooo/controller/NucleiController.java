package com.example.demmooo.controller;

import com.example.demmooo.dto.ResponseDTO;
import com.example.demmooo.dto.ScanDTO;
import com.example.demmooo.service.CreateNucleiService;
import com.example.demmooo.service.NucleiMainService;
import com.jcraft.jsch.JSchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@Controller
@RequestMapping("/nuclei")
public class NucleiController
{
    @Autowired
    private CreateNucleiService nucleiService;

    @Autowired
    private NucleiMainService nucleiMainService;

    @PostMapping("/create-nuclei")
    @ResponseBody
    public ResponseDTO createNuclei(@RequestBody ScanDTO scanDTO) throws JSchException, SQLException, IOException, ClassNotFoundException {
        nucleiMainService.nucleiMain(scanDTO);
//        nucleiService.createScan(scanDTO);
        ResponseDTO res = new ResponseDTO(true);
        return res;
    }
}
