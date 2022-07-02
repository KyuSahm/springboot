import impl.DollorCalculator;
import impl.KRWCalculator;
import impl.MarketAPI;
import impl.Operator;

public class Main {
    public static void main(String[] args) {
        Operator operator = new Operator(new KRWCalculator());

        System.out.println("KRW Sum Result:" + operator.operateSum(10, 10));
        System.out.println("KRW Minus Result:" + operator.operateMinus(10, 10));

        MarketAPI marketAPI = new MarketAPI();
        operator = new Operator(new DollorCalculator(marketAPI));
        System.out.println("Dollor Sum Result:" + operator.operateSum(10, 10));
        System.out.println("Dollor Minus Result:" + operator.operateMinus(10, 10));
    }
}