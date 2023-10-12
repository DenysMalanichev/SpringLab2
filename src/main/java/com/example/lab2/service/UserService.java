package com.example.lab2.service;

import com.example.lab2.entity.Queue;
import com.example.lab2.entity.User;
import com.example.lab2.exceptions.ClosedQueueException;
import com.example.lab2.repository.Interfaces.QueueRepository;
import com.example.lab2.repository.Interfaces.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final QueueRepository queueRepository;

    public UserService(UserRepository userRepository, QueueRepository queueRepository) {
        this.userRepository = userRepository;
        this.queueRepository = queueRepository;
    }

    public User createNewUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User updatedUser){
        User user = userRepository.findById(id).orElse(null);

        if(user == null){
            throw new IllegalArgumentException("No user with id " + id);
        }

        user.setName(updatedUser.getName());

        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public int occupyPlaceInQueue(Long userId, Long queueId) throws ClosedQueueException {
        User user = userRepository.findById(userId).orElse(null);
        Queue queue = queueRepository.findById(queueId).orElse(null);

        if(user == null){
            throw new IllegalArgumentException("No user with id " + userId);
        }

        if(queue == null){
            throw new IllegalArgumentException("No queue with id " + queueId);
        }

        if(!queue.isOpen()){
            throw new ClosedQueueException("Cannot add user to closed queue");
        }

        queue.getUsers().add(user);

        queueRepository.save(queue);

        return queue.getUsers().size();
    }

    public List<User> viewUserAhead(Long queueId, Long userId) {
        Queue queue = queueRepository.findById(queueId).orElse(null);

        if (queue == null) {
            throw new IllegalArgumentException("Queue does not exists");
        }

        int userPlace = findUserPlace(queue.getUsers(), userId);

        if (userPlace == -1) {
            throw new IllegalArgumentException("User is not in this queue");
        }

        return queue.getUsers().subList(0, userPlace);
    }

    public static int findUserPlace(List<User> userList, Long userId) {
        for (int i = 0; i < userList.size(); i++) {
            if (Objects.equals(userList.get(i).getId(), userId)) {
                return i;
            }
        }
        return -1;
    }
}
