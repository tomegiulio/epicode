package it.epicode.be.prenotazioni.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.be.prenotazioni.model.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

}
