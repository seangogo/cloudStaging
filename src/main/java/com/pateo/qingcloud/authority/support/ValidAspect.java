package com.pateo.qingcloud.authority.support;

import com.pateo.qingcloud.authority.exception.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Date;


/**
 * @author zhailiang
 *
 */
@Aspect
@Component
@Slf4j
public class ValidAspect {
	
	@Around("execution(* com.pateo.qingcloud.authority.controller.*.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("time aspect start");
		
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			if (arg instanceof BindingResult){
				BindingResult errors=(BindingResult)arg;
				if (errors.hasErrors()){
					throw new ValidateException(errors.getFieldError().getCode(),errors.getFieldError().getDefaultMessage());
				};
			}
		}
		
		long start = (new Date()).getTime();
		
		Object object = pjp.proceed();
		
		System.out.println("time aspect 耗时:"+ (((new Date()).getTime()) - start));
		
		System.out.println("time aspect end");
		
		return object;
	}

}
