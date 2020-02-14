package org.yangxin.test.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * ip
 *
 * @author yangxin
 * 2020/02/14 11:12
 */
public class IPTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost: " + localHost);
        System.out.println("address: " + Arrays.toString(localHost.getAddress()));
        System.out.println("canonical: " + localHost.getCanonicalHostName());
        System.out.println("hostAddress: " + localHost.getHostAddress());
        System.out.println("hostName: " + localHost.getHostName());
    }
}
