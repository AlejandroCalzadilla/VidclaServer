package org.mailgrupo13.vidcla.seeders;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/seed")
public class DBSeeder {


    @Autowired
    private VehiculoSeeder vehiculoSeeder;


    @PostMapping("")
    public void seed() throws CsvValidationException, IOException {
        vehiculoSeeder.seeder();
    }

}
