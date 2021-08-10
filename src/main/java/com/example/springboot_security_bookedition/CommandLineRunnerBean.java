package com.example.springboot_security_bookedition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    @Autowired
    DirectorRepo directorRepo;

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    public void run(String...args){
        User user = new User("bart", "bart@domain.com", "bart", "Bart","Simpson", true);
        Role userRole = new Role("bart", "ROLE_USER");
        userRepo.save(user);
        roleRepo.save(userRole);

        User admin = new User("super", "super@domain.com", "super", "Super", "Hero", true);
        Role adminRole1 = new Role("super", "ROLE_ADMIN");
        Role adminRole2 = new Role("super", "ROLE_USER");

        userRepo.save(admin);
        roleRepo.save(adminRole1);
        roleRepo.save(adminRole2);

        Director director = new Director();

        director.setName("Rodney Rothman");
        director.setGenre("Action");

        //Create first movie
        Movie movie = new Movie();
        movie.setTitle("Into the Spider-Verse");
        movie.setYear(2018);
        movie.setDescription("Black Spiderman");
        movie.setDirector(director);

        //Lets create another movie
        Movie movie2 = new Movie();
        movie2.setTitle("22 Jump Street");
        movie2.setYear(2014);
        movie2.setDescription("Detectives go to high school");
        movie2.setDirector(director);

        Set<Movie> movies = new HashSet<>();
        movies.add(movie);
        movies.add(movie2);

        director.setMovies(movies);

        directorRepo.save(director);

    }

}
