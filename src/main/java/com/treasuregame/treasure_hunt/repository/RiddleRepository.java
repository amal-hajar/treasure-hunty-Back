package com.treasuregame.treasure_hunt.repository;

import com.treasuregame.treasure_hunt.model.Riddle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiddleRepository extends CrudRepository<Riddle, Integer> {

}
