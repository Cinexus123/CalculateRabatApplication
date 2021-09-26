package layout;

import javax.swing.*;

public class LayoutApplication {

    private JFrame frame;
    private JPanel panel = new JPanel();

    private JLabel item1 = new JLabel("Przedmiot1");
    private JLabel item2 = new JLabel("Przedmiot2");
    private JLabel item3 = new JLabel("Przedmiot3");
    private JLabel item4 = new JLabel("Przedmiot4");
    private JLabel item5 = new JLabel("Przedmiot5");

    private JLabel item1Price = new JLabel("cena:");
    private JLabel item2Price = new JLabel("cena:");
    private JLabel item3Price = new JLabel("cena:");
    private JLabel item4Price = new JLabel("cena:");
    private JLabel item5Price = new JLabel("cena:");

    private JLabel item1PriceBeforeDiscount = new JLabel("");
    private JLabel item2PriceBeforeDiscount = new JLabel("");
    private JLabel item3PriceBeforeDiscount = new JLabel("");
    private JLabel item4PriceBeforeDiscount = new JLabel("");
    private JLabel item5PriceBeforeDiscount = new JLabel("");

    private JLabel item1PriceAfterDiscount = new JLabel("");
    private JLabel item2PriceAfterDiscount = new JLabel("");
    private JLabel item3PriceAfterDiscount = new JLabel("");
    private JLabel item4PriceAfterDiscount = new JLabel("");
    private JLabel item5PriceAfterDiscount = new JLabel("");

    private JButton calculateRabat = new JButton("Calculate discount");
    private JLabel rabatInfoLabel = new JLabel("Pass discount:");
    private JTextField rabatValue = new JTextField();

    public void setLayout() {
        frame = new JFrame("Panel");
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(null);
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

        item1PriceAfterDiscount.setBounds(460, 50, 120, 30);
        item2PriceAfterDiscount.setBounds(460, 100, 120, 30);
        item3PriceAfterDiscount.setBounds(460, 150, 120, 30);
        item4PriceAfterDiscount.setBounds(460, 200, 120, 30);
        item5PriceAfterDiscount.setBounds(460, 250, 120, 30);

        rabatInfoLabel.setBounds(200, 350, 120, 30);
        rabatValue.setBounds(300, 350, 120, 30);
        calculateRabat.setBounds(460, 350, 160, 30);

        frame.add(item1);
        frame.add(item2);
        frame.add(item3);
        frame.add(item4);
        frame.add(item5);
        frame.add(item1Price);
        frame.add(item2Price);
        frame.add(item3Price);
        frame.add(item4Price);
        frame.add(item5Price);
        frame.add(item1PriceBeforeDiscount);
        frame.add(item2PriceBeforeDiscount);
        frame.add(item3PriceBeforeDiscount);
        frame.add(item4PriceBeforeDiscount);
        frame.add(item5PriceBeforeDiscount);
        frame.add(item1PriceAfterDiscount);
        frame.add(item2PriceAfterDiscount);
        frame.add(item3PriceAfterDiscount);
        frame.add(item4PriceAfterDiscount);
        frame.add(item5PriceAfterDiscount);
        frame.add(calculateRabat);
        frame.add(rabatInfoLabel);
        frame.add(rabatValue);

        frame.add(panel);
    }
}
