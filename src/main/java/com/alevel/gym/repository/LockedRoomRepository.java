package com.alevel.gym.repository;

import com.alevel.gym.model.LockedRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LockedRoomRepository extends CrudRepository<LockedRoom, String> {
    @Query(value = "select * from locked_room where condition = 'ON' and sex = 'MAN' order by value asc", nativeQuery = true)
    List<LockedRoom> findAllLockedForMan();

    @Query(value = "select * from locked_room where condition = :condition and sex = 'MAN' and value = :value order by value asc", nativeQuery = true)
    LockedRoom findByValue(@Param("value") int value, @Param("condition") String condition);
    Optional<LockedRoom> findById(String id);

    @Query(value = "select * from locked_room where visitor_id = :visitor_id and sex = 'MAN'", nativeQuery = true)
    List<LockedRoom> findByVisitorId(@Param("visitor_id") String id);

    @Query(value = "select * from locked_room where condition = 'ON' and sex = 'WOMAN' order by value asc", nativeQuery = true)
    List<LockedRoom> findAllLockedForWoman();
}
