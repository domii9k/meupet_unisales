package com.meupet.api.Controller;

import com.meupet.api.DTO.HistoricoPesoAlturaDTO;
import com.meupet.api.Service.HistoricoPesoAlturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico-peso-altura")
public class HistoricoPesoAlturaController {

    @Autowired
    private HistoricoPesoAlturaService service;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<HistoricoPesoAlturaDTO> listar() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public HistoricoPesoAlturaDTO findHistoricoPesoAltura(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("novo-historico")
    @ResponseStatus(code = HttpStatus.CREATED)
    public HistoricoPesoAlturaDTO salvaHistoricoPesoAltura(@RequestBody HistoricoPesoAlturaDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public HistoricoPesoAlturaDTO atualiza(@PathVariable Long id, @RequestBody HistoricoPesoAlturaDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.delete(id);
    }
}
