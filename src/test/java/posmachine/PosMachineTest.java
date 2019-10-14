package posmachine;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PosMachineTest {


    @Test
    public void should_get_receipt_using_real_price_calculator() {
        //given
        PriceCalculator priceCalculator = new PriceCalculator();
        PosMachine posMachine = new PosMachine(priceCalculator);

        //when

        //then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            posMachine.getReceipt("Sprite");
        });
    }

    @Test
    public void should_get_receipt_using_stub_price_calculator() {
        //given

        PriceCalculator stubCalculator = new StubCalculator();
        PosMachine posMachine = new PosMachine(stubCalculator);
        //when
        String receipt = posMachine.getReceipt("Sprite");

        //then
        assertEquals("Name: Sprite, Price: 500.0", receipt);

    }

    @Test
    public void should_get_receipt_using_real_price_calculator_with_mockito() {
        //given
        PriceCalculator priceCalculator = Mockito.mock(PriceCalculator.class);

        PosMachine posMachine = new PosMachine(priceCalculator);
        String productName = "Sprite";
        //when
        when(priceCalculator.calculate(productName)).thenReturn(500.0);
        String receipt = posMachine.getReceipt(productName);


        //then
        assertEquals("Name: Sprite, Price: 500.0", receipt);
    }

    private class StubCalculator extends PriceCalculator {
        @Override
        public double calculate(String productName) {
            return 500;
        }
    }
}
