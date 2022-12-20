package com.example.demmooo.service;

import com.example.demmooo.model.Records;
import com.example.demmooo.repository.RecordRepository;
import com.example.demmooo.repository.ResultRepository;
import com.jcraft.jsch.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class JsonPartition {

    @Autowired
    private RecordRepository repository;

    private int vuln_ID;
    private String name;
    private String type;
    private String desc;
    private String severity;
    private String CWE_ID;
    protected void JsonPartition() throws JSchException {
        try {
            Channel channel = Connect.session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;

            InputStream stream = sftpChannel.get("/home/kali" +
                    "/results.txt");

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                String line;
                while ((line = br.readLine()) != null) {
                    //System.out.println(line);
                    JSONObject json = new JSONObject(line);
                    Records records = new Records();

                    try {
                        name = json.getJSONObject("info").get("name").toString();
                        records.setName(json.getJSONObject("info").get("name").toString());
                        if (name.hashCode() > 0) records.setVuln_ID(name.hashCode());
                        else records.setVuln_ID(name.hashCode() * -1);
                    } catch (JSONException e) {
                        records.setName("null");
                    }

                    try {
                        records.setType(json.get("type").toString());
                    } catch (JSONException e) {
                        records.setType("null");
                    }

                    try {
                        desc = json.getJSONObject("info").get("description").toString();
                        desc = desc.replace("\n", "");
                        records.setDesc(desc);
                    } catch (JSONException e) {
                        desc = "null";
                    }

                    try {
                        CWE_ID = json.getJSONObject("info").getJSONObject("classification").getJSONArray("cwe-id").toString();
                        CWE_ID = CWE_ID.replace("[", "");
                        CWE_ID = CWE_ID.replace("]", "");
                        CWE_ID = CWE_ID.replace("\"", "");
                        records.setCWE_ID(CWE_ID);
                    } catch (JSONException e) {
                        records.setCWE_ID("null");
                    }

                    try {
                        severity = json.getJSONObject("info").get("severity").toString();
                        records.setSeverity(severity);
                    } catch (JSONException e) {
                        records.setSeverity("null");
                    }

                    repository.save(records);
                }

            } catch (IOException io) {
                System.out.println("SFTP sunucusundan dosya okunurken istisna oluştu: " + io.getMessage());
            } catch (Exception e) {
                System.out.println("Hata oluştu. " + e.getMessage());
            }

        } /*catch (JSchException e) {
            //e.printStackTrace();
            System.err.println("Bağlantı hatası.");
        } catch (SftpException e) {
            //e.printStackTrace();
            System.err.println("Dosya bulunamadı.");
        }*/ catch (SftpException e) {
            throw new RuntimeException(e);
        }
    }
}

