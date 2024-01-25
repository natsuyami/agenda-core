/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.sample.agenda.service;

import com.sample.agenda.dto.AgendaDTO;
import com.sample.agenda.dto.AgendaItemDTO;
import com.sample.agenda.model.Agenda;
import com.sample.agenda.model.AgendaItem;
import com.sample.agenda.repository.AgendaRepository;
import com.sample.agenda.validation.AgendaValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author ryangabriel.bunquin
 * @version $Id: AgendaManagement.java, v 0.1 2024-01-23 8:08 PM Ryan Gabriel Bunquin Exp $$
 */
@Service
public class AgendaManagement {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private AgendaItemManagement agendaItemManagement;

    public List<AgendaDTO> get() {
        List<Agenda> agendas = agendaRepository.findAll();
        List<AgendaDTO> agendaDTOList = new ArrayList<>();

        for (Agenda agenda : agendas) {
            List<AgendaItem> agendaItemList = agendaItemManagement.getAll(agenda);

            AgendaDTO agendaDTO = new AgendaDTO();
            BeanUtils.copyProperties(agenda, agendaDTO);

            List<AgendaItemDTO> agendaItemDTOList = agendaItemList.stream()
                    .map(a -> new AgendaItemDTO(a.getItemOrder(), a.getPhase(), a.getContent(), a.getObjectives(),
                            a.getDuration(), a.isCreditable())).collect(Collectors.toList());
            agendaDTO.setAgendaItemDTOList(agendaItemDTOList);

            agendaDTOList.add(agendaDTO);
        }

        return agendaDTOList;
    }

    public Agenda add(AgendaDTO agendaDTO) {
        AgendaValidation.getInstance().validate(agendaDTO);

        Agenda agenda = agendaRepository.save(new Agenda(agendaDTO.getName()));
        agendaItemManagement.add(agendaDTO, agenda);

        return agenda;
    }

    public Agenda modify(AgendaDTO agendaDTO) {
        AgendaValidation.getInstance().validate(agendaDTO);

        Agenda agenda = patchAgenda(agendaDTO);
        agendaItemManagement.modify(agendaDTO, agenda);

        return agenda;
    }

    @Transactional
    public Agenda delete(long id) {
        Agenda agenda = getAgendaExist(id);
        agendaItemManagement.delete(agenda);
        agendaRepository.delete(agenda);

        return agenda;
    }

    private Agenda getAgendaExist(long id) {
        Optional<Agenda> agenda = agendaRepository.findById(id);
        if (!agenda.isPresent()) {
            throw new NullPointerException("Cannot find Agenda, data does not exist!");
        }

        return agenda.get();
    }

    private Agenda patchAgenda(AgendaDTO agendaDTO) {
        Agenda agenda = getAgendaExist(agendaDTO.getId());

        if (null != agendaDTO.getName() && !agendaDTO.getName().isEmpty()
                && !agendaDTO.getName().equalsIgnoreCase(agenda.getName())) {
            agenda.setName(agendaDTO.getName());
            agendaRepository.save(agenda);
        }

        return agenda;
    }
}