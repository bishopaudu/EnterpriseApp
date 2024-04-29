package com.cloudApp.cloudApp.RestApi;

import com.cloudApp.cloudApp.Film;
import com.cloudApp.cloudApp.FilmDAO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/test")
public class testcontroller {
    private final FilmDAO filmDAO;

    public testcontroller(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @GetMapping(path = "allfilms")
    public ArrayList<Film> getFilms(){
        return filmDAO.getAllFilms();
    }



    @GetMapping(path = "try")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}*/
