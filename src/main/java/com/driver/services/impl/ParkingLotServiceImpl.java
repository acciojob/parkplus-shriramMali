package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.driver.model.SpotType.*;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {

        ParkingLot parkingLot=new ParkingLot(name,address);
        return parkingLotRepository1.save(parkingLot);
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
     Spot spot=new Spot();

     if(numberOfWheels==0 ||numberOfWheels==1) numberOfWheels=2;
     else if(numberOfWheels==3)numberOfWheels=4;

     if(numberOfWheels==2) spot.setSpotType(TWO_WHEELER);
     else if (numberOfWheels==4) spot.setSpotType(FOUR_WHEELER);
     else spot.setSpotType(OTHERS);
     spot.setOccupied(true);
     spot.setPricePerHour(pricePerHour);

     ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();
     parkingLot.getSpotList().add(spot);
     spot.setParkingLot(parkingLot);

    return spot;
    }

    @Override
    public void deleteSpot(int spotId) {
    Spot spot=spotRepository1.findById(spotId).get();
    ParkingLot parkingLot=spot.getParkingLot();
    parkingLot.getSpotList().remove(spot);
    spotRepository1.delete(spot);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        Spot spot=spotRepository1.findById(spotId).get();
        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();
        parkingLot.getSpotList().remove(spot);
        spot.setPricePerHour(pricePerHour);
        parkingLot.getSpotList().add(spot);

        spotRepository1.save(spot);

        return spot;

    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();

        List<Spot> list=parkingLot.getSpotList();
        for (Spot spot:list){
            spot.setParkingLot(null);
        }
        parkingLotRepository1.delete(parkingLot);
    }
}
