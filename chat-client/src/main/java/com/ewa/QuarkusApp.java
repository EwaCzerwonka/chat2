package com.ewa;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

import java.util.UUID;

public class QuarkusApp implements QuarkusApplication {
    @Override
    public int run(String... args) {
        String name;
        if(args.length > 0) {
            name = args[0];
        } else{
            name = UUID.randomUUID().toString();
        }
        new ChatClient(name);
        Quarkus.waitForExit();
        return 0;
    }
}
