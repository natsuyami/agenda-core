package com.sample.agenda.api;

import com.sample.agenda.constant.Response;
import com.sample.agenda.dto.AgendaDTO;
import com.sample.agenda.service.AgendaManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController
public class AgendaController {

    @Autowired
    private AgendaManagement agendaManagement;

    @RequestMapping(value = "/all", method = RequestMethod.GET,
            consumes = "application/json", produces = "application/json")
    public List<AgendaDTO> create() {
        try {
            return agendaManagement.get();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public String create(@RequestBody AgendaDTO agendaDTO) {
        try {
            agendaManagement.add(agendaDTO);

            return Response.SUCCESS.getErrorMsg();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.GENERAL_ERROR.getErrorMsg();
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PATCH,
            consumes = "application/json", produces = "application/json")
    public String edit(@RequestBody AgendaDTO agendaDTO) {
        try {
            agendaManagement.modify(agendaDTO);

            return Response.SUCCESS.getErrorMsg();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.GENERAL_ERROR.getErrorMsg();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE,
            consumes = "application/json", produces = "application/json")
    public String delete(@PathVariable Long id) {
        try {
            agendaManagement.delete(id);

            return Response.SUCCESS.getErrorMsg();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.GENERAL_ERROR.getErrorMsg();
        }
    }
}
