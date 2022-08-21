package com.ewa.messages;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log
@ApplicationScoped
@RequiredArgsConstructor
public class ServerMsgProducer implements Runnable {

    @Inject
    ServerWorker worker;
    private final ExecutorService scheduler = Executors.newSingleThreadExecutor();


    void onStart(@Observes StartupEvent ev) {
        scheduler.submit(this);
    }

    void onStop(@Observes ShutdownEvent ev) {
        scheduler.shutdown();
    }


    @Override
    public void run() {
    }
}
