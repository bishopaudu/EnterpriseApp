package com.cloudApp.cloudApp.RestApi;

import com.cloudApp.cloudApp.Film;
import com.cloudApp.cloudApp.FilmDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/film")
public class controllers {
    private final FilmDAO filmDAO;

    public controllers(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @GetMapping(path = "test")
    public String testApi(){
        return "Api Working";
    }

    @GetMapping(path = "allfilms")
    public ArrayList<Film> getFilms(){
        return filmDAO.getAllFilms();
    }

    @PutMapping("/{id}")
    public String updateFilm(
            @PathVariable int id,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String director,
            @RequestParam(required = false) String review
    ) {
        // Check if any fields are provided for update
        if (year == null && director == null && review == null) {
            return "No fields provided for update.";
        }

        // Get the film by its ID
        Film filmToUpdate = filmDAO.getFilmByID(id);
        if (filmToUpdate == null) {
            return "Film with ID " + id + " not found.";
        }

        // Update the film fields if provided
        if (year != null) {
            filmToUpdate.setYear(year);
        }
        if (director != null) {
            filmToUpdate.setDirector(director);
        }
        if (review != null) {
            filmToUpdate.setReview(review);
        }

        // Update the film in the database
        filmDAO.updateFilmByTitle(filmToUpdate);

        return "Film with ID " + id + " updated successfully.";
    }

}
