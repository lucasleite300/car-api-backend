package br.com.teste.carapi.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car {

    String _id;

    String title;

    String brand;

    String price;

    String age;

}
