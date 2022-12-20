package capstone.els.serviceImpls;

import capstone.els.enities.User;
import capstone.els.repositories.UserRepository;
import capstone.els.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findByEmail(String email) {
        User user  = userRepository.findUserByEmail(email);
        if(user == null) throw new UsernameNotFoundException("USER_NOT_FOUND");
        return user;
    }
}
