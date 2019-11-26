package m1t1.database.dao;

import m1t1.database.model.VehicleDetails;

public class VehicleTableDao {

    public VehicleDetails getVehicleDetails(String carId) {
        //TODO
        VehicleDetails item = new VehicleDetails();
        item.setId(carId);
        item.setDetails("model name: XXX, maker name: XXX, year: XXX");
        item.setInterestedPeopleCount(100);
        return item;
    }
}
