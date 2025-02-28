public class payment {
    private Invoice invoice;
    private double amount;

    public payment(Invoice invoice, double amount) {
        this.invoice = invoice;
        this.amount = amount;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Payment{invoice=" + invoice.getInvoiceId() + ", amount=" + amount + "}";
    }
}
