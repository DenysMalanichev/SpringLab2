package com.example.lab2.service;

import com.example.lab2.entity.Queue;
import com.example.lab2.entity.User;
import com.example.lab2.exceptions.NoQueueException;
import com.example.lab2.repository.Interfaces.QueueRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class QueueService {

    private final QueueRepository queueRepository;

    public QueueService(QueueRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    public Queue createQueue(User owner, String queueName) {
        Queue queue = new Queue(queueName, owner);
        return queueRepository.save(queue);
    }

    public Queue getQueue(Long queueId){
        return queueRepository.findById(queueId).orElse(null);
    }

    public Queue updateQueue(Long queueId, Queue updatedQueue){
        Queue queue = queueRepository.findById(queueId).orElse(null);

        if(queue == null){
            throw new IllegalArgumentException("No queue with id " + queueId);
        }

        queue.setName(updatedQueue.getName());
        queue.setOwner(updatedQueue.getOwner());
        queue.setUsers(updatedQueue.getUsers());
        queue.setOpen(updatedQueue.isOpen());

        return queueRepository.save(queue);
    }

    public void deleteQueue(Long queueId){
        queueRepository.deleteById(queueId);
    }

    public Queue findQueueByOwner(Long ownerId){
        for (Queue q : queueRepository.findAll()) {
            if(Objects.equals(q.getOwner().getId(), ownerId))
                return q;
        }
        return null;
    }

    public User next(Long queueId) throws NoQueueException {
        Queue queue = queueRepository.findById(queueId).orElse(null);

        if(queue == null){
            throw new NoQueueException("Queue with id " + queueId + " does not exists.");
        }

        User user = queue.getUsers().pop();

        queueRepository.save(queue);

        return user;
    }

    public boolean removeFromQueue(Long queueId, Long userId) throws NoQueueException {
        Queue queue = queueRepository.findById(queueId).orElse(null);

        if(queue == null){
            throw new NoQueueException("Queue with id " + queueId + " does not exists.");
        }

        boolean isRemoved = queue.getUsers().remove(userId);

        queueRepository.save(queue);

        return isRemoved;
    }

    public void closeQueue(Long queueId) throws NoQueueException {
        Queue queue = queueRepository.findById(queueId).orElse(null);

        if(queue == null){
            throw new NoQueueException("Queue with id " + queueId + " does not exists.");
        }

        queue.setOpen(false);
    }

    public List<Queue> listOpenQueues() {
        ArrayList<Queue> openQueues = new ArrayList<>();

        for (Queue q: queueRepository.findAll()) {
            if(!q.isOpen())
                openQueues.add(q);
        }

        return openQueues;
    }
}
