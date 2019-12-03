package m1t1.database.dao;

import m1t1.database.model.CustomerInfo;

public class CustomerTableDao {


    public CustomerInfo getCustomerInfo(String phoneNumberCleaned) {
        //TODO - wait for Customer Information table to be created.
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setPhoneNumber(phoneNumberCleaned);
        customerInfo.setEmail("xxx@gmail.com");
        customerInfo.setFirstName("xxx");
        customerInfo.setLastName("xxx");
        return customerInfo;
    }
}
