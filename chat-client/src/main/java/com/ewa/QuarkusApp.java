package com.ewa;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

import java.util.UUID;

public class QuarkusApp implements QuarkusApplication {
    @Override
    public int run(String... args) {
        String name = (args != null && args.length > 0) ? args[0] : UUID.randomUUID().toString();
        ChatClient.clientName = name;
        Quarkus.waitForExit();
        return 0;
    }
}
