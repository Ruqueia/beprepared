package com.rkm.beprepared.service;

import com.rkm.beprepared.dto.response.StatsResponse;
import com.rkm.beprepared.model.User;

import java.util.List;

public interface UserService {

    String createUser(User user);

    User getUserById(Long id);

    StatsResponse getAllStats();
}
