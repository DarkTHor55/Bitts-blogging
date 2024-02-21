package com.Bitts.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.Bitts.configration.PassEnoder;
import com.Bitts.excepction.ResourceNotFound;
import com.Bitts.model.User;
import com.Bitts.repository.UserRepos;

import jakarta.websocket.Encoder;

@Service
public class UserService  {
	@Autowired
	private UserRepos userrepo;
	@Autowired
	private ModelMapper mapper;
	
//	private PassEnoder passencoder;

	@Autowired
	public UserService(UserRepos userRepos) {
		this.userrepo = userRepos;
	}

	public List<User> GetAllUser() {

		return userrepo.findAll();
	}

	public User getUserById(Long id) {
	
		User u= this.userrepo.findById(id).orElseThrow(()->new ResourceNotFound("User", "Id", id));
		return u;
	}

	public User createUser(User user) {
////	if(user.getConfirmPassword().equals(user.getPassword())){
//		User newUser = new User();
//		newUser.setId(user.getId());
//		newUser.setName(user.getName());
//		newUser.setEmail(user.getEmail());
//		newUser.setPassword(user.getPassword());
//		newUser.setAbout(user.getAbout());
//		return userrepo.save(user);

		//here we use modelmapper for create new user 
		
		User newuser=this.mapper.map(user, User.class);//which object then from which class
			userrepo.save(newuser);
return newuser;
	}

	public User deleteUser(Long id) {
		Optional<User>u=userrepo.findById(id);
		if(u.isEmpty()) {
			System.out.println("Not found");
		}
		userrepo.deleteById(id);
		return null;
	}

	public User UpdateUser(Long id, User user) {
//		// here we create a custom execption with lamda exxpression
//		User newuser =this.userrepo.findById(id).orElseThrow(()->new ResourceNotFound("User", "id", id));
		
		int a=0;
		for (User us : GetAllUser()) {
				if(us.getId().equals(id)) {
					deleteUser(id);
					User newUser = new User();
					newUser.setId(id);
					newUser.setName(user.getName());
					newUser.setEmail(user.getEmail());
					String pass=user.getPassword();
					newUser.setPassword(user.getPassword());
					newUser.setAbout(user.getAbout());
					userrepo.save(user);
					a=1;
				}
				
			
		}
		if(a==1) {
			System.out.println("changed.......");
			
		}else {
			System.out.println("id not found");
		}
		return null;
	}

}
