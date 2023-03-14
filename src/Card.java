public class Card {
    String pin = "";

    boolean createPin(String pin){
        // kanske validera att det är en gilltig pinkod vi fått...
        this.pin = pin ;
        return  true;
    }

}
