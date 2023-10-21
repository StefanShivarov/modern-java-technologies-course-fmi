package bg.sofia.uni.fmi.mjt.trading.stock;

import java.time.LocalDateTime;

public class MicrosoftStockPurchase implements StockPurchase {

    private static final String MICROSOFT_STOCK_TICKER = "MSFT";
    private int quantity;
    private LocalDateTime purchaseTimestamp;
    private double purchasePricePerUnit;

    public MicrosoftStockPurchase(int quantity, LocalDateTime purchaseTimestamp, double purchasePricePerUnit) {
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
        return MICROSOFT_STOCK_TICKER;
    }
}
