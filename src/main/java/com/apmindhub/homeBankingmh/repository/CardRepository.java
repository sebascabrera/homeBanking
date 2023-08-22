package com.apmindhub.homeBankingmh.repository;

import com.apmindhub.homeBankingmh.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card,Long> {

    List<Card> findAllById(Iterable<Long> longs);
}
