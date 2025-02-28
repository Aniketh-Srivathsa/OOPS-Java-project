import java.util.*;

public class Invoice {
    private String invoiceId;
    private User user;
    private List<Product> products;

    public Invoice(String invoiceId, User user, List<Product> products) {
        this.invoiceId = invoiceId;
        this.user = user;
        this.products = products;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public User getUser() {
        return user;
    }

    public double getTotalAmount() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public void markAsPaid() {
        // Implementation of markAsPaid
    }

    @Override
    public String toString() {
        return "Invoice ID: " + invoiceId + ", User: " + user + ", Products: " + products;
    }
}
