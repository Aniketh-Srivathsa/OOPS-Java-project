import java.util.*;

public class BillingService {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();
    private Map<String, Invoice> invoices = new HashMap<>();
    private List<Transaction> transactions = new ArrayList<>();
    private List<payment> payments = new ArrayList<>(); // Added payments list

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public void addInvoice(Invoice invoice) {
        invoices.put(invoice.getInvoiceId(), invoice);
    }

    public void processPayment(payment payment) {
        payments.add(payment); // Adding payment to list
        // Additional logic for processing payment
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public User getUserById(String userId) {
        return users.get(userId);
    }

    public Product getProductById(String productId) {
        return products.get(productId);
    }

    public Invoice getInvoiceById(String invoiceId) {
        return invoices.get(invoiceId);
    }

    public Collection<User> listUsers() {
        return users.values();
    }

    public Collection<Product> listProducts() {
        return products.values();
    }

    public Collection<Invoice> listInvoices() {
        return invoices.values();
    }

    public List<Transaction> listTransactions() {
        return transactions;
    }

    public List<payment> listPayments() {
        return payments;
    }
}
