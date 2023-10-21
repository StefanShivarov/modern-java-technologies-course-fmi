package bg.sofia.uni.fmi.mjt.trading;

import bg.sofia.uni.fmi.mjt.trading.stock.StockPurchase;

import java.time.LocalDateTime;

public interface PortfolioAPI {

    StockPurchase buyStock(String stockTicker, int quantity);

    StockPurchase[] getAllPurchases();

    StockPurchase[] getAllPurchases(LocalDateTime startTimestamp, LocalDateTime endTimestamp);

    double getNetWorth();

    double getRemainingBudget();

    String getOwner();
}
