package com.example.demmooo.service;

import com.jcraft.jsch.*;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@Service
public class Connect{

    static Session session ;

    public void connect() {

        try {
            JSch jSch = new JSch();

            Session session = jSch.getSession("kali", "192.168.1.102", 22);
            session.setDaemonThread(true);
            session.setTimeout(3600000);
            session.setServerAliveInterval(15000);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("kali");
            try {
                session.connect();

                Channel channel = session.openChannel("exec");
                ((ChannelExec)channel).setCommand("ls");
                channel.setInputStream(null);
                InputStream input = channel.getInputStream();
                channel.connect();

                channel.disconnect();
                session.disconnect();
            } catch (JSchException e){
                System.out.println(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (JSchException e) {
            //e.printStackTrace();
            System.out.println(e);
            System.err.println("Bağlantı başarısız.");
        }
    }
}
