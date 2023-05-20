package it.epicode.be.prenotazioni.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import it.epicode.be.prenotazioni.model.User;
import it.epicode.be.prenotazioni.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/user")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Page<User>> findAll(Pageable pageable) {
		Page<User> findAll = userService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> findById(@PathVariable(required = true) Long id) {
		Optional<User> find = userService.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/user")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> save(@RequestBody User user) {
		User save = userService.save(user);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		User save = userService.update(id, user);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/user/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		userService.delete(id);
		return new ResponseEntity<>("Element deleted", HttpStatus.OK);

	}

}
