package com.ggomzirak.service;

import com.ggomzirak.db.entity.User;
import com.ggomzirak.dto.RequestSignUpUser;

public interface AuthService {
    void signUpUser(RequestSignUpUser user);

    User loginUser(String id, String password) throws Exception;

	User updatePwd(String userid, String password);
}
