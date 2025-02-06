package com.treasuregame.treasure_hunt.service;

import com.treasuregame.treasure_hunt.model.Game;
import com.treasuregame.treasure_hunt.model.GameRiddle;
import com.treasuregame.treasure_hunt.model.Riddle;
import com.treasuregame.treasure_hunt.model.User;
import com.treasuregame.treasure_hunt.repository.GameRepository;
import com.treasuregame.treasure_hunt.repository.GameRiddleRepository;
import com.treasuregame.treasure_hunt.repository.RiddleRepository;
import com.treasuregame.treasure_hunt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private static final Logger log = LoggerFactory.getLogger(GameService.class);
    private final UserService userService;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final RiddleRepository riddleRepository;
    private final GameRiddleRepository gameRiddleRepository;


    public GameService(GameRepository gameRepository, UserRepository userRepository, RiddleRepository riddleRepository, GameRiddleRepository gameRiddleRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.riddleRepository = riddleRepository;
        this.gameRiddleRepository = gameRiddleRepository;
        this.userService = userService;

    }

    public Game createGameForAuthenticatedUser() {
        User user = userService.userAuthContext();
        if(user.getGameId() == -1){

            Game game = new Game();
            gameRepository.save(game);
            Iterable<Riddle> riddles = riddleRepository.findAll();
            riddles.forEach(riddle -> {
                log.info("Adding riddle: " + riddle.getRiddleName());
                GameRiddle gameRiddle = new GameRiddle();
                gameRiddle.setGameId(game.getId());
                gameRiddle.setRiddleId(riddle.getId());
                gameRiddleRepository.save(gameRiddle);
                });


            log.info("Creating game : "+ game.getId() +" for :  " + user.getId() + " name : " + user.getUsername());
            user.setGameId(game.getId());
            userRepository.save(user);

            return game;
        } else {

            return gameRepository.findById(user.getGameId())
                    .orElseThrow(() -> new IllegalStateException("Game not found for user"));
        }






    }
}
