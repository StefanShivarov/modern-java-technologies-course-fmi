package bg.sofia.uni.fmi.mjt.trading.stock;

import java.time.LocalDateTime;

public class GoogleStockPurchase implements StockPurchase {

    private static final String GOOGLE_STOCK_TICKER = "GOOG";
    private int quantity;
    private LocalDateTime purchaseTimestamp;
    private double purchasePricePerUnit;

    public GoogleStockPurchase(int quantity, LocalDateTime purchaseTimestamp, double purchasePricePerUnit) {
        this.quantity = quantity;
        this.purchaseTimestamp = purchaseTimestamp;
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public LocalDateTime getPurchaseTimestamp() {
        return purchaseTimestamp;
    }

    @Override
    public double getPurchasePricePerUnit() {
        return Math.round(purchasePricePerUnit * 100.0) / 100.0;
    }

    @Override
    public double getTotalPurchasePrice() {
        return Math.round((quantity * purchasePricePerUnit) * 100.0) / 100.0;
    }

    @Override
    public String getStockTicker() {
        return GOOGLE_STOCK_TICKER;
    }
}
