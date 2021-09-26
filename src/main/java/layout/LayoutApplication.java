package layout;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LayoutApplication {

    private RabatApplication rabatApplication;

    private JFrame frame = new JFrame("Panel");
    private JPanel panel = new JPanel();

    private JLabel item1 = new JLabel("");
    private JLabel item2 = new JLabel("");
    private JLabel item3 = new JLabel("");
    private JLabel item4 = new JLabel("");
    private JLabel item5 = new JLabel("");

    private JLabel item1Price = new JLabel("");
    private JLabel item2Price = new JLabel("");
    private JLabel item3Price = new JLabel("");
    private JLabel item4Price = new JLabel("");
    private JLabel item5Price = new JLabel("");

    private JLabel item1PriceBeforeDiscount = new JLabel("");
    private JLabel item2PriceBeforeDiscount = new JLabel("");
    private JLabel item3PriceBeforeDiscount = new JLabel("");
    private JLabel item4PriceBeforeDiscount = new JLabel("");
    private JLabel item5PriceBeforeDiscount = new JLabel("");

    public JButton calculateRabat = new JButton("Calculate discount");
    public JButton clearData = new JButton("Clear data");
    private JLabel rabatInfoLabel = new JLabel("Pass discount:");
    private JTextField rabatValue = new JTextField();

    List<JLabel> itemsName = new ArrayList<>(Arrays.asList(item1, item2, item3, item4, item5));
    List<JLabel> itemsPriceBeforeDiscount = new ArrayList<>(Arrays.asList(item1Price, item2Price, item3Price, item4Price, item5Price));
    List<JLabel> itemsPriceAfterDiscount = new ArrayList<>(Arrays.asList(item1PriceBeforeDiscount, item2PriceBeforeDiscount,
            item3PriceBeforeDiscount, item4PriceBeforeDiscount, item5PriceBeforeDiscount));

    List<String> productName;

    public LayoutApplication(RabatApplication rabatApplication, Map<Long, Double> productDetails, List<String> productName) {
        this.productName = productName;
        this.rabatApplication = rabatApplication;
        createLayout();
        setLayout(productDetails, productName);
    }

    private void setLayout(Map<Long, Double> productDetails, List<String> productName) {
        Iterator<Map.Entry<Long, Double>> entry = productDetails.entrySet().iterator();
        int setHeight = 50;

        for (int elementLayout = 0; elementLayout < productDetails.size(); elementLayout++) {
            Map.Entry<Long, Double> pair = entry.next();

            itemsName.get(elementLayout).setText(productName.get(elementLayout));
            itemsPriceBeforeDiscount.get(elementLayout).setText(pair.getValue().toString());
            itemsPriceAfterDiscount.get(elementLayout).setText("New price");

            setHeight = setHeight + 50;
        }
    }


    public void createLayout() {

        frame.setSize(800, 600);
        panel.setBorder(BorderFactory.createEmptyBorder(100, 300, 10, 30));
        panel.setLayout(new GridLayout(-5, -3));

        item1.setBounds(150, 50, 100, 30);
        item2.setBounds(150, 100, 100, 30);
        item3.setBounds(150, 150, 100, 30);
        item4.setBounds(150, 200, 100, 30);
        item5.setBounds(150, 250, 100, 30);

        item1Price.setBounds(260, 50, 120, 30);
        item2Price.setBounds(260, 100, 120, 30);
        item3Price.setBounds(260, 150, 120, 30);
        item4Price.setBounds(260, 200, 120, 30);
        item5Price.setBounds(260, 250, 120, 30);

        item1PriceBeforeDiscount.setBounds(360, 50, 120, 30);
        item2PriceBeforeDiscount.setBounds(360, 100, 120, 30);
        item3PriceBeforeDiscount.setBounds(360, 150, 120, 30);
        item4PriceBeforeDiscount.setBounds(360, 200, 120, 30);
        item5PriceBeforeDiscount.setBounds(360, 250, 120, 30);

        rabatInfoLabel.setBounds(200, 350, 120, 30);
        rabatValue.setBounds(300, 350, 120, 30);
        calculateRabat.setBounds(460, 350, 160, 30);
        clearData.setBounds(460, 450, 160, 30);

        panel.add(item1);
        panel.add(item2);
        panel.add(item3);
        panel.add(item4);
        panel.add(item5);
        panel.add(item1Price);
        panel.add(item2Price);
        panel.add(item3Price);
        panel.add(item4Price);
        panel.add(item5Price);
        panel.add(item1PriceBeforeDiscount);
        panel.add(item2PriceBeforeDiscount);
        panel.add(item3PriceBeforeDiscount);
        panel.add(item4PriceBeforeDiscount);
        panel.add(item5PriceBeforeDiscount);
        panel.add(calculateRabat);
        panel.add(clearData);
        panel.add(rabatInfoLabel);
        panel.add(rabatValue);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        clearData.setEnabled(false);

        calculateRabat.addActionListener(e -> {
            Pattern p = Pattern.compile("([0-9])");
            Matcher m = p.matcher(rabatValue.getText());
            String discountValue = rabatValue.getText();

            if (m.find()) {
                if (discountValue.contains(",")) {
                    discountValue = discountValue.replace(",", ".");
                }
                rabatApplication.rabat = Double.parseDouble(discountValue);
                rabatApplication.initDiscount();
                calculateRabat.setEnabled(false);
                clearData.setEnabled(true);
            } else {
                System.out.println("String must contain only digit");
            }
        });

        clearData.addActionListener(e -> {
            for (int elementLayout = 0; elementLayout < productName.size(); elementLayout++)
                itemsPriceAfterDiscount.get(elementLayout).setText("New price");

            calculateRabat.setEnabled(true);
            clearData.setEnabled(false);
            rabatValue.setText("");
            rabatApplication.clearData();
        });
    }

    public void setPriceAfterDiscount(List<Double> priceAfterDiscount) {

        for (int elementLayout = 0; elementLayout < priceAfterDiscount.size(); elementLayout++)
            itemsPriceAfterDiscount.get(elementLayout).setText(priceAfterDiscount.get(elementLayout).toString());
    }
}
