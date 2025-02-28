import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class billingappgui extends JFrame {
    private BillingService service;
    private JTextArea displayArea;

    public billingappgui() {
        service = new BillingService();
        setTitle("Billing System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize GUI components
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Users", createUserPanel());
        tabbedPane.addTab("Products", createProductPanel());
        tabbedPane.addTab("Invoices", createInvoicePanel());
        tabbedPane.addTab("Payments", createPaymentPanel());

        add(tabbedPane, BorderLayout.CENTER);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setPreferredSize(new Dimension(800, 200));
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);
    }

    private JPanel createUserPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10)); 
        JTextField userIdField = new JTextField(5);
        JTextField nameField = new JTextField(5);
        JTextField contactField = new JTextField(5);
        JTextField addressField = new JTextField(5);

        panel.add(new JLabel("User ID:"));
        panel.add(userIdField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Contact:"));
        panel.add(contactField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(e -> {
            User user = new User(userIdField.getText(), nameField.getText(), contactField.getText(), addressField.getText());
            service.addUser(user);
            displayArea.setText("User added successfully:\n" + user);
            clearFields(userIdField, nameField, contactField, addressField);
        });

        panel.add(addUserButton);
        return panel;
    }

    private JPanel createProductPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        JTextField productIdField = new JTextField(5);
        JTextField nameField = new JTextField(5);
        JTextField priceField = new JTextField(5);
        JTextField descriptionField = new JTextField(5);

        panel.add(new JLabel("Product ID:"));
        panel.add(productIdField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(e -> {
            try {
                double price = Double.parseDouble(priceField.getText());
                Product product = new Product(productIdField.getText(), nameField.getText(), price, descriptionField.getText());
                service.addProduct(product);
                displayArea.setText("Product added successfully:\n" + product);
                clearFields(productIdField, nameField, priceField, descriptionField);
            } catch (NumberFormatException ex) {
                displayArea.setText("Error: Invalid price format.\n");
            }
        });

        panel.add(addProductButton);
        return panel;
    }

    private JPanel createInvoicePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField userIdField = new JTextField(5);
        JTextField productIdField = new JTextField(5);

        JButton addProductButton = new JButton("Add Product to Invoice");
        addProductButton.addActionListener(e -> {
            User user = service.getUserById(userIdField.getText());
            if (user != null) {
                Product product = service.getProductById(productIdField.getText());
                if (product != null) {
                    Invoice invoice = new Invoice("INV" + System.currentTimeMillis(), user, Collections.singletonList(product));
                    service.addInvoice(invoice);
                    displayArea.setText("Invoice created successfully:\n" + invoice);
                } else {
                    displayArea.setText("Product not found.");
                }
            } else {
                displayArea.setText("User not found.");
            }
        });

        inputPanel = new JPanel();
        inputPanel.add(new JLabel("User ID:"));
        inputPanel.add(userIdField);
        inputPanel.add(new JLabel("Product ID:"));
        inputPanel.add(productIdField);
        inputPanel.add(addProductButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        return panel;
    }

    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField invoiceIdField = new JTextField(5);
        JTextField amountField = new JTextField(5);

        panel.add(new JLabel("Invoice ID:"));
        panel.add(invoiceIdField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);

        JButton processPaymentButton = new JButton("Process Payment");
        processPaymentButton.addActionListener(e -> {
            try {
                String invoiceId = invoiceIdField.getText();
                double amount = Double.parseDouble(amountField.getText());
                Invoice invoice = service.getInvoiceById(invoiceId);
                if (invoice != null) {
                    if (amount >= invoice.getTotalAmount()) {
                        invoice.markAsPaid();
                        payment payment = new payment(invoice, amount);
                        service.processPayment(payment);
                        displayArea.setText("Payment processed successfully for invoice " + invoiceId);
                    } else {
                        displayArea.setText("Amount exceeds the invoice total.");
                    }
                } else {
                    displayArea.setText("Invoice not found.");
                }
            } catch (NumberFormatException ex) {
                displayArea.setText("Error: Invalid amount format.\n");
            }
        });

        panel.add(processPaymentButton);
        return panel;
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new billingappgui().setVisible(true);
        });
    }
}
