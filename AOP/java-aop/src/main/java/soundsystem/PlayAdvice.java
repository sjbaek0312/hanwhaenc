package soundsystem;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PlayAdvice {
    public PlayAdvice(){
        System.out.println("PlayAdvice 생성자");
    }
    @Before("execution(* soundsystem.MediaPlayer.*(..))")
    public void before(JoinPoint joinPoint){
        System.out.println("메소드 호출 전 실행 : " + joinPoint.getTarget().getClass().getName() );
    }

    @After("execution(* soundsystem.MediaPlayer.*(..))")
    public void after(JoinPoint joinPoint){
        System.out.println("메소드 호출 후 실행 : " + joinPoint.getTarget().getClass().getName());
    }

}
