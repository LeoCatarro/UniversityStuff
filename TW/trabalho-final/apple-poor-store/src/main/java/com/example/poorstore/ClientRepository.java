package com.example.poorstore;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

	List<Client> findByFirstName(String firstName);

	Client findById(long id);

	Client findOneByUsername(String username);
}
