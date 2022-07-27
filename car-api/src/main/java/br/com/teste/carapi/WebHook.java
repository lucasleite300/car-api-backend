package br.com.teste.carapi;

import br.com.teste.carapi.controller.CarController;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@EnableScheduling
public class WebHook {

    Integer contPast;

    @Scheduled(fixedDelay = 750, initialDelay = 60)
    public void WebHook() {

        Integer cont = CarController.cont;

        if (cont != null) {
            Integer contNew = cont;
            if (contNew > contPast){
                System.out.println("Carro novo Adicionado!");
            }
            return;
        }
    }

    @Scheduled(fixedDelay = 3000, initialDelay = 60)
    public void contPast() {
        Integer cont = CarController.cont;
        if (cont != null ) {
            contPast = cont;
        }else {
            contPast = 0;
        }
    }
}
