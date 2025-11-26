
/*
   // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1321, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
package onlinevehicleauctionsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Biding extends javax.swing.JFrame {
    private List<Bid> bids;
    private JTextArea bidTextArea;
    private JTextField bidderNameField;
    private JTextField bidAmountField;
    private JButton submitButton;
    private JLabel timerLabel;
    private Timer countdownTimer;
    private int remainingTime;

    public Biding() {
        // Set up JFrame properties
        setTitle("Bidding Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize bids list
        bids = new ArrayList<>();

        // Create UI components
        JPanel bidPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        JPanel buttonPanel = new JPanel();
        JPanel timerPanel = new JPanel();

        JLabel bidderNameLabel = new JLabel("Bidder Name:");
        bidderNameField = new JTextField();

        JLabel bidAmountLabel = new JLabel("Bid Amount:");
        bidAmountField = new JTextField();

        submitButton = new JButton("Submit Bid");

        bidTextArea = new JTextArea(10, 30);
        bidTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bidTextArea);

        timerLabel = new JLabel("Time Left: 20 seconds");

        // Add components to the panels
        bidPanel.add(bidderNameLabel);
        bidPanel.add(bidderNameField);
        bidPanel.add(new JLabel()); // Empty label for spacing
        bidPanel.add(bidAmountLabel);
        bidPanel.add(bidAmountField);
        bidPanel.add(submitButton);

        buttonPanel.add(submitButton);

        timerPanel.add(timerLabel);

        // Add panels to the JFrame
        add(bidPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(timerPanel, BorderLayout.WEST);

        // Action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public synchronized void actionPerformed(ActionEvent e) {
                String bidderName = bidderNameField.getText();
                String bidAmountText 
                        = bidAmountField.getText();
    synchronized(this){
                if (!bidderName.isEmpty() && !bidAmountText.isEmpty()) {
                    double bidAmount = Double.parseDouble(bidAmountText);
                    Bid newBid = new Bid(bidderName, bidAmount);
                    bids.add(newBid);

                    updateBidTextArea();
                    clearFields();
                }
    }}
        });

        // Timer for 20 seconds
        remainingTime = 20;
        countdownTimer = new Timer();
        countdownTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                remainingTime--;

                if (remainingTime > 0) {
                    timerLabel.setText("Time Left: " + remainingTime + " seconds");
                } else {
                    countdownTimer.cancel();
                    submitButton.setEnabled(false);
                    bidderNameField.setEnabled(false);
                    bidAmountField.setEnabled(false);

                    if (!bids.isEmpty()) {
                        Collections.sort(bids); // Sort bids in descending order
                        Bid highestBid = bids.get(0);

                        String winnerMessage = "The winner is " + highestBid.getBidderName() + " with a bid of  Rupess " + highestBid.getAmount() + "!";
                        JOptionPane.showMessageDialog(null, winnerMessage, "Auction Winner", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }, 0, 1000); // Run every second

        // Display the JFrame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    Biding(Object[] rowData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private synchronized void updateBidTextArea() {
       synchronized(this){
        if (!bids.isEmpty()) {
            Collections.sort(bids); // Sort bids in descending order
            StringBuilder sb = new StringBuilder();
            for (Bid bid : bids) {
                sb.append("Bidder: ").append(bid.getBidderName()).append("\tAmount:  Rupess ").append(bid.getAmount()).append("\n");
            }
            bidTextArea.setText(sb.toString());
        }}
    }

    private void clearFields() {
        bidderNameField.setText("");
        bidAmountField.setText("");
    }

    private static class Bid implements Comparable<Bid> {
        private String bidderName;
        private double amount;

        public Bid(String bidderName, double amount) {
            this.bidderName = bidderName;
            this.amount = amount;
        }

        public String getBidderName() {
            return bidderName;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public int compareTo(Bid otherBid) {
            return Double.compare(otherBid.amount, this.amount); // Sort in descending order
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BiddingFrame();
            }
        });
    }
}
