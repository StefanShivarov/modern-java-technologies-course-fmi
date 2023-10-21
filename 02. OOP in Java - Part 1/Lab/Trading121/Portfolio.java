package bg.sofia.uni.fmi.mjt.trading;

import bg.sofia.uni.fmi.mjt.trading.price.PriceChartAPI;
import bg.sofia.uni.fmi.mjt.trading.stock.AmazonStockPurchase;
import bg.sofia.uni.fmi.mjt.trading.stock.GoogleStockPurchase;
import bg.sofia.uni.fmi.mjt.trading.stock.MicrosoftStockPurchase;
import bg.sofia.uni.fmi.mjt.trading.stock.StockPurchase;

import java.time.LocalDateTime;

public class Portfolio implements PortfolioAPI {

    private final String owner;
    private final PriceChartAPI priceChart;
    private StockPurchase[] stockPurchases;
    private int currentPurchasesAmount;
    private double budget;
    private final int maxSize;

    public Portfolio(String owner, PriceChartAPI priceChart, double budget, int maxSize){
        this.owner = owner;
        this.priceChart = priceChart;
        this.budget = budget;
        this.maxSize = maxSize;
        this.stockPurchases = new StockPurchase[maxSize];
        this.currentPurchasesAmount = 0;
    }

    public Portfolio(String owner, PriceChartAPI priceChart, StockPurchase[] stockPurchases, double budget, int maxSize){
        this(owner, priceChart, budget, maxSize);
        this.stockPurchases = new StockPurchase[maxSize];
        System.arraycopy(stockPurchases, 0, this.stockPurchases, 0, stockPurchases.length);
        this.currentPurchasesAmount = stockPurchases.length;
    }

    @Override
    public StockPurchase buyStock(String stockTicker, int quantity) {

        double currentPrice = priceChart.getCurrentPrice(stockTicker);
        if(stockTicker == null || currentPurchasesAmount == maxSize || quantity <= 0 || budget < quantity * currentPrice){
            return null;
        }

        StockPurchase purchase = null;
        switch (stockTicker) {
            case "MSFT" -> purchase = new MicrosoftStockPurchase(quantity, LocalDateTime.now(), currentPrice);
            case "GOOG" -> purchase = new GoogleStockPurchase(quantity, LocalDateTime.now(), currentPrice);
            case "AMZ" -> purchase = new AmazonStockPurchase(quantity, LocalDateTime.now(), currentPrice);
        }

        if(purchase != null){
            budget -= currentPrice * quantity;
            stockPurchases[currentPurchasesAmount++] = purchase;
            priceChart.changeStockPrice(stockTicker, 5);
        }
        return purchase;
    }

    @Override
    public StockPurchase[] getAllPurchases() {
        return stockPurchases;
    }

    @Override
    public StockPurchase[] getAllPurchases(LocalDateTime startTimestamp, LocalDateTime endTimestamp) {

        StockPurchase[] filteredPurchases = new StockPurchase[stockPurchases.length];
        int addIndex = 0;
        for(int i = 0; i < currentPurchasesAmount; i++){
            if(stockPurchases[i].getPurchaseTimestamp().compareTo(startTimestamp) >= 0
                    && stockPurchases[i].getPurchaseTimestamp().compareTo(endTimestamp) <= 0){
                filteredPurchases[addIndex++] = stockPurchases[i];
            }
        }

        StockPurchase[] result = new StockPurchase[addIndex];
        for(int i = 0; i < addIndex; i++){
            result[i] = filteredPurchases[i];
        }
        return result;
    }

    @Override
    public double getNetWorth() {

        double netWorth = 0.0;
        for(int i = 0; i < currentPurchasesAmount; i++){
            netWorth += stockPurchases[i].getQuantity() * priceChart.getCurrentPrice(stockPurchases[i].getStockTicker());
        }
        return Math.round(netWorth * 100.0) / 100.0;
    }

    @Override
    public double getRemainingBudget() {
        return Math.round(budget * 100.0) / 100.0;
    }

    @Override
    public String getOwner() {
        return owner;
    }
}
