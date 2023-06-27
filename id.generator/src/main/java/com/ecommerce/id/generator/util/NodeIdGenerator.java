package com.ecommerce.id.generator.util;

import com.ecommerce.id.generator.common.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.Enumeration;

@Component
public class NodeIdGenerator {
    @Bean
    public Integer generatingNodeId() {
        int maxNodeVal = (int) Math.pow(2, Constants.NODE_ID_BIT_LEN);
        int nodeId;
        try {
            StringBuilder sb = new StringBuilder();
            // To generate the Node ID or Machine ID, we will use the machine’s MAC address.
            // This ensures that the Machine ID is unique for every machine.
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                // to get the Node ID component of int datatype.
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    for(int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X", mac[i]));
                    }
                }
            }
            nodeId = sb.toString().hashCode();
        } catch (SocketException ex) {
            //in case of exception get a random number limited by max node size
            nodeId = (int) (new SecureRandom().nextInt() % Math.pow(2, 10));
        }
        nodeId = nodeId & maxNodeVal;
        return nodeId;
    }
}
