package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {

        if(!userRepository3.findById(userId).isPresent() || !parkingLotRepository3.findById(parkingLotId).isPresent()){
            //throw new Exception("Cannot make reservation");
            //Reserve a spot in the given parkingLot such that the total price is minimum. Note that the price per hour for each spot is different
            //Note that the vehicle can only be parked in a spot having a type equal to or larger than given vehicle
            //If parkingLot is not found, user is not found, or no spot is available, throw "Cannot make reservation" exception.
            throw new Exception("null");
        }

        ParkingLot parkingLot=parkingLotRepository3.findById(parkingLotId).get();
        List<Spot> spotList=parkingLot.getSpotList();

        int min =Integer.MIN_VALUE;
        Spot spot11=null;
        for(Spot spot:spotList){
            if(timeInHours*spot.getPricePerHour()<min){

              if((numberOfWheels==2 && (spot.getSpotType()==SpotType.TWO_WHEELER || spot.getSpotType()==SpotType.FOUR_WHEELER || spot.getSpotType()==SpotType.OTHERS))
                      || (numberOfWheels==4 && (spot.getSpotType()==SpotType.FOUR_WHEELER || spot.getSpotType()==SpotType.OTHERS)) || numberOfWheels>4 && spot.getSpotType()==SpotType.OTHERS)

              {   spot11=spot;
                  min=timeInHours*spot.getPricePerHour();
              }
            }
        }

        if(min==Integer.MAX_VALUE ||userRepository3.findById(userId).get()==null){
            throw new Exception("Cannot make reservation");
        }
       parkingLot.getSpotList().add(spot11);

     return null;
    }
}
