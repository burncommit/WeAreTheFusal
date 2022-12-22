package com.burncommit.futsal.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;



@Slf4j //로그남기는 방법
public abstract class AbstractValidator<T> implements Validator {

	 @Override
	    public boolean supports(Class<?> clazz) { // 인스턴스가 검증 대상 타입인지 확인
	        return true;
	    }

	    @SuppressWarnings("unchecked")
	    @Override
	    public void validate(Object target, Errors errors) {  // 실질적인 검증 작업
	        try {
	            doValidate((T) target, errors);
	        } catch(RuntimeException e) {
	            log.error("중복 검증 에러", e);
	            throw e;
	        }
	    }

	    protected abstract void doValidate(final T dto, final Errors errors);

    
}
