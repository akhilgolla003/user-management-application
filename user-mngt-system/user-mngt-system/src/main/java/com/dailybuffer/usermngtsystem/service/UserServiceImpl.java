package com.dailybuffer.usermngtsystem.service;

import com.dailybuffer.usermngtsystem.entity.UserEntity;
import com.dailybuffer.usermngtsystem.model.User;
import com.dailybuffer.usermngtsystem.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);

        userRepository.save(userEntity);
        return user;
    }

    @Override
    public List<User> getAllUsers() {

        List<UserEntity> userEntities = userRepository.findAll();

        List<User> users = userEntities.stream()
                .map(userEntity -> new User(
                        userEntity.getId(),
                        userEntity.getFirstName(),
                        userEntity.getLastName(),
                        userEntity.getEmailId()
                )).collect(Collectors.toList());

        return users;
    }

    @Override
    public User getUserById(long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        User user = new User();
        BeanUtils.copyProperties(userEntity,user);
        return user;
    }

    @Override
    public boolean deleteUserById(long id) {
        UserEntity user = userRepository.findById(id).get();
        userRepository.delete(user);
        return true;
    }

    @Override
    public User updateUser(long id, User user) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setEmailId(user.getEmailId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        userRepository.save(userEntity);
        return user;
    }


}
