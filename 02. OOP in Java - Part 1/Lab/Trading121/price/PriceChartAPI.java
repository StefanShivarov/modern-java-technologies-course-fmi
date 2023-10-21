package bg.sofia.uni.fmi.mjt.trading.price;

public interface PriceChartAPI {

    double getCurrentPrice(String stockTicker);

    boolean changeStockPrice(String stockTicker, int percentChange);
}
