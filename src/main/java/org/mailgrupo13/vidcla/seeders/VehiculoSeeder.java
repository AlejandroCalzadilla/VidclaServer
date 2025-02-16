package org.mailgrupo13.vidcla.seeders;

import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.MarcaV;
import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.Vehiculo;
import org.mailgrupo13.vidcla.Inventario.vehiculo.repositories.MarcaVRepository;
import org.mailgrupo13.vidcla.Inventario.vehiculo.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;

@Service
public class VehiculoSeeder {

    @Autowired
    private MarcaVRepository marcaVRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public void seeder() throws IOException, CsvValidationException {
        // Cargar el archivo desde la misma carpeta que VehiculoSeeder
        File file = new File("src/main/java/org/mailgrupo13/vidcla/seeders/catalogovidcla.csv");

        try (InputStream inputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             CSVReader csvReader = new CSVReader(inputStreamReader)) {

            String[] nextLine;
            boolean isFirstRow = true;

            while ((nextLine = csvReader.readNext()) != null) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue; // Saltar la fila de encabezado
                }

                // Verificar si la fila está vacía
                if (nextLine.length == 0 || Arrays.stream(nextLine).allMatch(String::isEmpty)) {
                    continue;
                }

                System.out.println("filas de el csv: " + nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3]);

                // Verificar si las celdas no están vacías antes de convertir
                Integer codigoMarca = !nextLine[0].isEmpty() ? Integer.valueOf(nextLine[0]) : null;
                String descripcion = nextLine[1];
                String yearInicio = nextLine[2];
                String yearFin = nextLine[3];

                MarcaV marcaV = marcaVRepository.findByCodigo(codigoMarca)
                        .orElseThrow(() -> new RuntimeException("Marca con código " + codigoMarca + " no encontrada"));

                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setDescripcion(descripcion);
                vehiculo.setYear_inicio(yearInicio);
                vehiculo.setYear_fin(yearFin);
                vehiculo.setMarcaV(marcaV);
                Optional<Vehiculo> lastVehiculo = vehiculoRepository.findTopByMarcaVIdOrderByCodigoDesc(marcaV.getId());
                int newCodigo = lastVehiculo.map(v -> Integer.parseInt(v.getCodigo()) + 1).orElse(marcaV.getCodigo());
                vehiculo.setCodigo(String.valueOf(newCodigo));

                vehiculoRepository.save(vehiculo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}