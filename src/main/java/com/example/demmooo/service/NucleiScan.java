package com.example.demmooo.service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NucleiScan {

    public void executeCommand(String command) throws JSchException {

        try {
            Channel channel = Connect.session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            channel.connect();
            while (true) {
                if (command.contains("rm")){
                    channelStatus(channel);
                }
                if (channel.isClosed()) {
                    break;
                }
            }
            channel.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void channelStatus(Channel channel) {
        if (channel.isEOF()) {
            System.out.println("true");
        }else
            System.out.println("false");
    }
}
