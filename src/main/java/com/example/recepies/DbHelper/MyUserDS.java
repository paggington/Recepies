package com.example.recepies.DbHelper;

import com.example.recepies.entities.AppUser;
import com.example.recepies.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDS implements UserDetailsService {
    @Autowired
    AppUserInterface appUserInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user=appUserInterface.findAppUserByUsername(username);
        user.orElseThrow(()->new UsernameNotFoundException(String.format("%s not found!",username)));
        return user.map(User::new).get();
    }
    public void saveNewUSer(AppUser appUser){
        appUser.setActive(true);
        appUser.setAverageReceiptsScore(0.0F);
        appUser.setNumberOfPublications(0);
        appUser.setDateOfRegistration(appUser.getCurrentDate());
        appUser.setLastActivityTime(appUser.getCurrentDate());
        appUser.setRole("ROLE_USER");
        appUser.setPassword(new BCryptPasswordEncoder(12).encode(appUser.getPassword()));
        appUserInterface.save(appUser);
    }
    public AppUser getUserByUsername(String username){
        AppUser user=appUserInterface.getAppUserByUsername(username).get();
        return user;
    }
    public void setNewDataAboutUser(AppUser user){
        appUserInterface.setNewDataToDb(user.getNumberOfPublications()+1, user.getUsername());
    }
}
