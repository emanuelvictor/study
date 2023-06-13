package br.com.emanuelvictor.infrastrcture;

import br.com.emanuelvictor.application.dto.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class XMLConverterTests {

    @Test
    public void marshalAndUnmarshalTest() {
        final String name = "Bob";
        final String specie = "Cão";
        final String race = "Pastor Alemão";
        final String gender = "Macho";
        final String ageDate = "04/10/1989";
        final Animal animal = new Animal(name, specie, race, gender, ageDate);
        final Converter<Animal> animalConverter = new XMLConverter();

        animalConverter.convertToXML(animal, new File("./result.xml"));
        final Animal animalConverted = animalConverter.convertToObject(new File("./result.xml"), new File("AnimalSchema.xsd"));

        Assertions.assertEquals(name, animalConverted.getName());
        Assertions.assertEquals(specie, animalConverted.getSpecies());
        Assertions.assertEquals(race, animalConverted.getRace());
        Assertions.assertEquals(gender, animalConverted.getGender());
        Assertions.assertEquals(ageDate, animalConverted.getAgeDate());
    }
}
