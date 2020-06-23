package com.company.projname.experiments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RuntimeTest {
    public static void main(final String[] args) {
        final var r = Runtime.getRuntime();
        System.out.println("total mem: " + r.totalMemory());
        System.out.println("free  mem: " + r.freeMemory());
        System.out.println("total processors: " + r.availableProcessors());

        try {
            final var proc = r.exec("ls -al");
            final var isr = new InputStreamReader(proc.getInputStream());
            final var br = new BufferedReader(isr);
            while(br.ready()) {
                final var line = br.readLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Runs GC
        r.gc();
    }
}
