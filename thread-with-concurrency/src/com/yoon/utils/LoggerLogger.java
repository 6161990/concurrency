package com.yoon.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class LoggerLogger {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static void log(Object obj) {
        String time = LocalDateTime.now().format(formatter);
        System.out.printf("%s [%9s] %s\n", time, Thread.currentThread().getName(), obj);
    }
}
