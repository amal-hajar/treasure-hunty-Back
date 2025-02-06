package com.treasuregame.treasure_hunt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "game_riddles")
public class GameRiddle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "game_id", nullable = false)
    private Integer gameId;

    @Column(name = "riddle_id", nullable = false)
    private Integer riddleId;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getRiddleId() {
        return riddleId;
    }

    public void setRiddleId(Integer riddleId) {
        this.riddleId = riddleId;
    }

    @Column(nullable = false)
    private boolean solved = false;  // Indicates if the riddle is solved in this game

    // No-args constructor
    public GameRiddle() {}

    // All-args constructor


    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }





    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
