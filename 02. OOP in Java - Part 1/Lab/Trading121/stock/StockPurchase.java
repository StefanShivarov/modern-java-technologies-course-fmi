package bg.sofia.uni.fmi.mjt.trading.stock;

import java.time.LocalDateTime;

public interface StockPurchase {

    int getQuantity();

    LocalDateTime getPurchaseTimestamp();

    double getPurchasePricePerUnit();

    double getTotalPurchasePrice();

    String getStockTicker();
}
