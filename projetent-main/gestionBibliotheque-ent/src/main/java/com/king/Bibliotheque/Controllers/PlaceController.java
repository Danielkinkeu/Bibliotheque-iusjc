package com.king.Bibliotheque.Controllers;

import com.king.Bibliotheque.Models.Place;
import com.king.Bibliotheque.Services.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class PlaceController {
    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "addPlace", consumes = APPLICATION_JSON_VALUE)
    public void addPlace(@RequestBody Place place){
        this.placeService.addPlace(place);
    }

    @GetMapping(path = "place", produces = APPLICATION_JSON_VALUE)
    public List<Place> getPlace(){
        return this.placeService.search();
    }

    @GetMapping(path = "place/{id}", produces = APPLICATION_JSON_VALUE)
    public Place getPlaceById(@PathVariable int id){
        return this.placeService.getPlaceById(id);
    }

    @PutMapping(path = "updatePlace/{id}" ,consumes = APPLICATION_JSON_VALUE)
    public Place updatePlace(@PathVariable int id, @RequestBody Place updatedPlace) {
        return placeService.updatePlaceDetails(id, updatedPlace);
    }

    @DeleteMapping(path = "place/delete/{id}")
    public void deletePlace(@PathVariable int id) {
        placeService.deletePlace(id);
    }

}
