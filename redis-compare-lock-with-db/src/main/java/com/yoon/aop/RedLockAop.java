package com.yoon.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Aspect
@Component
@RequiredArgsConstructor
public class RedLockAop {

    private final RedissonClient redissonClient;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Around("@annotation(com.yoon.aop.RedLock)")
    public Object lock(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        RedLock redLock = signature.getMethod().getAnnotation(RedLock.class);

        RLock lock = redissonClient.getLock(getDynamicValue(signature.getParameterNames(), pjp.getArgs(), redLock.key()).toString());
        try {
            if (lock.tryLock(redLock.waitTime(), redLock.leaseTime(), redLock.timeUnit())) {
                return pjp.proceed();
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                applicationEventPublisher.publishEvent(lock);
            }
        }
    }

    private Object getDynamicValue(String[] parameterNames, Object[] args, String key) {
        SpelExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, Object.class);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void unlock(RLock lock) {
        lock.unlock();
    }

}
