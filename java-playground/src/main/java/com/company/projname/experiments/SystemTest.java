package com.company.projname.experiments;

import java.util.List;

public class SystemTest {
    public static void main(final String[] args) {
        // Read config.properties doesn't work
        final var propVal = System.getProperty("db.user");
        System.out.println(propVal);


        final var props = System.getProperties();
        final var keys = props.keys();


        while(keys.hasMoreElements()) {
            final var i = keys.nextElement();
            System.out.println(i);
        }


    }
}
