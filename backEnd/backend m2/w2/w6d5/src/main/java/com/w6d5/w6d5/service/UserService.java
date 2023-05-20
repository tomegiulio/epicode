package com.w6d5.w6d5.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.w6d5.w6d5.enumerated.State;
import com.w6d5.w6d5.model.Device;
import com.w6d5.w6d5.model.User;
import com.w6d5.w6d5.repository.DeviceRepository;
import com.w6d5.w6d5.repository.UserRepository;

@Service
public class UserService {
@Autowired
UserRepository ur;
@Autowired
DeviceRepository dr;
@Bean
public PasswordEncoder encoderr(){
    return new BCryptPasswordEncoder();
}

public Optional<User> getById(int id) {
	return ur.findById(id);
	
}
public List<User> getall() {
	return ur.findAll();
}
public User save(User u) {
	u.setPassword(encoderr().encode(u.getPassword()));
	boolean check=false;
	for(User x:getall()) {
		if(x.getUsername()==u.getUsername()) {
			check=true;
		}
	}
	if(check==true) {
		System.out.println("username already taked");
		System.exit(0);
	}
	
	return ur.save(u);

}

public User update(int id,User userr) throws Exception {
	Optional<User> userResult = ur.findById(id);
	if (userResult.isPresent()) {
		User user = userResult.get();
		user.setFullName(userr.getFullName());
		user.setUsername(userr.getUsername());
		user.setEmail(userr.getEmail());
		ur.save(user);
		return user;
	}else {
		throw new Exception("valori non aggiornati");
	}
	

}
public User giveDevice(int userId,int deviceId) throws Exception {
	Optional<User> userResult = ur.findById(userId);
	Optional<Device> deviceResult = dr.findById(userId);
	if (userResult.isPresent()&&deviceResult.isPresent()) {
		User user = userResult.get();
		Device device=deviceResult.get();
		if(device.getState()==State.AVAILABLE) {
			user.setDevices(new HashSet<>() {private static final long serialVersionUID = 1L;
			{
				add(device);
			}});
			device.setState(State.ASSIGNED);
		}else {
			throw new Exception("dispositivo non assegnato");
		}
		
		
		ur.save(user);
		dr.save(device);
		return user;
	}else {
		throw new Exception("valori non aggiornati");
	}
}
public User removeDevice(int userId,int deviceId) throws Exception {
	Optional<User> userResult = ur.findById(userId);
	Optional<Device> deviceResult = dr.findById(userId);
	if (userResult.isPresent()&&deviceResult.isPresent()) {
		User user = userResult.get();
		Device device=deviceResult.get();
		if(user.getDevices().contains(device)) {
			user.setDevices(new HashSet<>() {private static final long serialVersionUID = 1L;
			{
				remove(device);
			}});
			device.setState(State.AVAILABLE);
		}else {
			throw new Exception("dispositivo non rimosso");
		}
		
		
		ur.save(user);
		dr.save(device);
		return user;
	}else {
		throw new Exception("valori non aggiornati");
	}
}

public void delete(int id) {
	ur.deleteById(id);
}



}
