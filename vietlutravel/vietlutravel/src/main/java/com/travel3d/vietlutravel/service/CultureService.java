package com.travel3d.vietlutravel.service;

import com.travel3d.vietlutravel.model.Culture;
import com.travel3d.vietlutravel.repository.CultureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CultureService {

    private final CultureRepository cultureRepository;

    public CultureService(CultureRepository cultureRepository) {
        this.cultureRepository = cultureRepository;
    }

    public List<Culture> getAllCultures() {
        return cultureRepository.findAllByOrderByIdAsc();
    }

    public Culture getCultureById(int id) {
        Optional<Culture> opt = cultureRepository.findById(id);
        return opt.orElse(null);
    }
}
