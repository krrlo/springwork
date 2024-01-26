package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository <Customer, Long>{ 
	//CrudRepository는 CRUD(Create, Read, Update, Delete) 작업을 수행할 수 있는 기본적인 메서드들을 제공하는 인터페이스입니다.
	// Customer 엔티티와 그 엔티티의 기본 키인 Long 타입을 사용하여 CRUD 작업을 수행할 수 있습니다.

}
