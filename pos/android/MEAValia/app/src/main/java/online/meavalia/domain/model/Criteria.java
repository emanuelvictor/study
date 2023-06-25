package online.meavalia.domain.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import online.meavalia.domain.converters.BigDecimalConverter;


@Entity
public class Criteria implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String sentence;
    @NonNull
    private int priority;
    private Boolean legalPerson;
    private String document;
    private String email;

    @TypeConverters(BigDecimalConverter.class)
    private BigDecimal avg;
    private int countOfAssessments = 0;

    public Criteria() {
    }

    public Criteria(@NonNull String name, @NonNull String sentence, final int valueOfPriority) {
        this.name = name;
        this.sentence = sentence;
        this.priority = (valueOfPriority);
    }

    public Criteria(@NonNull String name, @NonNull String sentence, String document, String email, int valueOfPriority, Boolean legalPerson) {
        this.name = name;
        this.sentence = sentence;
        this.document = document;
        this.email = email;
        this.priority = (valueOfPriority);
        this.legalPerson = legalPerson;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public Boolean getLegalPerson() {
        return legalPerson;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public int getCountOfAssessments() {
        return countOfAssessments;
    }

    public String getName() {
        return name;
    }

    public String getSentence() {
        return sentence;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setSentence(@NonNull String sentence) {
        this.sentence = sentence;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setLegalPerson(Boolean legalPerson) {
        this.legalPerson = legalPerson;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public void setCountOfAssessments(int countOfAssessments) {
        this.countOfAssessments = countOfAssessments;
    }

    public void addAssessment(final int newNote) {

        if (avg == null) {
            avg = BigDecimal.valueOf(newNote);
            countOfAssessments++;
            return;
        }
        final BigDecimal avgMultiplied = avg.multiply(BigDecimal.valueOf(countOfAssessments));
        avg = avgMultiplied.add(BigDecimal.valueOf(newNote)).divide(BigDecimal.valueOf(countOfAssessments + 1), 2, RoundingMode.HALF_UP);
        this.countOfAssessments++;
    }

    public BigDecimal getAvg() {
        if(avg == null)
            return null;
        final MathContext round = new MathContext(3);
        return avg.round(round);
    }

}