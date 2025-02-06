package com.treasuregame.treasure_hunt.repository;

import com.treasuregame.treasure_hunt.model.GameRiddle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRiddleRepository extends CrudRepository<GameRiddle, Integer> {
    List<GameRiddle> findByGameId(Integer gameId);

}
