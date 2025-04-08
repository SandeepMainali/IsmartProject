package Meeting_Management.System.Service;

import Meeting_Management.System.Entity.User;
import Meeting_Management.System.Repository.UserRepo;
import Meeting_Management.System.Service.Impl.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    @Autowired
    public UserDetailServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepo.findByUserName(username);

         if (userOptional.isPresent()) {
            return new UserPrincipal(userOptional.get());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
