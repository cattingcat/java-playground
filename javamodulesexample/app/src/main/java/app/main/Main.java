package app.main;

import coredto.UserDto;
import core.MyLogger;

import java.util.ServiceLoader;

public class Main {
    public static void main(final String[] args) {
        final var userDto = new UserDto();

        // Provided and used via module-infos
        final var loggerLoader = ServiceLoader.load(MyLogger.class);
        final var loggerOptional = loggerLoader.findFirst();
        if(loggerOptional.isEmpty()) {
            System.out.println("Not provided");
        } else {
            loggerOptional.get().foo();
        }
    }
}
