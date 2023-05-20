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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.prenotazioni.model.Citta;
import it.epicode.be.prenotazioni.model.Postazione;
import it.epicode.be.prenotazioni.model.TipoPostazione;
import it.epicode.be.prenotazioni.service.CittaService;
import it.epicode.be.prenotazioni.service.PostazioneService;

@RestController
@RequestMapping("/api")
public class PostazioneController {

	@Autowired
	private PostazioneService postazioneService;

	@Autowired
	private CittaService cittaService;

	@GetMapping(path = "/postazione")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Page<Postazione>> findAll(Pageable pageable) {
		Page<Postazione> findAll = postazioneService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/postazione/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Postazione> findById(@PathVariable(required = true) Long id) {
		Optional<Postazione> find = postazioneService.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/postazione")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Postazione> save(@RequestBody Postazione postazione) {
		Postazione save = postazioneService.save(postazione);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/postazione/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Postazione> update(@PathVariable Long id, @RequestBody Postazione postazione) {
		Postazione save = postazioneService.update(id, postazione);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/postazione/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		postazioneService.delete(id);
		return new ResponseEntity<>("Element deleted", HttpStatus.OK);

	}

	@GetMapping(path = "/postazione/libere")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> findLibereByCitta(@RequestParam Long idCitta, @RequestParam String tipoPostazione,
			@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataPrenotazione, Pageable pageable) {
		Optional<Citta> citta = cittaService.findById(idCitta);

		TipoPostazione tipo = TipoPostazione.valueOf(tipoPostazione);
		if (citta.isEmpty()) {
			return new ResponseEntity<String>("Città not found", HttpStatus.NOT_FOUND);
		}

		Page<Postazione> findLibereByCitta = postazioneService.findLibereByCitta(citta.get(), tipo, dataPrenotazione,
				pageable);
		return new ResponseEntity<>(findLibereByCitta, HttpStatus.OK);

	}

}
