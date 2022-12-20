package capstone.els.services;

import capstone.els.enities.User;

public interface UserService {
    User findByEmail(String email);
}
