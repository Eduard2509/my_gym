package com.alevel.gym.service;

import com.alevel.gym.model.LockedRoom;
import com.alevel.gym.model.Sex;
import com.alevel.gym.repository.LockedRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LockedRoomService {

    LockedRoomRepository lockedRoomRepository;

    public LockedRoomService(LockedRoomRepository lockedRoomRepository) {
        this.lockedRoomRepository = lockedRoomRepository;
    }

    public List<LockedRoom> findAllLockedForMan() {
        return lockedRoomRepository.findAllLockedForMan();
    }


    public LockedRoom findById(String id) {
        return lockedRoomRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public LockedRoom findByValueManLocked(int value, String condition){
        return lockedRoomRepository.findByValue(value, condition, Sex.MAN.name());
    }

    public LockedRoom findByValueWomanLocked(int value, String condition){
        return lockedRoomRepository.findByValue(value, condition, Sex.WOMAN.name());
    }

    public void save(LockedRoom lockedRoom) {
        lockedRoomRepository.save(lockedRoom);
    }

    public boolean findByVisitorId(String id) {
        return lockedRoomRepository.findByVisitorId(id).isEmpty();
    }

    public List<LockedRoom> findAllLockedForWoman() {
        return lockedRoomRepository.findAllLockedForWoman();
    }
}
