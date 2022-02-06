package com.bilgeadam.odevbolumapp.controller;

import com.bilgeadam.odevbolumapp.entity.Bolum;
import com.bilgeadam.odevbolumapp.exception.BolumNotFoundException;
import com.bilgeadam.odevbolumapp.repository.BolumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class BolumController {

    @Autowired
    private BolumRepository bolumRepository;

    @GetMapping("/bolum/{id}")
    public Bolum getBolum(@PathVariable("id")long id) {
        Bolum bolum = null;
        Optional<Bolum> bolumDB = bolumRepository.findById(id);

        if (bolumDB.isPresent())
        {
            bolum = bolumDB.get();
            return bolum;
        } else
        {
            throw new BolumNotFoundException(id + "nolu Bölüm Bulunamadı");
        }

    }

        @GetMapping("/bolum")
        public List<Bolum> getTumBolumler ()
        {
            return bolumRepository.findAll();
        }
        @GetMapping("/bolumad/{id}")
        public String getBolumAd ( @PathVariable("id") long id)
        {

            Bolum bolum = getBolum(id);
            return bolum.getAd();

            //return getBolum(id).getAd();

        }
    private String getSehir(long id)
    {

        String sehirURL = "http://localhost:8240";

        RestTemplate restTemplate = new RestTemplate();

        String sehirAd = restTemplate.getForObject(sehirURL + "/sehirad/" + id, String.class);

        return sehirAd;
    }

    }
