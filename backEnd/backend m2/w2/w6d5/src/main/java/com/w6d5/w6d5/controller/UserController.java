package com.w6d5.w6d5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.w6d5.w6d5.model.User;
import com.w6d5.w6d5.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserService us;
	
	@GetMapping(path = "/user/{id}")
	public ResponseEntity<User> findById(@PathVariable(required = true) int id) {
	Optional<User> find = us.getById(id);	

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	@GetMapping(path = "login_success")
	public String success(){
		return "welcome";
		
	}
	@GetMapping(path = "/allUser")
	public ResponseEntity<List<User>> findAll() {
		List<User> findAll = us.getall();

		if (findAll.size()>0) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(path = "admin/user")
	public ResponseEntity<User> save(@RequestBody User user) {
		User save = us.save(user);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "admin/user/{id}")
	public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user) throws Exception {
		User save = us.update(id, user);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	@PutMapping(path = "admin/giveDevice")
	public ResponseEntity<User> giveDevice(@RequestBody int UserId,int DeviceId) throws Exception {
		User save = us.giveDevice(UserId, DeviceId);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	@PutMapping(path = "admin/removeDevice")
	public ResponseEntity<User> removeDevice(@RequestBody int UserId,int DeviceId) throws Exception {
		User save = us.removeDevice(UserId, DeviceId);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "admin/user/{id}")
	
	public ResponseEntity<String> delete(@PathVariable int id) {
		us.delete(id);
		return new ResponseEntity<>("Element deleted", HttpStatus.OK);
	}
}
