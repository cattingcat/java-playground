package com.company.projname.experiments;

import java.io.*;

public class IoTests {
    public static void main(final String[] args) throws Exception {
        final var file = new File("/Users/mark.martynov/Desktop/S1-112A.djvu");
        System.out.println(file.isDirectory());

        final var desktop = new File("/Users/mark.martynov/Desktop");
        final var filtered = desktop.listFiles((f, n) -> {
            return n.endsWith(".pdf") || n.endsWith(".djvu");
        });

        for(var f : filtered) {
            System.out.println(f.getName());
        }

        testByteStream();

        testObjStream();
    }

    void testTryStatement() {
        try(final var t = new MyAutoCloseable()) {
            System.out.println(t);
        }
    }

    static void testByteStream() throws IOException {
        final var s = "qweasdqweasdzxczxcrtyrty123123";
        final var bs = s.getBytes();
        final var bytesStream = new ByteArrayInputStream(bs);

        {
            int c = bytesStream.read();
            while (c != -1) {
                System.out.println(c);
                c = bytesStream.read();
            }
        }
        {
            bytesStream.reset();
            System.out.println("Buffered:");
            final var buffered = new BufferedInputStream(bytesStream, 3);
            int c = buffered.read();
            while (c != -1) {
                System.out.println(c);
                c = buffered.read();
            }
        }

//        {
//            final var fis = new FilterInputStream(bytesStream);
//            int c = bytesStream.read();
//            while (c != -1) {
//                System.out.println(c);
//                c = bytesStream.read();
//            }
//        }
    }

    static void testObjStream() throws Exception {
        System.out.println("testObjStream");
        final var out = new ByteArrayOutputStream(500);

        try (final var objStream = new ObjectOutputStream(out)) {
            final var testObj = new MySomeObj(1, "a", 2);
            objStream.writeObject(testObj);
        }

        final var arr = out.toByteArray();
        for(var i : arr) {
            System.out.println(i);
        }

        final var s = String.valueOf(arr);
        System.out.println(s);


        final var in = new ByteArrayInputStream(arr);
        try(final var inputObjStream = new ObjectInputStream(in)) {
            final var o = (MySomeObj) inputObjStream.readObject();
            System.out.println(o.s);
        }
    }
}

class MySomeObj implements Serializable {
    public final int i;
    public final String s;
    public final transient int j;

    MySomeObj(final int i, final String s, final int j) {
        this.i = i;
        this.j = j;
        this.s = s;
    }
}

class NonSerializableClass {}
class MySomeObj2 implements Serializable {
    public final int i;
    public final String s;
    public NonSerializableClass j;

    MySomeObj2(final int i, final String s) {
        this.i = i;
        this.j = new NonSerializableClass();
        this.s = s;
    }
}

class MyAutoCloseable implements AutoCloseable {
    @Override
    public void close() {

    }
}
