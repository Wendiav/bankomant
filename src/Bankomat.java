public class Bankomat {
    boolean cardInserted = false;
    Card card;
    int amount;
    int machineBalance = 11000;

    public String getMessage() {
        return message;
    }

    String message = "";

    void insertCard(Card card){
        cardInserted = true;
        this.card = card;
        if (this.card.pin == "" ){
            addmessage("this is a new cars. Please enter a pin code for it.");
        }else {
            addmessage("Enter your pin");
        }
    }

    void addmessage(String msg){
        message = msg;
        System.out.println(message);
    }
    boolean createPin(String pin){
        return card.createPin(pin);
    }
    void ejectCard(){
        cardInserted = false;
    }

    boolean enterPin(String pin){
        if(card.pin == pin){
            return true;
        }else{
            return false;
        }
    }

    int withdrawMoney(int amount){
        if(amount%100 != 0){
            return 0;
        }
        if(machineBalance >= amount){
           machineBalance -= amount;
            return amount;
        }else{
            return 0;
        }
    }
}
