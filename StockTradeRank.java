class StockTracker {
    Map<String, Integer> stockToQuantity;
    TreeSet<String> sortedStocks;
    List<List<String>> cachedRankedStocks;
    boolean hasChanged;
    public StockTracker() {
        stockToQuantity = new HashMap<>();
        sortedStocks = new TreeSet<>((a, b) ->
            stockToQuantity.get(a) == stockToQuantity.get(b) ? a.compareTo(b)
            : stockToQuantity.get(b) - stockToQuantity.get(a));
        cachedRankedStocks = new ArrayList<>();
        hasChanged = false;
    }
    // O(logn)
    public void recordTrade(String stockName, int quantity) {
        hasChanged = true;
        if (!stockToQuantity.containsKey(stockName)) {
            stockToQuantity.put(stockName, quantity);
            sortedStocks.add(stockName);
        } else {
            sortedStocks.remove(stockName);
            stockToQuantity.put(stockName, stockToQuantity.get(stockName) + quantity);
            sortedStocks.add(stockName);
        }
    }
    // O(n)
    public List<List<String>> getRankedTrades() {
        if (!hasChanged) {
            return cachedRankedStocks;
        }
        List<List<String>> rankedTrades = new ArrayList<>();
        // treeSet -> output list
        for (String stock : sortedStocks) {
            List<String> stockInfo = new ArrayList<>();
            stockInfo.add(stock);
            stockInfo.add(String.valueOf(stockToQuantity.get(stock)));
            rankedTrades.add(stockInfo);
        }
        cachedRankedStocks = rankedTrades;
        hasChanged = false;
        return rankedTrades;
    }
}
