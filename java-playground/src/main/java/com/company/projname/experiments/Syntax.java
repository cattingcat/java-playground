package com.company.projname.experiments;

import org.slf4j.LoggerFactory;

public class Syntax {
    public static void main(String[] args) throws Exception {
        final var logger = LoggerFactory.getLogger(Syntax.class);
        final int[] arr = new int[55];
        final int[] arr2 = {1,2,3,4,5};
        final int l = arr.length;
        final String[] anotherArr = new String[l];
        anotherArr[1] = "kek";

        final byte b = 0b1100110;
        final int i = b << 1;
        final var str = Integer.toBinaryString(i);
        logger.info("shifted: {}", str);


        logger.info("""
            arr 1 = {}; 
            anotherArr 1 = {}, 
            anotherArr 2 = {}""", arr[1], anotherArr[1], anotherArr[2]);

        logger.info("Multi arr test:");
        final int[][] multiArr = new int[3][3];
        for(var arri : multiArr) {
            for(var a : arri) {
                logger.info("{}", a);
            }
        }

        System.out.println("Hello");

        breakTest();

        continueTest();
    }

    private static void breakTest() {
        fst: {
            snd: {
                thr: {
                    if (5 > 3) break snd;
                }
                System.out.println("end of snd;");
            }
            System.out.println("end of fst");
        }
    }

    private static void continueTest() {
        firstLoop: for(var i = 0; i < 10; ++i) {
            System.out.println();
        for(var j = 0; j < 10; ++j) {
                if(j > i) continue firstLoop;

                System.out.print(j);
            }

        }
    }

    static void testDefaultVisibility() {}
}
