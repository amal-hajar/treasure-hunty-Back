package com.treasuregame.treasure_hunt.repository;

import com.treasuregame.treasure_hunt.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
}
