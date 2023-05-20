package it.epicode.be.prenotazioni.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.prenotazioni.model.Postazione;
import it.epicode.be.prenotazioni.model.Prenotazione;
import it.epicode.be.prenotazioni.model.User;
import it.epicode.be.prenotazioni.service.PostazioneService;
import it.epicode.be.prenotazioni.service.PrenotazioneService;
import it.epicode.be.prenotazioni.service.UserService;

@RestController
@RequestMapping("/api")
public class PrenotazioneController {

	@Autowired
	private PrenotazioneService prenotazioneService;

	@Autowired
	private UserService userService;

	@Autowired
	private PostazioneService postazioneService;

	@GetMapping(path = "/prenotazione")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Prenotazione>> findAll(Pageable pageable) {
		Page<Prenotazione> findAll = prenotazioneService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(path = "/prenotazione/utente")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Prenotazione>> findPerUtente(Pageable pageable) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		
		Optional<User> user = userService.findByUsername(username);

		Page<Prenotazione> findAll = prenotazioneService.findByUser(user.get(), pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	

	@GetMapping(path = "/prenotazione/prenota")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> prenota(@RequestParam Long idUser, @RequestParam Long idPostazione, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataPrenotazione) {
		Optional<User> user = userService.findById(idUser);
		if (user.isEmpty()) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

		}

		Optional<Postazione> postazione = postazioneService.findById(idPostazione);
		if (postazione.isEmpty()) {
			return new ResponseEntity<>("Postazione not found", HttpStatus.NOT_FOUND);

		}

		Prenotazione prenota = prenotazioneService.prenota(user.get(), postazione.get(), dataPrenotazione);

		return new ResponseEntity<>(prenota, HttpStatus.OK);
		


	}

	@GetMapping(path = "/prenotazione/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Prenotazione> findById(@PathVariable(required = true) Long id) {
		Optional<Prenotazione> find = prenotazioneService.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/prenotazione")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Prenotazione> save(@RequestBody Prenotazione prenotazione) {
		Prenotazione save = prenotazioneService.save(prenotazione);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/prenotazione/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Prenotazione> update(@PathVariable Long id, @RequestBody Prenotazione prenotazione) {
		Prenotazione save = prenotazioneService.update(id, prenotazione);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/prenotazione/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		prenotazioneService.delete(id);
		return new ResponseEntity<>("Element deleted", HttpStatus.OK);

	}

}
