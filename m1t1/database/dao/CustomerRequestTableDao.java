package m1t1.database.dao;

import m1t1.database.model.CustomerRequest;
import m1t1.database.utils.SQLUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerRequestTableDao {
    private static final String CUSTOMER_ID = "customerId";
    private static final String VEHICLE_ID = "vehicleId";
    private static final String DEALER_ID = "dealerId";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String CONTACT_NO = "contactNo";
    private static final String COMMENT = "comment";


    private final String url;
    private final String customerRequestTableName;

    private Connection connection;

    public CustomerRequestTableDao(String url, String customerRequestTableName) {
        this.url = url;
        this.customerRequestTableName = customerRequestTableName;
        connection = SQLUtil.getConnection(url);
    }

    public void writeCustomerRequest(CustomerRequest customerRequest) {
        try {
            if (connection.isClosed()) {
                connection = SQLUtil.getConnection(url);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Statement statement = connection.createStatement()) {
            String writeSyntax = String.format("INSERT INTO dbo.%s (%s, %s, %s, %s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    customerRequestTableName, CUSTOMER_ID, VEHICLE_ID, DEALER_ID, FIRST_NAME, LAST_NAME, EMAIL, CONTACT_NO, COMMENT,
                    customerRequest.getCustomerId(), customerRequest.getVehicleId(), customerRequest.getDealerId(),
                    customerRequest.getFirstName(), customerRequest.getLastName(), customerRequest.getEmail(),
                    customerRequest.getContactNo(), SQLUtil.escape(customerRequest.getComment()));
            statement.executeUpdate(writeSyntax);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
