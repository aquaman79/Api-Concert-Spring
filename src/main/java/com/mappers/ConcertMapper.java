package com.mappers;

import com.dtos.Concertdto;
import com.entities.Concert;
import com.entities.Salle;
import com.entities.Soiree;
import com.repositories.SalleRepository;
import com.repositories.SoireeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;


@Component

public class ConcertMapper  {
    @Autowired
    private SalleMapper salleMapper;
    @Autowired
    private SoireeMapper soireeMapper;

    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private SoireeRepository soireeRepository;
    public Concert ConcertDtoToEntity(Concertdto concertdtoDto){
        Concert concertEntit = new Concert();
        concertEntit.setDate(concertdtoDto.getDate());
        concertEntit.setDuree(concertdtoDto.getDuree());
        concertEntit.setPrix(concertdtoDto.getPrix());
        concertEntit.setId(concertdtoDto.getId());
        Salle salle = this.salleRepository.findById(concertdtoDto.getSalle().getIdSalle()).orElseThrow(() -> new EntityNotFoundException("salle not found"));
        Soiree soiree = this.soireeRepository.findById(concertdtoDto.getSoiree().getIdSoiree()).orElseThrow(()->new EntityNotFoundException("soiree n'existe pas"));
        concertEntit.setSalle(salle);
        concertEntit.setSoiree(soiree);
        salle.getConcerts().add(concertEntit);
        soiree.getConcerts().add(concertEntit);
        //est ce que c'est normale que l'argument il prend que l'id est les autres sont null ,
       // du coup je suis besoin de passer par trouver la salle by findbyid
        return concertEntit;
    }

    public Concertdto concertEntitytoDTO(Concert concert){
        Concertdto concertdto = new Concertdto();
        concertdto.setPrix(concert.getPrix());
        concertdto.setDuree(concert.getDuree());
        concertdto.setDate(concert.getDate());
        concertdto.setId(concert.getId());
        //System.out.println("je suis dto "+ concert.getSoiree().getNomSoiree());
        Salle salle = this.salleRepository.findById(concert.getSalle().getIdSalle()).orElseThrow(() -> new EntityNotFoundException("salle not found"));
        Soiree soiree = this.soireeRepository.findById(concert.getSoiree().getIdSoiree()).orElseThrow(()->new EntityNotFoundException("soiree n'existe pas"));
        concertdto.setSoiree(this.soireeMapper.soireeEntityToDto(soiree));
        concertdto.setSalle(this.salleMapper.salleEntityToDto(salle));
        //est ce que c'est la place ideale pour stocker les concerts ?
        salle.getConcerts().add(concert);
        soiree.getConcerts().add(concert);
        return concertdto;
    }

}
