/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.sample.agenda.repository.dummy;

import com.sample.agenda.model.Agenda;
import com.sample.agenda.model.AgendaItem;
import com.sample.agenda.repository.AgendaItemRepository;
import com.sample.agenda.repository.AgendaRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author ryangabriel.bunquin
 * @version $Id: SampleData.java, v 0.1 2024-01-25 2:58 PM Ryan Gabriel Bunquin Exp $$
 */
@Component
public class SampleData implements ApplicationRunner {

    private final AgendaRepository agendaRepository;

    private final AgendaItemRepository agendaItemRepository;

    public SampleData(AgendaRepository agendaRepository, AgendaItemRepository agendaItemRepository) {
        this.agendaRepository = agendaRepository;
        this.agendaItemRepository = agendaItemRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        // Save dummy data to the database
        createAgendaWithItem(1);
        createAgendaWithItem(2);
        createAgendaWithItem(3);
        createAgendaWithItem(4);
        createAgendaWithItem(5);
    }

    private void createAgendaWithItem(int count) {
        Agenda agenda = new Agenda("Agenda " + count);
        AgendaItem item = new AgendaItem(1, "Welcome", "content-"+count, "obj-"+count,
                15l, false, agendaRepository.save(agenda));

        agendaItemRepository.save(item);
    }
}