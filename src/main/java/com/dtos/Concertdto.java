package com.dtos;

import com.entities.Salle;
import com.entities.Soiree;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class Concertdto {
    private Long id;
    private Date date;
    private int duree;
    private int prix ;
    private Salledto salle;
    private Soireedto soiree;
}
