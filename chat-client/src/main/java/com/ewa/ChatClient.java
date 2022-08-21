package com.ewa;

import com.ewa.messages.Worker;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@NoArgsConstructor
public class ChatClient {

   public static String clientName;

}
