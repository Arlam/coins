package aa.tools;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class CoinTest {

    private final int[] coins;
    private final int number;
    private final int weight;

    public CoinTest(int[] coins, int number, int weight) {
        this.coins = coins;
        this.number = number;
        this.weight = weight;
    }

    @Parameterized.Parameters(name = "{index}: testFindDefect(). Expected number = {1}, weight = {2}")
    public static Collection<Object[]> data() {
        Object[][] data = new Object[24][3];
        for (int i = 0; i < 12; i++) {
            int[] coins = new int[12];
            coins[i] = -1;
            data[i][0] = coins;
            data[i][1] = i;
            data[i][2] = -1;
            //--
            coins = new int[12];
            coins[i] = 1;
            data[i+12][0] = coins;
            data[i+12][1] = i;
            data[i+12][2] = 1;
        }
        return Arrays.asList(data);
    }


    @Test
    public void testFindDefect() {
        Assertions.assertThat(Coin.findDefect(coins))
                .hasFieldOrPropertyWithValue("number", number)
                .hasFieldOrPropertyWithValue("weight", weight);
    }
}

