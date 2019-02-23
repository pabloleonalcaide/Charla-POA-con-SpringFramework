import java.time.LocalDate;

public class BankAccountPOO{
    
    Double balance;

    public BankAccountPOO(Double balance){
        this.balance = balance;
    }
    
    public void doDeposit(Double amount){
        if(amount <=0){
            System.out.println("You haven't enough money");
        }else{
            LocalDate date = LocalDate.now();
            this.balance += amount;
            
	        System.out.println("Operation performed in" + date);
        }
    }
    
    public void doTransfer(Double amount, BankAccountPOO accountDestiny){
        if(this.balance < amount){
            System.out.println("You haven't enough money");
        }else{
            LocalDate date = LocalDate.now();
            accountDestiny.doDeposit(amount);
            this.balance -= amount;
    
            System.out.println("Operation performed in" + date);

        }
    }
}