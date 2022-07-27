package br.com.teste.carapi.service;

import br.com.teste.carapi.entity.Log;
import br.com.teste.carapi.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LogService {

    private final LogRepository logRepository;

    public void saveLog(String car_id){
        logRepository.save(new Log(car_id));
    }

}
