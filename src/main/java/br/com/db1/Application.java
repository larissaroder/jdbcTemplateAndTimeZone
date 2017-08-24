package br.com.db1;

import br.com.db1.model.Movie;
import br.com.db1.service.MovieService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

@SpringBootApplication(scanBasePackages = "br.com.db1")
public class Application implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private MovieService service;

    @Override
    public void run(String... strings) throws Exception {

        Random r = new Random();
        Movie movie = new Movie("Velozes e Furiosos", "08/08/2017 09:00");
        service.insertBatch(Arrays.asList(movie));
    }
}