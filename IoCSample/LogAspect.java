import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component 
@Aspect
public class LogAspect{

    @After("execution(* do*(..))")
    public void adviceLogger(JoinPoint jp) {
        LocalDate date = LocalDate.now();
        System.out.println("Operation performed in" + date);
        System.out.println("On method - " + jp.getSignature().getName());
    }

}