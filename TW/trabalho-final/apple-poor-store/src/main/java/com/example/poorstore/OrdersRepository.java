package com.example.poorstore;


import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long> {
	Orders findById(long id);
}
