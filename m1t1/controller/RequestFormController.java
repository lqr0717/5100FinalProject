package m1t1.controller;

import m1t1.RequestForm;
import m1t1.database.dao.CustomerTableDao;
import m1t1.database.dao.DealerTableDao;
import m1t1.database.dao.LeadTableDao;
import m1t1.database.dao.VehicleTableDao;
import m1t1.database.model.DealerDetails;
import m1t1.database.model.VehicleDetails;

public class RequestFormController {
    private final VehicleTableDao vehicleTableDao;
    private final DealerTableDao dealerTableDao;
    private final CustomerTableDao customerTableDao;
    private final LeadTableDao leadTableDao;

    public RequestFormController(VehicleTableDao vehicleTableDao, DealerTableDao dealerTableDao, CustomerTableDao customerTableDao, LeadTableDao leadTableDao) {
        this.vehicleTableDao = vehicleTableDao;
        this.dealerTableDao = dealerTableDao;
        this.customerTableDao = customerTableDao;
        this.leadTableDao = leadTableDao;
    }


    public RequestForm createRequestForm(String carId, String dealerId) {
        VehicleDetails vehicleDetails = vehicleTableDao.getVehicleDetails(carId);
        DealerDetails dealerDetails = dealerTableDao.getDealerDetails(dealerId);

        return new RequestForm.RequestFormBuilder()
                .withCarDetails(vehicleDetails.getDetails())
                .withDealerDetails(dealerDetails.getDetails())
                .withInterestedPeopleCount(vehicleDetails.getInterestedPeopleCount())
                .withRequestFormController(this)
                .build();
    }
}
