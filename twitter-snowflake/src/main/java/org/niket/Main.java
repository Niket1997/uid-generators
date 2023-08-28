package org.niket;

import org.niket.snowflake.Snowflake;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Snowflake snowflake = new Snowflake(23);
        for(int i = 0; i < 10; i++) {
            System.out.println(snowflake.getId());
            if (i % 3 == 0) {
                Thread.sleep(1000);
            }
        }
    }
}