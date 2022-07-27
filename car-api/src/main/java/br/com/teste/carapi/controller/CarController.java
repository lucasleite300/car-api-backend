package br.com.teste.carapi.controller;

import br.com.teste.carapi.entity.Car;
import br.com.teste.carapi.entity.Log;
import br.com.teste.carapi.repository.LogRepository;
import br.com.teste.carapi.service.LogService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.*;

@Validated
@RestController
@Slf4j
@AllArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("api")
public class CarController {

    private final LogRepository logRepository;
    private final LogService logService;

    public Queue<Car> fila = new LinkedList<>();
    public static Integer cont;

    @ApiOperation(value = "Listar os carros")
    @GetMapping("/listCars")
    public List<Car> listCar() {
        String url = "http://api-test.bhut.com.br:3000/api/cars";
        RestTemplate restTemplate = new RestTemplate();

        Car[] cars = restTemplate.getForObject(url, Car[].class);

        return Arrays.asList(cars);
    }

    @ApiOperation(value = "Salva um novo carro")
    @PostMapping("/createCar")
    public ResponseEntity<ResponseEntity<Car>> createCar(@Valid @RequestBody Car car){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("title", car.getTitle());
        map.add("brand", car.getBrand());
        map.add("price", car.getPrice());
        map.add("age", car.getAge());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        String url = "http://api-test.bhut.com.br:3000/api/cars";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Car> response = restTemplate.postForEntity( url, request , Car.class );

        //Chamando o cadastro de log
        logService.saveLog(response.getBody().get_id());

        //Colocando a informação do carro cadastrado na fila
        fila.add(response.getBody());
        cont = fila.size();
        System.out.println(fila);
        System.out.println(cont);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Listar os logs")
    @GetMapping("/logs")
    public ResponseEntity<Iterable<Log>> logs() {
        return ResponseEntity.ok(logRepository.findAll());
    }

}
