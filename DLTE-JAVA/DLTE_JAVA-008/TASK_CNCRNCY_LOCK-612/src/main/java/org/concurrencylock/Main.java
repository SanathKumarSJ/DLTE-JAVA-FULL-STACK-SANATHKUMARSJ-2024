package org.concurrencylock;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;

import static org.concurrencylock.Resource.transaction;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main(String[] args) {

        transaction.add(new Transaction(10500.89, "Maneesh", new Date(2024, 3, 15), "emergency"));
        transaction.add(new Transaction(78625.48, "Rahul", new Date(2024, 6, 3), "family"));
        transaction.add(new Transaction(857895.00, "Ashwin", new Date(2024, 8, 28), "education"));
        transaction.add(new Transaction(1784521.00, "Rohith", new Date(2024, 11, 8), "bill"));
        transaction.add(new Transaction(12575.89, "Vibha", new Date(2024, 8, 21), "emergency"));
        Resource transaction=new Resource();
        ScheduledExecutorService service= Executors.newScheduledThreadPool(3);
        ScheduledFuture future = service.scheduleAtFixedRate(transaction,0,5, TimeUnit.SECONDS);
        service.schedule(new Runnable() {
            @Override
            public void run() {
                future.cancel(true);
                service.shutdown();
            }
        },30,TimeUnit.SECONDS);
    }
}