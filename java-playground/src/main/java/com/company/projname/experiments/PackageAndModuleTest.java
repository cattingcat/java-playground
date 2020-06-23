package com.company.projname.experiments;

public class PackageAndModuleTest {
    public static void main(final String[] args) {
        final var packages = Package.getPackages();
        for(var i : packages) {
            System.out.println(i);
        }

        final var currModule = PackageAndModuleTest.class.getModule();
        System.out.println(currModule.getName());

        currModule.getLayer().configuration();
    }
}
