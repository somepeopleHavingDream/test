package org.yangxin.test.id;

import java.util.concurrent.atomic.AtomicLong;

public class WorkerIdTest {
    public static void main(String[] args) {
        AtomicLong seq = new AtomicLong(0);
        for (int i = 0; i < 10000; i++) {
            long seqValue = seq.getAndIncrement();
            long dataCenterId = (seqValue / 32) % 32;
            long workerId = seqValue % 32;
            System.out.println("dataCenterId: " + dataCenterId + ", workerId: " + workerId);
        }
    }
}
