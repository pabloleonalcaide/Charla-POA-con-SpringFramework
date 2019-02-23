import java.time.LocalDate;

public class BankAccountPOO2{
    
    Double balance;

    public BankAccountPOO2(Double balance){
        this.balance = balance;
    }
    
    public void logOperation(){
        LocalDate date = LocalDate.now();
        System.out.println("Operation performed in" + date);
    }
    
    public void doDeposit(Double amount){
        if(amount <=0){
            System.out.println("You haven't enough money");
        }else{
            this.balance += amount;
            logOperation();
        }
    }
    
    public void doTransfer(Double amount, BankAccountPOO2 accountDestiny){
        if(this.balance < amount){
            System.out.println("There aren't enough money");
        }else{
            accountDestiny.doDeposit(amount);
            this.balance -= amount;
            logOperation();
        }
    }
}