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

import it.epicode.be.prenotazioni.model.Citta;
import it.epicode.be.prenotazioni.service.CittaService;

@RestController
@RequestMapping("/api")
public class CittaController {

	@Autowired
	private CittaService cittaService;

	@GetMapping(path = "/citta")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Citta>> findAll(Pageable pageable) {
		Page<Citta> findAll = cittaService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/citta/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Citta> findById(@PathVariable(required = true) Long id) {
		Optional<Citta> find = cittaService.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/citta")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Citta> save(@RequestBody Citta citta) {
		Citta save = cittaService.save(citta);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/citta/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Citta> update(@PathVariable Long id, @RequestBody Citta citta) {
		Citta save = cittaService.update(id, citta);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/citta/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		cittaService.delete(id);
		return new ResponseEntity<>("Element deleted", HttpStatus.OK);

	}

}
