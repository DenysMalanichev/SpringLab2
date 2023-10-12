package com.example.lab2.repository.realization;

import com.example.lab2.entity.Queue;

import java.util.Optional;

public class QueueRepository implements com.example.lab2.repository.Interfaces.QueueRepository {
    @Override
    public Optional<Queue> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public <S extends Queue> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Queue> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Queue> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Queue> findAll() {
        return null;
    }

    @Override
    public Iterable<Queue> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Queue entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Queue> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
