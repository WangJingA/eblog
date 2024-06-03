package com.blog.gfblog.formlock;

import org.aspectj.lang.ProceedingJoinPoint;

public interface RequestKeyGenerator {
    String getLockKey(ProceedingJoinPoint proceedingJoinPoint);
}
