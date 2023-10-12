package com.example.lab2.repository.Interfaces;

import com.example.lab2.entity.Queue;
import com.example.lab2.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QueueRepository extends CrudRepository<Queue, Long> {
    Optional<Queue> findByName(String name);
}
