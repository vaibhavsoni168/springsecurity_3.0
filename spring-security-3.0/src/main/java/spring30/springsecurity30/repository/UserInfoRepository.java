package spring30.springsecurity30.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import spring30.springsecurity30.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{

	
	
	public Optional<UserInfo> findByName(String name);
	
}
