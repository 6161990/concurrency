package com.yoon.thread.cas.increment;

public class IncrementPerformanceMain {

    private static final int COUNT = 100_000_000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicIncrementInteger()); // 가장 빠름. cpu 캐시를 사용하기 때문에.. 단일 스레드에서 가장 좋음
        test(new VolatileIncrementInteger()); // cpu 캐시 사용하지 않음. 메인 메모리만 사용. 단일 스레드(basic 보다 느리므로), 멀티 스레드 모두 사용 불가
        test(new SynchronizedIncrementInteger()); // 멀티 스레드에서 사용 가능. 가장 느림
        test(new AtomicIncrementInteger()); //  멀티 스레드에서 사용 가능. synchronized 보다 빠름
        /** synchronized 와 Atomic 의 차이가 도대체 뭘까?
         * 멀티스레드에서 사용가능해야하면 임계영역에 locking 해야할 것 같은데 왜 Atomic 은 더 빠를까?
         * 결론, Atomic 은 Lock 을 사용하지 않음
         * */

    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        long start = System.currentTimeMillis();

        for (int i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }

        long end = System.currentTimeMillis();
        System.out.println(incrementInteger.getClass().getSimpleName() + "time : " + (end-start));
    }
}
