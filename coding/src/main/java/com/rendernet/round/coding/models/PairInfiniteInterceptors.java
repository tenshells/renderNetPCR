package com.rendernet.round.coding.models;

import org.springframework.stereotype.Component;

@Component
public class PairInfiniteInterceptors {

    public void startPairIntercept(){        
        InfinityInterceptor interceptor1 = new InfinityInterceptor();
        InfinityInterceptor interceptor2 = new InfinityInterceptor();

        Thread thread1 = new Thread(() -> {
            while (true) {
                interceptor1.intercept();
                // try {
                //     Thread.sleep(5000); // Wait for 5 seconds
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                interceptor2.intercept();
                try {
                    Thread.sleep(5000); // Wait for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
