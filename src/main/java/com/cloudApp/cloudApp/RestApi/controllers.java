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

    @GetMapping(path="getfilmbyId")
    public ResponseEntity<Film>  getById(@RequestParam int Id){
       Film film = filmDAO.getFilmByID(Id);
       if (film == null){
           return ResponseEntity.notFound().build();
       } else{
           return  ResponseEntity.ok(film);
        }
    }

    @PostMapping(path = "add")
    public ResponseEntity<String> addfilm(@RequestBody Film film){
        try{
            filmDAO.addFilm(film);
            return ResponseEntity.ok("added Successfully");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(
                    "failed to add " + film.getTitle() + "to database");
        }
    }

    @PutMapping("/{id}")
    public String updateFilm(
            @PathVariable int id,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String director,
            @RequestParam(required = false) String review
    ) {
        if (year == null && director == null && review == null) {
            return "No values provided for update.";
        }
        Film filmToUpdate = filmDAO.getFilmByID(id);
        if (filmToUpdate == null) {
            return "Film with ID " + id + " not found.";
        }
        if (year != null) {
            filmToUpdate.setYear(year);
        }
        if (director != null) {
            filmToUpdate.setDirector(director);
        }
        if (review != null) {
            filmToUpdate.setReview(review);
        }
        filmDAO.updateFilmByID(id, year, director, review);
        return "Film with ID " + id + " updated successfully.";
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<String> deleteFilm(@RequestParam String title){
        try {
            filmDAO.deleteFilmByTitle(title);
            return  ResponseEntity.ok("Deleted");
        } catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.internalServerError().body("unable to delete");

        }
    }



}
