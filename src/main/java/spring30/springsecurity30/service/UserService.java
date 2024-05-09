package spring30.springsecurity30.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import spring30.springsecurity30.config.UserUnfoUserDetails;
import spring30.springsecurity30.entity.UserInfo;
import spring30.springsecurity30.repository.UserInfoRepository;

@Service
public class UserService implements UserDetailsService {

	
	@Autowired
	UserInfoRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserInfo> userInfo = repository.findByName(username);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		String password = bCryptPasswordEncoder.encode( userInfo.get().getPassword());
		userInfo.get().setPassword(password);
		
		
		return userInfo.map(UserUnfoUserDetails::new).orElseThrow();
		
	}

}
