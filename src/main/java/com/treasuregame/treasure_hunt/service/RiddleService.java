package com.treasuregame.treasure_hunt.service;


import com.treasuregame.treasure_hunt.DTO.RiddleDTO;
import com.treasuregame.treasure_hunt.DTO.RiddleResponse;
import com.treasuregame.treasure_hunt.DTO.SingleRiddleDTO;
import com.treasuregame.treasure_hunt.model.GameRiddle;
import com.treasuregame.treasure_hunt.model.Riddle;
import com.treasuregame.treasure_hunt.model.User;
import com.treasuregame.treasure_hunt.repository.GameRiddleRepository;
import com.treasuregame.treasure_hunt.repository.RiddleRepository;
import com.treasuregame.treasure_hunt.repository.UserRepository;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RiddleService {
    public final UserService userService;
    public final GameRiddleRepository gameRiddleRepository;
    public final RiddleRepository riddleRepository;
    UserRepository userRepository;

    public RiddleService(UserService userService,
                         GameRiddleRepository gameRiddleRepository,
                         RiddleRepository riddleRepository,
                         UserRepository userRepository
    ) {
        this.userService = userService;
        this.gameRiddleRepository = gameRiddleRepository;
        this.riddleRepository = riddleRepository;
        this.userRepository = userRepository;

    }

    public List<RiddleDTO> getAllRiddles() {
        Integer gameId = userService.userAuthContext().getGameId();
        List<GameRiddle> gameRiddles = gameRiddleRepository.findByGameId(gameId);

        return gameRiddles.stream()
                .map(gr -> {
                    Riddle riddle = riddleRepository.findById(gr.getRiddleId()).orElse(null);
                    return (riddle != null) ? new RiddleDTO(gr.getId(), riddle.getRiddleName(), gr.isSolved()) : null;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(RiddleDTO::getName))
                .collect(Collectors.toList());
    }



public Optional<SingleRiddleDTO> getRiddleByGameRiddleId(Integer gameRiddleId) {
    Optional<GameRiddle> gameRiddle = gameRiddleRepository.findById(gameRiddleId);

    if (gameRiddle.isPresent()) {
        Integer riddleId = gameRiddle.get().getRiddleId();
        boolean solved = gameRiddle.get().isSolved();

        Optional<Riddle> riddle = riddleRepository.findById(riddleId);
        if (riddle.isPresent()) {
            Riddle r = riddle.get();

            Double latitude = null;
            Double longitude = null;
            String answer = null;

            if (solved && r.getLocation() instanceof Point) {
                Point point = (Point) r.getLocation();
                latitude = point.getY();
                longitude = point.getX();
                answer = r.getRiddleAnswer();
            }

            return Optional.of(new SingleRiddleDTO(
                    gameRiddleId, r.getRiddleName(), r.getRiddleText(), answer, solved, latitude, longitude
            ));
        }
    }
    return Optional.empty();
}


    public RiddleResponse checkRiddleSolution(Integer gameRiddleId, double latitude, double longitude) {
        Optional<GameRiddle> optionalGameRiddle = gameRiddleRepository.findById(gameRiddleId);

        if (optionalGameRiddle.isEmpty()) {
            return new RiddleResponse("Game riddle not found", null, null, -1, false);
        }

        GameRiddle gameRiddle = optionalGameRiddle.get();
        Integer riddleId = gameRiddle.getRiddleId();

        Optional<Riddle> optionalRiddle = riddleRepository.findById(riddleId);
        if (optionalRiddle.isEmpty()) {
            return new RiddleResponse("Riddle not found", null, null, -1, false);
        }

        Riddle riddle = optionalRiddle.get();
        Geometry riddleLocation = riddle.getLocation();

        if (!(riddleLocation instanceof Point)) {
            return new RiddleResponse("Invalid riddle location", null, null, -1, false);
        }

        Point actualLocation = (Point) riddleLocation;


        double distance = calculateDistance(actualLocation, latitude, longitude);

        if (distance <= 300) {

            gameRiddle.setSolved(true);
            gameRiddleRepository.save(gameRiddle);


            User user = userService.userAuthContext();
            user.setScore(user.getScore() + 150);
            userRepository.save(user);

            return new RiddleResponse(
                    "Correct location! Riddle solved 🎉",
                    riddle.getRiddleAnswer(),
                    new double[]{actualLocation.getX(), actualLocation.getY()},
                    0,
                    true
            );
        } else {


            return new RiddleResponse(
                    "Incorrect location. You are " + distance + " meters away.",
                    null,
                    null,
                    distance,
                    false
            );
        }
    }


    private boolean isLocationMatch(Point actual, double lat, double lon) {
        return Math.abs(actual.getX() - lon) < 0.0001 && Math.abs(actual.getY() - lat) < 0.0001;
    }

    private double calculateDistance(Point actual, double lat, double lon) {
        Point userLocation = actual.getFactory().createPoint(new org.locationtech.jts.geom.Coordinate(lon, lat));
        return DistanceOp.distance(actual, userLocation) * 111_000;
    }
}
