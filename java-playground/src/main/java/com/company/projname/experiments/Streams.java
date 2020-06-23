package com.company.projname.experiments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Streams {
    public static void main(String[] args) throws IOException {
        final var o = System.out;
        final var i = System.in;
        try(final var br = new BufferedReader(new InputStreamReader(i))) {
            final var line = br.readLine();

            o.println(line);
        }
    }
}
