package RandomProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stocks {

    public static void main(String[] args) {

        List<Integer> stockPrice = new ArrayList<>(Arrays.asList(110, 90, 100, 80, 90));
        int profit = -1;
        int profitBuy = 0;

        int buyIndex = 0;
        int sellIndex = 0;

        for (int i = 1; i < stockPrice.size(); i++){
            if (stockPrice.get(i) < stockPrice.get(buyIndex))  {
                buyIndex = i;
            }
            int currentProfit = stockPrice.get(i) - stockPrice.get(buyIndex);
            if (currentProfit > profit) {
                sellIndex = i;
                profit = currentProfit;
                profitBuy = buyIndex;
            }
        }
        System.out.print(profit + " " + profitBuy + " " + sellIndex);

    }

}
