package com.sample.agenda.repository;

import com.sample.agenda.model.Agenda;
import com.sample.agenda.model.AgendaItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaItemRepository extends CrudRepository<AgendaItem, Long> {

    void deleteByAgenda(Agenda agenda);

    List<AgendaItem> findByAgenda(Agenda agenda);

}
