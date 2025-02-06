package com.treasuregame.treasure_hunt.controller;

import com.treasuregame.treasure_hunt.model.Game;
import com.treasuregame.treasure_hunt.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/games")
public class GameController {


    private final GameService gameService;

    public GameController(GameService gameService) {

        this.gameService = gameService;
    }


    @PostMapping
    public ResponseEntity<Game> createGame() {
        Game createdGame = gameService.createGameForAuthenticatedUser();
        return ResponseEntity.status(201).body(createdGame);
    }



}