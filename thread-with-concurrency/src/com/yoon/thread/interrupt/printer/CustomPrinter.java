package com.yoon.thread.interrupt.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

import static com.yoon.utils.LoggerLogger.log;
import static com.yoon.utils.ThreadSleepUtils.sleep;

public class CustomPrinter {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread thread = new Thread(printer, "printer");

        thread.start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            log("Please Enter question. Nothing, input 'q'.");
            String nextLine = scanner.nextLine();
            if (nextLine.equals("q")) {
                printer.work = false;
                break;
            }
            printer.addJob(nextLine);
        }
    }

    static class Printer implements Runnable {
        volatile boolean work = true;
        Queue<String> jobList = new ConcurrentLinkedDeque<>();

        @Override
        public void run() {
            while (work) {
                if (jobList.isEmpty()) {
                    continue;
                }

                log("printing start");
                log(jobList.poll());
                sleep(4000);
                log("printing .. remain list" + jobList);
            }
            log("printing end");
        }

        public void addJob(String input) {
            jobList.offer(input);
        }
    }
}
