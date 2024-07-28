package com.rkm.beprepared.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="provincias")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designation;

    @OneToMany(mappedBy = "province") /*uma provincia tem muitos distritos, ao carregar uma provincia posso ter todos distritos*/
    private List<City> cities;
}
