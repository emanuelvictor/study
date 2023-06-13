package br.com.emanuelvictor.application.dto;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "animal")
public class Animal {
    private String name;
    private String species;
    private String race;
    private String gender;
    private String ageDate;

    public Animal() {
    }

    public Animal(String name, String species, String race, String gender, String ageDate) {
        this.name = name;
        this.species = species;
        this.race = race;
        this.gender = gender;
        this.ageDate = ageDate;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public String getSpecies() {
        return species;
    }

    @XmlElement
    public String getRace() {
        return race;
    }

    @XmlElement
    public String getGender() {
        return gender;
    }

    @XmlElement
    public String getAgeDate() {
        return ageDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAgeDate(String ageDate) {
        this.ageDate = ageDate;
    }
}
