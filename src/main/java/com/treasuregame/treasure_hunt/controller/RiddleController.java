package com.treasuregame.treasure_hunt.controller;


import com.treasuregame.treasure_hunt.DTO.RiddleDTO;
import com.treasuregame.treasure_hunt.DTO.RiddleResponse;
import com.treasuregame.treasure_hunt.DTO.SingleRiddleDTO;
import com.treasuregame.treasure_hunt.DTO.SolveRiddleRequest;

import com.treasuregame.treasure_hunt.repository.RiddleRepository;
import com.treasuregame.treasure_hunt.service.RiddleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("riddles")
public class RiddleController {
    private static final Logger log = LoggerFactory.getLogger(RiddleController.class);
    public final RiddleService riddleService;
    private final RiddleRepository riddleRepository;


    public RiddleController(RiddleService riddleService, RiddleRepository riddleRepository ) {
        this.riddleService = riddleService;
        this.riddleRepository = riddleRepository;



    }


    @GetMapping
    public List<RiddleDTO> riddles() {

     return riddleService.getAllRiddles();
    }

    @GetMapping("/{riddleId}")
    public ResponseEntity<?> getRiddle(@PathVariable Integer riddleId) {
        Optional<SingleRiddleDTO> riddle = riddleService.getRiddleByGameRiddleId(riddleId);

        return riddle.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/solve/{gameRiddleId}")
    public ResponseEntity<?> solveRiddle(
            @PathVariable Integer gameRiddleId,
            @RequestBody SolveRiddleRequest request
    ) {
        RiddleResponse response = riddleService.checkRiddleSolution(gameRiddleId, request.latitude, request.longitude);
        return ResponseEntity.ok(response);
    }

}

