public class ReutersQuoteEvent extends TradeEvent {
    private double bidPrice;
    private double askPrice;

    public ReutersQuoteEvent(String senderId, long timestamp, double basePrice,
                             double bidPrice, double askPrice) {
        super(senderId, timestamp, basePrice);
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
    }

    @Override
    public void dispatch() {
        System.out.printf("Reuters quote | Bid: %.2f | Ask: %.2f | Spread: %.4f%n",
                bidPrice, askPrice, askPrice - bidPrice);
    }
}
