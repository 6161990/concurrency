package com.yoon.thread.cas.spinlock;

public class SpinLockMain {

    public static void main(String[] args) {
        SpinLockWithCas spinLock = new SpinLockWithCas();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                spinLock.lock();
                try {
                    // doing something
                    /**
                    * 근데 여기서 비즈니스 로직이 오래 걸려
                     * 그럼 락을 기다리는 스레드가 계속 while 문을 돌겠지.
                     * BLOCKED, WAITING 스레드는 cpu 를 거의 사용하지 않지만,
                     * Cas 연산은 RUNNABLE 상태로 락 획득을 대기하는거라, cpu 가 직접 관여해.
                     * 그래서 그 작업이 오래걸린다면 치명적일 수 있어. 너무 비효율인거지.
                     * 동기화 락 vs. cas 연산 중에 더 효율적인 방법은 케바케야.
                     *
                     *
                     * cas 장점은
                     * 1. 충돌이 많이 발생하지 않을거라 기대하는 작업에 효율적
                     * 2. 스레드 블로킹 없이 락 획득을 위해 대기 -> 컨텍스트 스위칭이 발생하지 않음.
                     *
                     * 단점은
                     * 1. 충돌이 빈번하면 오버헤드 발생 cpu 를 사용하니까.
                     *
                     *
                     * 동기화 락 장점
                     * 1. 충돌이 발생할 수 없음 리소스에 하나의 접근만 할 수 있으므로
                     * 2. cpu 를 거의 사용하지 않음
                     * 3. 복잡한 상황에서 일관성있게 동작함
                     *
                     * 동기화 락의 단점
                     * 1. 락 획득의 대기시간이 길어질 수 있다.
                     * 2. 컨텍스트 스위칭 오버헤드
                    * */
                }finally {
                    spinLock.unlock();
                }
            }
        };

        Thread thread = new Thread(runnable, "thread1");
        Thread thread2 = new Thread(runnable, "thread2");
        thread.start();
        thread2.start();
    }
}
