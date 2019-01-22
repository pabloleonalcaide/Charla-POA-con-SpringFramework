import java.time.LocalDate;

public class CuentaBancariaPOO{
    
    Double saldo;

    public CuentaBancariaPOO(Double saldo){
        this.saldo = saldo;
    }
    
    public void hacerDeposito(Double cantidad){
        if(cantidad <=0){
            System.out.println("No hay dinero suficiente");
        }else{
            LocalDate fecha = LocalDate.now();
            this.saldo += cantidad;
            // registramos el movimiento
            System.out.println("Movimiento realizado el " + fecha);
        }
    }
    
    public void hacerTransferencia(Double cantidad, CuentaBancariaPOO cuentaDestino){
        if(this.saldo < cantidad){
            System.out.println("No hay fondos suficientes");
        }else{
            LocalDate fecha = LocalDate.now();
            cuentaDestino.hacerDeposito(cantidad);
            this.saldo -= cantidad;
            // registramos el movimiento
            System.out.println("Movimiento realizado el " + fecha);
        }
    }
}