import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame {
    private JTextField temperatureField;
    private JComboBox<String> unitComboBox;
    private JButton convertButton;
    private JTextArea resultArea;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel temperatureLabel = new JLabel("Temperature: ");
        temperatureField = new JTextField(10);
        unitComboBox = new JComboBox<>(new String[] { "Celsius", "Fahrenheit", "Kelvin" });
        inputPanel.add(temperatureLabel);
        inputPanel.add(temperatureField);
        inputPanel.add(unitComboBox);

        convertButton = new JButton("Convert");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(convertButton);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        String inputTemperature = temperatureField.getText();
        if (inputTemperature.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a temperature value.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        double temperature = Double.parseDouble(inputTemperature);
        String selectedUnit = (String) unitComboBox.getSelectedItem();
        double convertedTemperature;

        switch (selectedUnit) {
            case "Celsius":
                convertedTemperature = temperature;
                resultArea.setText(String.format("%.2f Celsius is equal to %.2f Fahrenheit and %.2f Kelvin.",
                        temperature, celsiusToFahrenheit(temperature), celsiusToKelvin(temperature)));
                break;
            case "Fahrenheit":
                convertedTemperature = fahrenheitToCelsius(temperature);
                resultArea.setText(String.format("%.2f Fahrenheit is equal to %.2f Celsius and %.2f Kelvin.",
                        temperature, convertedTemperature, fahrenheitToKelvin(temperature)));
                break;
            case "Kelvin":
                convertedTemperature = kelvinToCelsius(temperature);
                resultArea.setText(String.format("%.2f Kelvin is equal to %.2f Celsius and %.2f Fahrenheit.",
                        temperature, convertedTemperature, kelvinToFahrenheit(temperature)));
                break;
        }
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9 + 273.15;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9 / 5 + 32;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TemperatureConverterGUI().setVisible(true);
            }
        });
    }
}
