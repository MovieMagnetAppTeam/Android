package com.pam.pam_front.model;

import java.util.ArrayList;
import java.util.List;

public class MovieMock{
    public List<String> movieTitles;

    public MovieMock() {
        movieTitles = new ArrayList<>();
        movieTitles.add("Avengers");
        movieTitles.add("Ragnarok");
        movieTitles.add("Justice League");
        movieTitles.add("Coco");
        movieTitles.add("Last Jedi");
        movieTitles.add("Murder on the Orient Express");
        movieTitles.add("Lady Bird");
        movieTitles.add("Kingsman");
        movieTitles.add("Wonder");
        movieTitles.add("Disaster Artist");
        movieTitles.add("The Room");
        movieTitles.add("It");
        movieTitles.add("Jumanji");
        movieTitles.add("Logan Lucky");
        movieTitles.add("Logan");
        movieTitles.add("Wind River");
        movieTitles.add("Blade Runner");
        movieTitles.add("Homecoming");
    }

    public MovieMock(String s) {
        movieTitles = new ArrayList<>();
        movieTitles.add("Suits");
        movieTitles.add("Grey's anatomy");
        movieTitles.add("Punisher");
        movieTitles.add("Peaky Blinders");
        movieTitles.add("Vikings");
        movieTitles.add("Flash");
        movieTitles.add("Supergirl");
        movieTitles.add("Riverdale");
        movieTitles.add("Simpsons");
        movieTitles.add("Outlander");
        movieTitles.add("Game of thrones");
        movieTitles.add("Daredevil");
    }
}
