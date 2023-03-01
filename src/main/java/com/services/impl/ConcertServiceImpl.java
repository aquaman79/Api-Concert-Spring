package com.services.impl;

import com.dtos.Concertdto;
import com.entities.Concert;
import com.entities.Salle;
import com.entities.Soiree;
import com.mappers.ConcertMapper;
import com.repositories.ConcertRepository;
import com.repositories.SalleRepository;
import com.repositories.SoireeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service("ConcertService")

public class ConcertServiceImpl {

        @Autowired
       private  ConcertMapper concertMapper;
        @Autowired

    private SalleRepository  salleRepository;
        @Autowired
        private ConcertRepository concertRepository ;
        @Autowired

        private SoireeRepository soireeRepository;
        public Concertdto saveConcert(Concertdto concertdto) {
            Concert concert  = concertMapper.ConcertDtoToEntity(concertdto);
            concert = concertRepository.save(concert);
            return this.concertMapper.concertEntitytoDTO(concert);
        }

        public List<Concertdto> getAllConcert(){
            List<Concertdto> Concertdto = new ArrayList<>();
            List<Concert> concerts = concertRepository.findAll();
            concerts.forEach(concert -> {
                Concertdto.add(this.concertMapper.concertEntitytoDTO(concert));
            });
            return Concertdto;
        }

        public Concertdto getConcertById(Long Id) {
            Concert concert  = concertRepository.findById(Id).orElseThrow(() -> new EntityNotFoundException("concert not found"));
            return concertMapper.concertEntitytoDTO(concert);
        }

        public boolean deleteConcert(Long Id) {
            concertRepository.deleteById(Id);
            return true;
        }

        public Concertdto update(Concertdto concertdto,Long id ){
            Concert concert = this.concertRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("concert not found"));
            Salle salle  = this.salleRepository.findById(concertdto.getSalle().getIdSalle()).orElseThrow(() -> new EntityNotFoundException("salle not found"));
            Soiree soiree =  this.soireeRepository.findById(concertdto.getSoiree().getIdSoiree()).orElseThrow(() -> new EntityNotFoundException("soiree not found"));
            concert.setSalle(salle);
            concert.setSoiree(soiree);
            concert.setPrix(concertdto.getPrix());
            concert.setDate(concertdto.getDate());
            concert.setDuree(concertdto.getDuree());
            this.concertRepository.save(concert);
            return this.concertMapper.concertEntitytoDTO(concert);
        }


}
