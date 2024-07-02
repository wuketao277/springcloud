package com.hello.websocket.controller;

import lombok.Data;

@Data
public class PersonVO {
    /**
     * 这个id就是websocket链接时传入的id
     */
    private String id;
    private int age;
    private String name;
}
