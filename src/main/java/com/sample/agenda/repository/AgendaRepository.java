package com.sample.agenda.repository;

import com.sample.agenda.model.Agenda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends PagingAndSortingRepository<Agenda, Long>, CrudRepository<Agenda, Long> {

    List<Agenda> findAll();
}
