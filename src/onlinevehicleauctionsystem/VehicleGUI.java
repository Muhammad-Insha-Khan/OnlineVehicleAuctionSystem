/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlinevehicleauctionsystem;

/**
 *
 * @author insha
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Vehicle {
    String name;
    String description;

    public Vehicle(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
class MyVehicle extends Thread {

    public void run()
    {
       synchronized(this){
        System.out.println("Vehciles we deal");
    }}
}
class VehicleLoader extends Thread {
    private static final String[] vehicleNames = {"Car", "Bus", "Motorcycle", "Truck", "Bicycle", "Scooter", "Boat", "Airplane", "Helicopter", "Train"};
    private static final String[] vehicleDescriptions = {"Four-wheeled motor vehicle", "Large vehicle for transporting people", "Motor-driven two-wheeler", "Heavy-duty vehicle for cargo", "Human-powered, pedal-driven", "Small two-wheeler with step-through frame", "Watercraft designed for travel", "Powered fixed-wing aircraft", "Rotorcraft that can take off and land vertically", "Connected series of railroad cars"};

    private JTextArea textArea;
    private int currentIndex = 0;
    private Object lock = new Object();
    private boolean reset = false;

    public VehicleLoader(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    @Override
    public void run() {
        while (currentIndex < vehicleNames.length) {
            synchronized (lock) {
                if (reset) {
                    // Clear text area if reset is requested
                    SwingUtilities.invokeLater(() -> textArea.setText(""));
                    currentIndex = 0;
                    reset = false;
                }

                String name = vehicleNames[currentIndex];
                String description = vehicleDescriptions[currentIndex];
                Vehicle vehicle = new Vehicle(name, description);

                SwingUtilities.invokeLater(() -> {
                    textArea.append("Name: " + vehicle.name + "\nDescription: " + vehicle.description + "\n\n");
                });

                currentIndex++;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class VehicleGUI extends JFrame {
    private JTextArea textArea;
    private VehicleLoader vehicleLoader;

    public VehicleGUI() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Vehicle Information");
 textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 22));  // Set font size to 24px
        textArea.setBackground(new Color(225, 225, 170)); 

        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the button is clicked, reset information
                vehicleLoader.setReset(true);
            }
        });

        add(resetButton, BorderLayout.SOUTH);

        setSize(1500, 900);
        setLocationRelativeTo(null);
        setVisible(true);

        vehicleLoader = new VehicleLoader(textArea);
        Thread thread = new Thread(vehicleLoader);
        thread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VehicleGUI());
         MyVehicle myThread = new MyVehicle();
       
         myThread.start();
    }
}
