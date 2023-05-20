package it.epicode.be.prenotazioni.common.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.epicode.be.prenotazioni.model.Citta;
import it.epicode.be.prenotazioni.model.Edificio;
import it.epicode.be.prenotazioni.model.Postazione;
import it.epicode.be.prenotazioni.model.Role;
import it.epicode.be.prenotazioni.model.RoleType;
import it.epicode.be.prenotazioni.model.TipoPostazione;
import it.epicode.be.prenotazioni.model.User;
import it.epicode.be.prenotazioni.repository.CittaRepository;
import it.epicode.be.prenotazioni.repository.EdificioRepository;
import it.epicode.be.prenotazioni.repository.PostazioneRepository;
import it.epicode.be.prenotazioni.repository.RoleRepository;
import it.epicode.be.prenotazioni.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppStartupRunner implements CommandLineRunner {

	@Autowired
	CittaRepository cittaRepository;

	@Autowired
	EdificioRepository edificioRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PostazioneRepository postazioneRepository;

	@Override
	public void run(String... args) throws Exception {

		if (cittaRepository.count() == 0) {

			Citta initCitta = initCitta();
			List<Edificio> edificioList = initEdificio(initCitta);
			List<Postazione> postazioneList = initPostazione(edificioList);
			List<Role> role = initRole();
			User user = initUser(role);
			
			

		}

	}
	
	private List<Role> initRole() {
		List<Role> result = new ArrayList<Role>();
		Role role = new Role();
		role.setRoleType(RoleType.ROLE_ADMIN);
		roleRepository.save(role);
		result.add(role);
		log.info("Saved Role: {}", role.getRoleType());

		
		role = new Role();
		role.setRoleType(RoleType.ROLE_USER);
		roleRepository.save(role);
		result.add(role);

		log.info("Saved Role: {}", role.getRoleType());

		return result;
	}

	private List<Postazione> initPostazione(List<Edificio> edificioList) {
		List<Postazione> result = new ArrayList<Postazione>();
		
		for (Edificio ed : edificioList) {
			
			for (int i=1;i<4;i++) {
				Postazione post = new Postazione();
				post.setCodice("P_"+i+"_"+ed.getNome());
				post.setDescrizione("Postazione " + post.getCodice());
				post.setEdificio(ed);
				post.setNumeroMassimoOccupanti(i);
				post.setTipo(TipoPostazione.PRIVATO);
				postazioneRepository.save(post);
				log.info("Saved Postazione: {}", post.getCodice());
				result.add(post);

			}
			
			
		}
		
		return result;
	}

	private User initUser(List<Role> roles) {
		User user = new User();
		user.setEmail("user@email.em");
		user.setNome("Mario Rossi");
		user.setUsername("m.rossi");
		user.setPassword("test");
		user.setRoles(new HashSet<Role>(roles));
		userRepository.save(user);
		
		
		
		log.info("Saved User: {}", user.getNome());

		return user;
	}

	private List<Edificio> initEdificio(Citta initCitta) {
		List<Edificio> lista = new ArrayList<Edificio>();

		Edificio edificio = new Edificio();
		edificio.setCitta(initCitta);
		edificio.setIndirizzo("Via Nazionale, 6");
		edificio.setNome("Sede Centrale - Roma");
		edificioRepository.save(edificio);
		lista.add(edificio);
		log.info("Saved Edificio: {}", edificio.getNome());

		edificio = new Edificio();
		edificio.setCitta(initCitta);
		edificio.setIndirizzo("Via Ponzio, 1");
		edificio.setNome("Sede Distaccata - Roma");
		edificioRepository.save(edificio);
		lista.add(edificio);
		log.info("Saved Edificio: {}", edificio.getNome());

		return lista;

	}

	private Citta initCitta() {
		Citta citta = new Citta();
		citta.setNome("Roma");
		cittaRepository.save(citta);
		log.info("Saved Citta: {}", citta.getNome());
		return citta;

	}
}