/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.sample.agenda.service;

import com.sample.agenda.dto.AgendaDTO;
import com.sample.agenda.dto.AgendaItemDTO;
import com.sample.agenda.model.Agenda;
import com.sample.agenda.model.AgendaItem;
import com.sample.agenda.repository.AgendaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryangabriel.bunquin
 * @version $Id: AgendaItemManagement.java, v 0.1 2024-01-23 10:00 PM Ryan Gabriel Bunquin Exp $$
 */
@Service
public class AgendaItemManagement {

    @Autowired
    private AgendaItemRepository agendaItemRepository;

    public List<AgendaItem> getAll(Agenda agenda) {
        return agendaItemRepository.findByAgenda(agenda);
    }

    public List<AgendaItem> add(AgendaDTO agendaDTO, Agenda agenda) {
        List<AgendaItem> agendaItemList = new ArrayList<>();

        for (AgendaItemDTO agendaItemDTO: agendaDTO.getAgendaItemDTOList()) {
            AgendaItem agendaItem = new AgendaItem(
                    agendaItemDTO.getItemOrder(),
                    agendaItemDTO.getPhase(),
                    agendaItemDTO.getContent(),
                    agendaItemDTO.getObjectives(),
                    agendaItemDTO.getDuration(),
                    agendaItemDTO.isCreditable(),
                    agenda);

            agendaItemList.add(agendaItemRepository.save(agendaItem));
        }

        return agendaItemList;
    }

    public List<AgendaItem> modify(AgendaDTO agendaDTO, Agenda agenda) {
        List<AgendaItem> agendaItemList = new ArrayList<>();

        for (AgendaItemDTO agendaItemDTO: agendaDTO.getAgendaItemDTOList()) {
            AgendaItem agendaItem = new AgendaItem();
            patchAgendaItem(agendaItemDTO, agenda, agendaItem);
            agendaItemList.add(agendaItemRepository.save(agendaItem));
        }

        return agendaItemList;
    }

    public Agenda delete(Agenda agenda) {
        agendaItemRepository.deleteByAgenda(agenda);

        return agenda;
    }

    public int patchAgendaItem(AgendaItemDTO agendaItemDTO, Agenda agenda, AgendaItem agendaItem) {
        agendaItem.setCreditable(agendaItemDTO.isCreditable());
        agendaItem.setAgenda(agenda);

        int updateCount = 0;
        if (agendaItemDTO.getItemOrder() > 0) {
            agendaItem.setItemOrder(agendaItemDTO.getItemOrder());
            updateCount++;
        }

        if (null != agendaItemDTO.getContent() || !agendaItemDTO.getContent().isEmpty()) {
            agendaItem.setContent(agendaItemDTO.getContent());
            updateCount++;
        }

        if (agendaItemDTO.getDuration() > 0) {
            agendaItem.setDuration(agendaItemDTO.getDuration());
            updateCount++;
        }

        if (null != agendaItemDTO.getObjectives() || !agendaItemDTO.getObjectives().isEmpty()) {
            agendaItem.setObjectives(agendaItemDTO.getObjectives());
            updateCount++;
        }

        if (null != agendaItemDTO.getPhase() || !agendaItemDTO.getPhase().isEmpty()) {
            agendaItem.setPhase(agendaItemDTO.getPhase());
            updateCount++;
        }

        return updateCount;
    }
}