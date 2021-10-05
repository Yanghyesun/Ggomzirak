package com.ggomzirak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggomzirak.db.entity.Salt;
import com.ggomzirak.db.entity.User;
import com.ggomzirak.db.repository.UserRepository;
import com.ggomzirak.dto.RequestSignUpUser;
import com.ggomzirak.dto.SaltUtil;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

//    @Autowired
//    private SaltRepository saltRepository;

	@Autowired
	private SaltUtil saltUtil;

	@Override
	public void signUpUser(RequestSignUpUser reqUser) {
		User user = new User();
		String password = reqUser.getPassword();
		String salt = saltUtil.genSalt();

		String image = "https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/defaultImg.PNG";
		if (reqUser.getGender() == 0) {
			int number = (int) (Math.random()*3);
			if (number == 0)
				image = "https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/defaultImg.PNG";
			else if (number == 1)
				image = "https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/0c6781c9-3bb1-4902-b13d-cb3b361a215fboy2.png";
			else
				image = "https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/dd374a6f-88c8-4c54-93f4-3f597df43f92boy1.png";
		} else {
			int number = (int)(Math.random()*4);
			if (number == 0)
				image = "https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/0a1d522f-306a-42aa-9fef-72c8e4497240girl1.png";
			else if (number == 1)
				image = "https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/292ca788-0b40-477f-9397-9e7c23fdbaf1girl2.png";
			else if (number == 2)
				image = "https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/4059193d-f5e5-4555-8398-3c849a24ce64girl3.png";
			else
				image = "https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/0b8a5849-8c99-4d83-b3c6-611dd1b236ccgirl4.png";
		}

		user.setUserId(reqUser.getUserid());
		user.setEmail(reqUser.getEmail());
		user.setName(reqUser.getName());
		user.setSalt(new Salt(salt));
		user.setPassword(saltUtil.encodePassword(salt, password));
		user.setImage(image);
		user.setLevel("1");
		user.setPoints(0);
		userRepository.save(user);
	}

	@Override
	public User loginUser(String id, String password) throws Exception {
		System.out.println(id);
		User user = userRepository.findByUserId(id);
		if (user == null)
			throw new Exception("멤버가 조회되지 않음");
		String salt = user.getSalt().getSalt();
		password = saltUtil.encodePassword(salt, password);
		if (!user.getPassword().equals(password))
			throw new Exception("비밀번호가 틀립니다.");
		return user;
	}

	@Override
	public User updatePwd(String userid, String password) {
		User user = userRepository.findByUserId(userid);
		String salt = saltUtil.genSalt();
		user.setPassword(saltUtil.encodePassword(salt, password));
		user.setSalt(new Salt(salt));
		userRepository.save(user);
		return user;
	}
}
