public class FIXOrderEvent extends TradeEvent {
    private String clOrdId;
    private int quantity;

    public FIXOrderEvent(String senderId, long timestamp, double basePrice,
                         String clOrdId, int quantity) {
        super(senderId, timestamp, basePrice);
        this.clOrdId = clOrdId;
        this.quantity = quantity;
    }

    @Override
    public void dispatch() {
        double subtotal = basePrice * quantity;
        double fee = subtotal * 0.0015;
        System.out.printf("FIX BUY | Order: %s | Qty: %d | Subtotal: %.2f | Fee: %.2f | Total: %.2f%n",
                clOrdId, quantity, subtotal, fee, subtotal + fee);
    }
}
