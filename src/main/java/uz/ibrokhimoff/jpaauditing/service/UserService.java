package uz.ibrokhimoff.jpaauditing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.ibrokhimoff.jpaauditing.entity.User;
import uz.ibrokhimoff.jpaauditing.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService{
    
    @Autowired
    private UserRepository userRepository;
    
    public ResponseEntity<User> create(User user){
        User userTemp = userRepository.save(user);
        return ResponseEntity.ok().body(userTemp);
    }
    
    public ResponseEntity<User> update(User user){
        Optional<User> userTemp = userRepository.findByUsername(user.getUsername());
        
        User saveUser = new User();
        
        if(userTemp.isPresent()){
            saveUser = userTemp.get();
            saveUser.setPassword(user.getPassword());
            
            return ResponseEntity.ok().body(userRepository.saveAndFlush(saveUser));
        }
        else
            return null;
    }
}