package com.treasuregame.treasure_hunt.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;





    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +

                '}';
    }

    // No-args constructor
    public Game() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // All-args constructor
//    public Game( List<GameRiddle> gameRiddles) {
//
//
//        this.gameRiddles = gameRiddles;
//    }




//    public List<GameRiddle> getGameRiddles() {
//        return gameRiddles;
//    }
//
//    public void setGameRiddles(List<GameRiddle> gameRiddles) {
//        this.gameRiddles = gameRiddles;
//    }
}
