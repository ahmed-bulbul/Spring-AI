package com.spring.ai.service;

import com.spring.ai.model.DateUtil;
import com.spring.ai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DataService {

    @Autowired
    private UserRepository userRepository;

}