package com.king.Bibliotheque.Services;

import com.king.Bibliotheque.Exceptions.RoleException;
import com.king.Bibliotheque.Exceptions.RoleNotFoundException;
import com.king.Bibliotheque.Models.Place;
import com.king.Bibliotheque.Repositories.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public void addPlace( Place place){
        if (placeRepository.findByLine(place.getLine()) != null && placeRepository.findByColumn(place.getColumn()) != null) {
            throw new RoleException("Place is already exist");
        }
        this.placeRepository.save(place);
    }

    public List<Place> search(){
        return this.placeRepository.findAll();
    }

    public Place getPlaceById(int id) {
        Optional<Place> optionalPlace = this.placeRepository.findById(id);
        return optionalPlace.orElse(null);
    }

    public Place updatePlaceDetails(int id, Place updatedPlace) {
        Optional<Place> existingPlace = placeRepository.findById(id);

        if (existingPlace.isPresent()) {
            Place place = existingPlace.get();

            // Update place details as needed
            place.setLine(updatedPlace.getLine());
            place.setColumn(updatedPlace.getColumn());

            // Save the updated role to the database
            return placeRepository.save(place);
        } else {
            throw new RoleNotFoundException("Place not found");
        }
    }
    public void deletePlace(int id) {
        Optional<Place> place = placeRepository.findById(id);
        if (place.isPresent()) {
            placeRepository.deleteById(id);
        } else {
            throw new RoleNotFoundException("Place not found");
        }
    }
}
