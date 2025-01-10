package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Model.classes.DeliveryDetails;
import Model.classes.Transaction;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class ShowHistoryView extends JFrame {
    protected JTable table;
    public ShowHistoryView() {
        setTitle("Lihat History Pengiriman");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        String[] columnNames = {"Transaction ID", "Customer ID", "Delivery Type", "Delivery Weight", 
                                 "Total Cost", "Created At", "Receipt Name", "Receipt Address","Receipt Phone", "Details"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        ArrayList<Transaction> transactions = Transaction.getTransactions();
        for (Transaction transaction : transactions) {
            model.addRow(new Object[]{transaction.getId(), transaction.getCustomerId(),transaction.getDeliveryType(),
                                     transaction.getDeliveryWeight(), transaction.getTotalCost(), transaction.getCreatedAt(), 
                                     transaction.getReceiptName(),transaction.getReceiptAddress(), transaction.getReceiptPhone(),
                                     "Details"
            });
        }

        table.getColumn("Details").setCellRenderer(new ButtonRenderer());
        table.getColumn("Details").setCellEditor(new ButtonEditor(new JCheckBox(), this));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            new MainMenu().setVisible(true); 
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ShowDetailDeliveryView extends JFrame {
        protected JTable table;
    
        public ShowDetailDeliveryView(ArrayList<DeliveryDetails> deliveryDetails) {
            setTitle("Detail History Pengiriman");
            setSize(600, 400);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());
    
            String[] columnNames = {"Delivery ID", "Transaction ID", "Status", "Current Position", "Evidence", "Date", "Updated By"};


            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            table = new JTable(model);
    
            for (DeliveryDetails detail : deliveryDetails) {
                model.addRow(new Object[]{

                    detail.getId(),
                    detail.getTransactionId(),
                    detail.getStatus().toString(),
                    detail.getCurrentPosition(),
                    detail.getEvidence(),
                    new Date(detail.getDate().getTime()).toString(),
                    detail.getUpdatedBy()
                });
            }
    
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);

            JButton backButton = new JButton("Back");
            backButton.addActionListener(e -> dispose());
            JPanel bottomPanel = new JPanel();
            bottomPanel.add(backButton);
            add(bottomPanel, BorderLayout.SOUTH);
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        ShowHistoryView shv;
        private JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, ShowHistoryView shv) {
            super(checkBox);
            this.shv = shv;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> {
                if (isPushed) {
                    int row = shv.table.getSelectedRow();
                    String transactionId = table.getValueAt(row, 0).toString();
                    ArrayList<DeliveryDetails> deliveryDetailsList = DeliveryDetails.gDeliveryDetails();
                    ArrayList<DeliveryDetails> selectedDeliveryDetailsList = new ArrayList<>();
                    for (DeliveryDetails dd : deliveryDetailsList) {
                        if (dd.getTransactionId() == Integer.parseInt(transactionId)) {
                            selectedDeliveryDetailsList.add(dd);
                        } 
                    }

                    new ShowDetailDeliveryView(selectedDeliveryDetailsList).setVisible(true);
                }
                fireEditingStopped();
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            isPushed = false;
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
}
