package com.mt.springbootmongo.service;

import com.mt.springbootmongo.repository.UserSequencesRepository;
import com.mt.springbootmongo.domain.User;
import com.mt.springbootmongo.exception.ResourceNotFoundException;
import com.mt.springbootmongo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

@Service
@Repository
public class UserService {
    private final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSequencesRepository userSequencesRepository;

    @Autowired
    private NextSequenceService nextSequenceService;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getAllUsers(){
        Pageable pageableRequest= PageRequest.of(0,15);
        Page<User> pages= userRepository.findAll(pageableRequest);
        List<User> users=pages.getContent();
        //return mongoTemplate.findAll(User.class);
       //return userRepository.findAll(new Sort(Sort.Direction.ASC, "firstName"));
       // users.remove(0);
        return users;
    }

    public User getUserByName(String firstName) throws ResourceNotFoundException{
        Query query=new Query();
        //query.addCriteria(Criteria.where("id").is(userId));
        query.addCriteria(Criteria.where("firstName").is(firstName));
        return mongoTemplate.findOne(query,User.class);

       /* User user=userRepository.findById(userId).
               orElseThrow(()-> new ResourceNotFoundException("User not found for this id ::"+userId));*/
    }

    public User getUserById(String userId) throws ResourceNotFoundException{
        Query query=new Query();
        //query.addCriteria(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("_id").is(userId));
        return mongoTemplate.findOne(query,User.class);

       /* User user=userRepository.findById(userId).
               orElseThrow(()-> new ResourceNotFoundException("User not found for this id ::"+userId));*/
    }

    public User save(User user){
        //return userRepository.save(user);
        User iUser=new User();
        iUser.setSeq(nextSequenceService.getNextSequence("users_seq"));
        iUser.setFirstName(user.getFirstName());
        iUser.setLastName(user.getLastName());
        iUser.setPhone(user.getPhone());
        iUser.setEmail(user.getEmail());
        return mongoTemplate.save(iUser);
    }

    public User updateUser(User userDetails, String firstName){
        Query query=new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        User user= mongoTemplate.findOne(query, User.class);
        user.setSeq(userDetails.getSeq());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPhone(userDetails.getPhone());
        user.setEmail(userDetails.getEmail());

        return userRepository.save(user);
    }

    public User updateUserById(User userDetails, String userId){
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(userId));
        User user= mongoTemplate.findOne(query, User.class);
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPhone(userDetails.getPhone());
        user.setEmail(userDetails.getEmail());

        return userRepository.save(user);
    }

    public String deleteUser(String firstName){
        Query query=new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        User user= mongoTemplate.findOne(query, User.class);
       //Optional<User> user1=userRepository.findOne(user.getId());
       // mongoTemplate.remove(user, "users");
        userRepository.delete(user);
        return "user has deleted";
    }

    public String deleteUserById(String userId){
        Optional<User> user1=userRepository.findById(userId);
        // mongoTemplate.remove(user, "users");
        userRepository.delete(user1.get());
        return "user has deleted";
    }


}
