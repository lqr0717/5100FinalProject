package m1t1;

import m1t1.controller.RequestFormController;
import m1t1.database.dao.CustomerTableDao;
import m1t1.database.dao.DealerTableDao;
import m1t1.database.dao.CustomerRequestTableDao;
import m1t1.database.dao.VehicleTableDao;
import m1t1.database.model.CustomerInfo;
import m1t1.database.model.DealerDetails;
import m1t1.database.model.VehicleDetails;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;
import java.util.UUID;

public class RequestForm extends JFrame{
    private static final String BLUE_CODE = "#CDE7FB";
    private static final String BLACK_CODE = "#000000";
    private static final String RED_CODE = "#F08080";

    private JLabel firstNameLabel, lastNameLabel, emailLabel, phoneNumLabel;
    private JTextField firstNameField, lastNameField, emailField, phoneNumField;
    private JTextArea messageArea;
    private JButton submitButton;

    private RequestFormController requestFormController;
    private VehicleDetails carDetails;
    private DealerDetails dealerDetails;

    private RequestForm(RequestFormController requestFormController, VehicleDetails carDetails, DealerDetails dealerDetails,
                        int interestedPeopleCount) {
        this.requestFormController = requestFormController;
        this.carDetails = carDetails;
        this.dealerDetails = dealerDetails;

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
        if (carDetails != null) {
            carDetailsField.setText(carDetails.getDetails());
        }
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
        if (dealerDetails != null) {
            dealerDetailsField.setText(dealerDetails.getDetails());
        }
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

        messageArea = new JTextArea(10, 36);
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

        submitButton.addActionListener((ActionEvent ae) -> {
            writeToTable();
            System.exit(0);
        });

        phoneNumField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAndFillCustomerInfo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAndFillCustomerInfo();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAndFillCustomerInfo();
            }
        });

    }

    public VehicleDetails getCarDetails() {
        return carDetails;
    }

    public DealerDetails getDealerDetails() {
        return dealerDetails;
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPhoneNumField() {
        return phoneNumField;
    }

    public JTextArea getMessageArea() {
        return messageArea;
    }

    private void writeToTable(){
        requestFormController.writeCustomerRequest(this);
    }

    private void searchAndFillCustomerInfo() {
        String phoneNumber = phoneNumField.getText();
        Optional<CustomerInfo> customerInfo = requestFormController.getCustomerInfo(phoneNumber);

        if (customerInfo.isPresent()) {
            emailField.setText(customerInfo.get().getEmail());
            firstNameField.setText(customerInfo.get().getFirstName());
            lastNameField.setText(customerInfo.get().getLastName());
        }
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
        private VehicleDetails carDetails;
        private DealerDetails dealerDetails;
        private int interestedPeopleCount;
        private RequestFormController requestFormController;

        public RequestFormBuilder withCarDetails(VehicleDetails carDetails) {
            this.carDetails = carDetails;
            return this;
        }

        public RequestFormBuilder withDealerDetails(DealerDetails dealerDetails) {
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
        String sqlUrl = "jdbc:sqlserver://is-swang01.ischool.uw.edu:1433;databaseName=VechileManagementSystem;user=INFO6210;password=NEUHusky!";
        String carTableName = "CarInventory";
        String customerRequestTableName = "CustomerRequest";

        RequestFormController controller = new RequestFormController(new VehicleTableDao(sqlUrl, carTableName), new DealerTableDao(),
                new CustomerTableDao(), new CustomerRequestTableDao(sqlUrl, customerRequestTableName));
        JFrame f = controller.createRequestForm("SED2018001", "bbbbb");
        f.setTitle("Customer Request Form");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 600);
        f.setVisible(true);
    }
}