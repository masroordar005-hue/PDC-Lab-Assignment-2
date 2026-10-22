public class TradingEngineApp {
    public static void main(String[] args) {
        TradeEvent first = new FIXOrderEvent("FIX-GW-1", System.currentTimeMillis(),
                125.50, "ORD-1001", 100);
        TradeEvent second = new ReutersQuoteEvent("RTRS-GW-2", System.currentTimeMillis(),
                125.50, 125.45, 125.60);

        first.dispatch();
        second.dispatch();
    }
}
