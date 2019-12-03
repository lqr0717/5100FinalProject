package m1t1.controller;

import m1t1.RequestForm;
import m1t1.database.dao.CustomerTableDao;
import m1t1.database.dao.DealerTableDao;
import m1t1.database.dao.CustomerRequestTableDao;
import m1t1.database.dao.VehicleTableDao;
import m1t1.database.model.CustomerInfo;
import m1t1.database.model.CustomerRequest;
import m1t1.database.model.DealerDetails;
import m1t1.database.model.VehicleDetails;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class RequestFormController {
    private static final Logger log = Logger.getLogger(RequestFormController.class.getSimpleName());

    private final VehicleTableDao vehicleTableDao;
    private final DealerTableDao dealerTableDao;
    private final CustomerTableDao customerTableDao;
    private final CustomerRequestTableDao customerRequestTableDao;

    public RequestFormController(VehicleTableDao vehicleTableDao, DealerTableDao dealerTableDao, CustomerTableDao customerTableDao, CustomerRequestTableDao customerRequestTableDao) {
        this.vehicleTableDao = vehicleTableDao;
        this.dealerTableDao = dealerTableDao;
        this.customerTableDao = customerTableDao;
        this.customerRequestTableDao = customerRequestTableDao;
    }


    public RequestForm createRequestForm(String carId, String dealerId) {
        VehicleDetails vehicleDetails = vehicleTableDao.getVehicleDetails(carId);
        DealerDetails dealerDetails = dealerTableDao.getDealerDetails(dealerId);

        return new RequestForm.RequestFormBuilder()
                .withCarDetails(vehicleDetails)
                .withDealerDetails(dealerDetails)
                .withInterestedPeopleCount(vehicleDetails.getInterestedPeopleCount())
                .withRequestFormController(this)
                .build();
    }

    public Optional<CustomerInfo> getCustomerInfo(String phoneNumber) {
        if (phoneNumber == null) {
            return Optional.empty();
        }

        String phoneNumberCleaned = phoneNumber.trim();
        return Optional.ofNullable(customerTableDao.getCustomerInfo(phoneNumberCleaned));
    }

    public void writeCustomerRequest(RequestForm requestForm) {
        CustomerRequest customerRequest = new CustomerRequest();
        try {
            String customerId = UUID.randomUUID().toString();
            customerRequest.setCustomerId(customerId);
            customerRequest.setVehicleId(requestForm.getCarDetails().getId());
            customerRequest.setDealerId(requestForm.getDealerDetails().getId());
            customerRequest.setFirstName(requestForm.getFirstNameField().getText());
            customerRequest.setLastName(requestForm.getLastNameField().getText());
            customerRequest.setEmail(requestForm.getEmailField().getText());
            customerRequest.setContactNo(requestForm.getPhoneNumField().getText());
            customerRequest.setComment(requestForm.getMessageArea().getText());
        } catch (NullPointerException e) {
            throw new RuntimeException("Some fields have null text.", e);
        }

        customerRequestTableDao.writeCustomerRequest(customerRequest);
        log.info("Successfully wrote customer request into table.");
    }
}
