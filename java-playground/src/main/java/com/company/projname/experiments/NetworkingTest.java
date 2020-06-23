package com.company.projname.experiments;

import java.net.*;

public class NetworkingTest {
    public static void main(final String[] args) throws Exception {
        final var s = "google.com";
        final var addrs = InetAddress.getAllByName(s);
        final var addrs6 = Inet6Address.getAllByName(s);

        for(var i : addrs) {
            System.out.println(i.getCanonicalHostName());
        }

        for(var i : addrs6) {
            System.out.println(i.getCanonicalHostName());
        }


        final var addr = addrs6[0];
        final var sock = new Socket(addr, 80);

        System.out.println(sock.isConnected());
    }
}
