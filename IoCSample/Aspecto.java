import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component 
@Aspect
public class Aspecto{

    @After("execution(* hacer*(..))")
    public void adviceLogger(JoinPoint jp) {
        LocalDate fecha = LocalDate.now();
        System.out.println("Movimiento realizado el" + fecha);
        System.out.println("En el método - " + jp.getSignature().getName());
    }

}