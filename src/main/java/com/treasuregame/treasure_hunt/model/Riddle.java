package com.treasuregame.treasure_hunt.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "riddles")
public class Riddle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Override
    public String toString() {
        return "Riddle{" +
                "id=" + id +
                ", riddleName='" + riddleName + '\'' +
                ", riddleText='" + riddleText + '\'' +
                ", location=" + location +
                ", riddleAnswer='" + riddleAnswer + '\'' +
                ", finalRiddle=" + finalRiddle +
                '}';
    }

    @Column(nullable = false)
    private String riddleName;

    public String getRiddleName() {
        return riddleName;
    }

    public String getRiddleAnswer() {
        return riddleAnswer;
    }

    @Column(nullable = false)
    private String riddleText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false, columnDefinition = "geometry(Point, 4326)")

    private Geometry location;

    @Column(nullable = false)
    private String riddleAnswer;



    @Column(nullable = false)
    private boolean finalRiddle;

    // No-args constructor
    public Riddle() {}

    // All-args constructor
    public Riddle(Integer id, String riddleName, String riddleText, Point location, boolean finalRiddle) {

        this.riddleName = riddleName;
        this.riddleText = riddleText;
        this.location = location;

        this.finalRiddle = finalRiddle;
    }

    // Getters and Setters
    public String getRiddleText() {
        return riddleText;
    }

    public void setRiddleText(String riddleText) {
        this.riddleText = riddleText;
    }

    public Geometry getLocation() {
        return location;
    }

    public void setLocation(Geometry location) {
        this.location = location;
    }





    public boolean isFinalRiddle() {
        return finalRiddle;
    }

    public void setFinalRiddle(boolean finalRiddle) {
        this.finalRiddle = finalRiddle;
    }
}
