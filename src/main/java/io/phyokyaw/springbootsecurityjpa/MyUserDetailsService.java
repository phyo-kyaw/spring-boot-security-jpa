package io.phyokyaw.springbootsecurityjpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.phyokyaw.springbootsecurityjpa.MyUserDetails;
import io.phyokyaw.springbootsecurityjpa.models.User;
import io.phyokyaw.springbootsecurityjpa.models.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//return new MyUserDetails(userName);
		User actualUser = new User();
		
		Optional<User> user = userRepository.findByUserName(userName);
		
		user.orElseThrow( () -> new UsernameNotFoundException("Not found: " + userName) );
		//user.ifPresent( optional -> actualUser = optional );
		
		//return new MyUserDetails(actualUser);
		
		return user.map(MyUserDetails::new).get();   //.map(MyUserDetails::new).get();
	}

}
