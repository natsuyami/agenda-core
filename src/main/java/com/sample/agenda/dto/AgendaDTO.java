package com.sample.agenda.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AgendaDTO {

    private Long id;

    private String name;

    private List<AgendaItemDTO> agendaItemDTOList = new ArrayList<>();

    public AgendaDTO() {

    }

    public AgendaDTO(String name, List<AgendaItemDTO> agendaItemDTOList) {
        this.name = name;
        this.agendaItemDTOList = agendaItemDTOList;
    }

}
