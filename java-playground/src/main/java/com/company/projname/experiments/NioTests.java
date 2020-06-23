package com.company.projname.experiments;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.file.Files;
import java.nio.file.Path;

public class NioTests {
    public static void main(final String[] args) throws Exception {
        final var bb = ByteBuffer.allocate(5);
        bb.put(new byte[] {1,2,3,4,5});

        final var file = new File("/Users/mark.martynov/Desktop/S1-112A.djvu");
        try(final var fs = new FileInputStream(file)) {
            try (final var chan = fs.getChannel()) {
                final var bytes = ByteBuffer.allocate(512000000);
                final var read = chan.read(bytes);
                System.out.println(read);
            }
        }


        final var p = Path.of("/Users/mark.martynov/Desktop/").normalize();
        System.out.println(p);

        final var dirStream = Files.newDirectoryStream(p);
        dirStream.forEach((d) -> {
            System.out.println(d);
        });


    }
}


class TestStaticCtor {
    private final int i;
    TestStaticCtor(final int i) {
        this.i = i;
    }

    static TestStaticCtor create() {
        return new TestStaticCtor(0);
    }
}
