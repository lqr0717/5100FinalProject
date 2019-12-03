package m1t1.database.dao;

import m1t1.database.model.VehicleDetails;
import m1t1.database.utils.SQLUtil;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehicleTableDao {
    private static final Logger log = Logger.getLogger(VehicleTableDao.class.getSimpleName());
    private static final String VEHICLE_ID = "vechileId";
    private static final String PRICE = "price";
    private static final String BRAND = "brand";
    private static final String DEALER_ID = "dealerId";
    private static final String MODEL = "model";
    private static final String DATE_OF_MANUFAC = "dateofmanufacturing";
    private static final String TYPE = "type";
    private static final String CATEGORY = "category";
    private static final String COLOR = "color";
    private static final String MILEAGE = "mileage";

    private final String url;
    private final String carTableName;

    private Connection connection;

    public VehicleTableDao(String url, String carTableName) {
        this.url = url;
        this.carTableName = carTableName;
        connection = SQLUtil.getConnection(url);
    }

    public VehicleDetails getVehicleDetails(String carId) {
        try {
            if (connection.isClosed()) {
                connection = SQLUtil.getConnection(url);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Statement statement = connection.createStatement()) {
            String querySyntax = String.format("SELECT * FROM dbo.%s WHERE %s = '%s'", carTableName, VEHICLE_ID, carId);
            ResultSet result = statement.executeQuery(querySyntax);

            if (result.next()) {
                VehicleDetails vehicleDetails = new VehicleDetails();
                vehicleDetails.setId(result.getString(VEHICLE_ID));
                vehicleDetails.setDealerId(result.getString(DEALER_ID));
                vehicleDetails.setInterestedPeopleCount(100);
                vehicleDetails.setPrice(result.getDouble(PRICE));
                vehicleDetails.setBrand(result.getString(BRAND));
                vehicleDetails.setModel(result.getString(MODEL));
                vehicleDetails.setDateOfManufacturing(result.getDate(DATE_OF_MANUFAC));
                vehicleDetails.setType(result.getString(TYPE));
                vehicleDetails.setCategory(result.getString(CATEGORY));
                vehicleDetails.setColor(result.getString(COLOR));
                vehicleDetails.setMileage(result.getDouble(MILEAGE));

                return vehicleDetails;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
