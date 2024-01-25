package com.sample.agenda.dto;

import lombok.Data;

@Data
public class AgendaItemDTO {

    private int itemOrder;

    private String phase;

    private String content;

    private String objectives;

    private Long duration;

    private boolean creditable;

    public AgendaItemDTO() {

    }

    public AgendaItemDTO(int itemOrder, String phase, String content, String objectives, Long duration, boolean creditable) {
        this.itemOrder = itemOrder;
        this.phase = phase;
        this.content = content;
        this.objectives = objectives;
        this.duration = duration;
        this.creditable = creditable;
    }

}
