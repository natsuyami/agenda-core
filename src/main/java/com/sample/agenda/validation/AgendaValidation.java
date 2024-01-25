/**
 * Globe FinTech Innovations, Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package com.sample.agenda.validation;


import com.sample.agenda.dto.AgendaDTO;

/**
 * @author ryangabriel.bunquin
 * @version $Id: AgendaValidation.java, v 0.1 2024-01-24 8:58 AM Ryan Gabriel Bunquin Exp $$
 */
public class AgendaValidation {

    protected static AgendaValidation instance;

    public AgendaValidation() {}

    public static AgendaValidation getInstance() {
        if (instance == null) {
            instance = new AgendaValidation();
        }

        return instance;
    }

    public void validate(AgendaDTO agendaDTO) {
        checkEmptyAgenda(agendaDTO);
        checkAgendaName(agendaDTO.getName());
    }

    private void checkEmptyAgenda(AgendaDTO agendaDTO) {
        if (agendaDTO == null || agendaDTO.getAgendaItemDTOList().isEmpty()) {
            throw new NullPointerException("Request payload for adding agenda is null!");
        }
    }

    private void checkAgendaName(String agendaName) {
        if (null == agendaName || agendaName.isEmpty()) {
            throw new NullPointerException("Agenda name is required!");
        }
    }

}