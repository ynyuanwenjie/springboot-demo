package com.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class MessageCommunicator {

    public void deliver(String message) {
        System.out.println(message);
    }

    public void deliver(String person, String message) {
        System.out.println(person + ", " + message);
    }
}
