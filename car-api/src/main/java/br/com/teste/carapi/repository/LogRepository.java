package br.com.teste.carapi.repository;

import br.com.teste.carapi.entity.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {

}