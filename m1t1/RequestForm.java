package m1t1;

import m1t1.controller.RequestFormController;
import m1t1.database.dao.CustomerTableDao;
import m1t1.database.dao.DealerTableDao;
import m1t1.database.dao.LeadTableDao;
import m1t1.database.dao.VehicleTableDao;

import javax.swing.*;
import java.awt.*;

public class RequestForm extends JFrame{
    private static final String BLUE_CODE = "#CDE7FB";
    private static final String BLACK_CODE = "#000000";
    private static final String RED_CODE = "#F08080";

    private JLabel firstNameLabel, lastNameLabel, emailLabel, phoneNumLabel;
    private JTextField firstNameField, lastNameField, emailField, phoneNumField;
    private JButton submitButton;

    private RequestFormController requestFormController;

    private RequestForm(RequestFormController requestFormController, String carDetails, String dealerDetails,
                        int interestedPeopleCount) {
        this.requestFormController = requestFormController;

        firstNameLabel = createJLabel("First Name  ", BLACK_CODE);
        setBoldFont(firstNameLabel);
        lastNameLabel = createJLabel("Last Name   ", BLACK_CODE);
        setBoldFont(lastNameLabel);
        emailLabel = createJLabel("Email       ", BLACK_CODE);
        setBoldFont(emailLabel);
        phoneNumLabel = createJLabel("Phone Number", BLACK_CODE);
        setBoldFont(phoneNumLabel);
        JLabel customerInfoLabel = createJLabel("      ", BLACK_CODE);

        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        emailField =   new JTextField(10);
        phoneNumField =  new JTextField(10);

        Container con = getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.setBackground(Color.decode(BLUE_CODE));

        // Customer information section
        JPanel customerInfoPanel = new JPanel();
        customerInfoPanel.setLayout(new BoxLayout(customerInfoPanel, BoxLayout.Y_AXIS));
        customerInfoPanel.setBackground(Color.decode(BLUE_CODE));
        customerInfoPanel.add(customerInfoLabel);

        JPanel phoneEmailPanel = new JPanel();
        phoneEmailPanel.add(phoneNumLabel);
        phoneEmailPanel.add(phoneNumField);
        phoneEmailPanel.add(emailLabel);
        phoneEmailPanel.add(emailField);
        phoneEmailPanel.setBackground(Color.decode(BLUE_CODE));
        customerInfoPanel.add(phoneEmailPanel);

        JPanel customerNamePanel = new JPanel();
        customerNamePanel.setBackground(Color.decode(BLUE_CODE));
        customerNamePanel.add(firstNameLabel);
        customerNamePanel.add(firstNameField);
        customerNamePanel.add(lastNameLabel);
        customerNamePanel.add(lastNameField);
        customerInfoPanel.add(customerNamePanel);

        con.add(customerInfoPanel);
        con.add(Box.createVerticalGlue());

        // Vehicle and dealer information section
        JPanel informationPanel = new JPanel();
        informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
        informationPanel.setBackground(Color.decode(BLUE_CODE));

        JPanel carDetailsTitle = new JPanel();
        carDetailsTitle.setBackground(Color.decode(BLUE_CODE));
        JLabel carsDetailLabel = createJLabel("Car's detail                            ", BLACK_CODE);
        setBoldFont(carsDetailLabel);
        carDetailsTitle.add(carsDetailLabel);
        JLabel interestedPeopleLabel = createJLabel(String.format("There are %d people interested as well", interestedPeopleCount), RED_CODE);
        setBoldFont(interestedPeopleLabel);
        carDetailsTitle.add(interestedPeopleLabel);
        informationPanel.add(carDetailsTitle);

        JTextArea carDetailsField = new JTextArea(5, 36);
        carDetailsField.setText(carDetails);
        carDetailsField.setEditable(false);
        JPanel carDetailsFieldPanel = new JPanel();
        carDetailsFieldPanel.setBackground(Color.decode(BLUE_CODE));
        carDetailsFieldPanel.add(carDetailsField);
        informationPanel.add(carDetailsFieldPanel);

        JLabel dealerInfoLabel = createJLabel("Dealer information                                                                               ", BLACK_CODE);
        setBoldFont(dealerInfoLabel);
        JPanel dealerInfoLabelPanel = new JPanel();
        dealerInfoLabelPanel.setBackground(Color.decode(BLUE_CODE));
        dealerInfoLabelPanel.add(dealerInfoLabel);
        informationPanel.add(dealerInfoLabelPanel);

        JTextArea dealerDetailsField = new JTextArea(5, 36);
        dealerDetailsField.setText(dealerDetails);
        dealerDetailsField.setEditable(false);
        JPanel dealerDetailsFieldPanel = new JPanel();
        dealerDetailsFieldPanel.setBackground(Color.decode(BLUE_CODE));
        dealerDetailsFieldPanel.add(dealerDetailsField);
        informationPanel.add(dealerDetailsFieldPanel);

        con.add(informationPanel);
        con.add(Box.createVerticalGlue());

        // Message section
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBackground(Color.decode(BLUE_CODE));

        JLabel messageLabel = createJLabel("Write any message to the dealer in below box                                     ", BLACK_CODE);
        setBoldFont(messageLabel);
        JPanel messageLabelPanel = new JPanel();
        messageLabelPanel.setBackground(Color.decode(BLUE_CODE));
        messageLabelPanel.add(messageLabel);
        messagePanel.add(messageLabelPanel);

        JTextArea messageArea = new JTextArea(10, 36);
        JPanel messageAreaPanel = new JPanel();
        messageAreaPanel.setBackground(Color.decode(BLUE_CODE));
        messageAreaPanel.add(messageArea);
        messagePanel.add(messageAreaPanel);

        con.add(messagePanel);
        con.add(Box.createVerticalGlue());

        // Submit button
        submitButton = new JButton("Submit");
        JPanel submitButtonPanel = new JPanel();
        submitButtonPanel.setBackground(Color.decode(BLUE_CODE));
        submitButtonPanel.add(submitButton);
        con.add(submitButtonPanel);
    }

    private JLabel createJLabel(String text, String colorCode) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode(colorCode));
        return label;
    }

    private void setBoldFont(JLabel label) {
        label.setFont(new Font("Aerial", Font.BOLD, 12));
    }

    public static class RequestFormBuilder {
        private String carDetails;
        private String dealerDetails;
        private int interestedPeopleCount;
        private RequestFormController requestFormController;

        public RequestFormBuilder withCarDetails(String carDetails) {
            this.carDetails = carDetails;
            return this;
        }

        public RequestFormBuilder withDealerDetails(String dealerDetails) {
            this.dealerDetails = dealerDetails;
            return this;
        }

        public RequestFormBuilder withInterestedPeopleCount(int interestedPeopleCount) {
            this.interestedPeopleCount = interestedPeopleCount;
            return this;
        }

        public RequestFormBuilder withRequestFormController(RequestFormController requestFormController) {
            this.requestFormController = requestFormController;
            return this;
        }

        public RequestForm build() {
            return new RequestForm(requestFormController, carDetails, dealerDetails, interestedPeopleCount);
        }
    }

    public static void main(String[] args) {
        RequestFormController controller = new RequestFormController(new VehicleTableDao(), new DealerTableDao(),
                new CustomerTableDao(), new LeadTableDao());
        JFrame f = controller.createRequestForm("aaaaa", "bbbbb");
        f.setTitle("Customer Request Form");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 600);
        f.setVisible(true);
    }
}