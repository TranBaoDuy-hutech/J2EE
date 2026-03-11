package com.travel3d.vietlutravel.repository;

import com.travel3d.vietlutravel.model.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CultureRepository extends JpaRepository<Culture, Integer> {

    List<Culture> findAllByOrderByIdAsc();
}
