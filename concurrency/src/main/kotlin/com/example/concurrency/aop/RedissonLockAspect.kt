package com.example.concurrency.aop

import com.example.concurrency.aop.annotation.RedissonLock
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RedissonClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit

@Aspect
@Component
class RedissonLockAspect(
    private val redissonClient: RedissonClient
) {
    val log = LoggerFactory.getLogger(this.javaClass)

    @Pointcut("@annotation(com.example.concurrency.aop.annotation.RedissonLock)")
    fun redissonLockPointCut() {}

    @Around("redissonLockPointCut()")
    fun tryRedissonLock(joinPoint: ProceedingJoinPoint): Any? {
        val methodSignature = joinPoint.signature as MethodSignature
        val method = methodSignature.method
        val annotation = method.getAnnotation(RedissonLock::class.java)

        val value = CustomSpELParser.getDynamicValue(methodSignature.parameterNames, joinPoint.args, annotation.key)
        val key = "${method.name}::${value}"
        log.info("key = {}", key)

        val lock = redissonClient.getLock(key)

        try {
            if (!lock.tryLock(5L, 3L, TimeUnit.SECONDS)) {
                return false
            }

            return proceed(joinPoint)
        } catch (e: Exception) {
            log.error("message = {}", e.message, e)
            throw e
        } finally {
            lock.unlock()
        }
    }

    fun doLock(joinPoint: ProceedingJoinPoint, key: String): Any? {
        val proceed: Any?
        val lock = redissonClient.getLock(key)

        try {
            if (!lock.tryLock(5L, 3L, TimeUnit.SECONDS)) {
                return false
            }

            proceed = proceed(joinPoint)
        } catch (e: Exception) {
            log.error("message = {}", e.message, e)
            throw e
        } finally {
            lock.unlock()
        }

        return proceed
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun proceed(joinPoint: ProceedingJoinPoint): Any? {
        return joinPoint.proceed()
    }
}