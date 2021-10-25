package com.example.recepies.Services;

import com.example.recepies.DbHelper.AppUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    @Autowired
    private AppUserInterface appUserInterface;

}
