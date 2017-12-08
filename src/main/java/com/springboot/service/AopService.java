package com.springboot.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
/**
 * Created by Administrator on 2017/8/9.
 */
@Aspect
public class AopService {
    //Patterns
    //blank = modifier (public/private/protected or default(blank) should be looked for
    //* = return type to look for. Void/Object
    //com.jayway.YourClass.yourMethodBefore(..) = PackageName . ClassName . methodName (parameters)
    @Before("execution (* com.springboot.controller.ReservationController(..))")
    //JointPoint = the reference of the call to the method
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("YourAspect's BeforeAdvice's body is now executed Before yourMethodBefore is called.");
    }
    //Patterns
    //public = look for the specific modifier named public
    //!Object = Basically we are looking for void or primitives. But if we specified Object we could get a good pattern
    //com.jayway.YourClass.yourMethodBefore(..) = PackageName . ClassName . methodName (parameters)
    @After("execution (public !Object com.springboot.controller.ReservationController(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("YourAspect's afterAdvice's body is now executed After yourMethodAfter is called.");
    }
    //Patterns
    //!private = look for any modifier thats not private
    //void = looking for method with void
    //com.jayway.YourClass.yourMethodBefore(..) = PackageName . ClassName . methodName (parameters)
    @Around("execution (!private void com.springboot.controller.ReservationController(Integer,..))")
    //ProceedingJointPoint = the reference of the call to the method.
    //Difference between ProceedingJointPoint and JointPoint is that a JointPoint cant be continued(proceeded)
    //A ProceedingJointPoint can be continued(proceeded) and is needed for a Around advice
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        //Default Object that we can use to return to the consumer
        Object returnObject = null;
        try {
            System.out.println("YourAspect's aroundAdvice's body is now executed Before yourMethodAround is called.");
            //We choose to continue the call to the method in question
            returnObject = joinPoint.proceed();
            //If no exception is thrown we should land here and we can modify the returnObject, if we want to.
        } catch (Throwable throwable) {
            //Here we can catch and modify any exceptions that are called
            //We could potentially not throw the exception to the caller and instead return "null" or a default object.
            throw throwable;
        }
        finally {
            //If we want to be sure that some of our code is executed even if we get an exception
            System.out.println("YourAspect's aroundAdvice's body is now executed After yourMethodAround is called.");
        }

        return returnObject;
    }
    //Patterns//* = return type to look for. Void/Object/Primitive type
    //blank = modifier (public/private/protected or default(blank) should be looked for
    //* = return type to look for. Void/Object/Primitive type
    //com.jayway.YourClass.yourMethod*(..) = PackageName . ClassName . * (parameters)
    //Where the "*" will catch any method name
    @After("execution ( * com.springboot.controller.ReservationController.*(..))")
    //JointPoint = the reference of the call to the method
    public void printNewLine(JoinPoint pointcut){
        //Just prints new lines after each method that's executed in
        System.out.print("---------------------end-----------------------\n\r");
    }
}
