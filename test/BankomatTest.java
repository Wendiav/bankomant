import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankomatTest {

    Bankomat bankomat;
    Card card;

    @BeforeEach
    void setUp() {
        this.bankomat = new Bankomat();
    }
    void prepareCardAndPin(){
        bankomat.insertCard(card);
        bankomat.createPin("0123");

    }

    @Test
    void insertCard() {
        bankomat.insertCard(card);
        Assertions.assertEquals(true, bankomat.cardInserted);
    }

    @Test
    void ejectCard() {
        bankomat.insertCard(card);
        bankomat.ejectCard();
        Assertions.assertEquals(false, bankomat.cardInserted);
    }

    @Test
    void enterPin() {
        // prepare
        prepareCardAndPin();
        // actual test
        boolean result = bankomat.enterPin("0123");
        Assertions.assertTrue(result);
        bankomat.ejectCard();
        bankomat.insertCard(card);
        result = bankomat.enterPin("0012");
        Assertions.assertFalse(result);
    }

    @Test
    void withdrawMoney() {
        // prepare
        prepareCardAndPin();
        bankomat.enterPin("0123");
        // actual test
        bankomat.withdrawMoney(10000);
        Assertions.assertEquals(1000, bankomat.machineBalance);
        bankomat.withdrawMoney(2000);
        Assertions.assertEquals(1000, bankomat.machineBalance);
        bankomat.withdrawMoney(999); // fel! ska inte kunna ta ut gågot som inte är jämnt delbart med sedelvalörer
        Assertions.assertEquals(1000, bankomat.machineBalance);
        bankomat.withdrawMoney(1000);
        Assertions.assertEquals(0, bankomat.machineBalance);
    }

    @Test
    void createPin(){
        bankomat.insertCard(card);
        String msg = bankomat.getMessage();
        assertEquals("this is a new cars. Please enter a pin code for it.", msg);
        boolean result = bankomat.createPin("0123");
        assertTrue(result);
    }
}