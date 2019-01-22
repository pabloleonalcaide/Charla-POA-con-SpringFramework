import java.time.LocalDate;

public class CuentaBancariaPOO2{
    
    Double saldo;

    public CuentaBancariaPOO2(Double saldo){
        this.saldo = saldo;
    }
    
    public void registrarMovimiento(){
        LocalDate fecha = LocalDate.now();
        System.out.println("Movimiento realizado el" + fecha);
    }
    
    public void hacerDeposito(Double cantidad){
        if(cantidad <=0){
            System.out.println("No hay dinero suficiente");
        }else{
            this.saldo += cantidad;
            registrarMovimiento();
        }
    }
    
    public void hacerTransferencia(Double cantidad, CuentaBancariaPOO2 cuentaDestino){
        if(this.saldo < cantidad){
            System.out.println("No hay fondos suficientes");
        }else{
            cuentaDestino.hacerDeposito(cantidad);
            this.saldo -= cantidad;
            registrarMovimiento();
        }
    }
}