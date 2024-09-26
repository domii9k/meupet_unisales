package com.meupet.api.Controller;

import com.meupet.api.DTO.VacinasDTO;
import com.meupet.api.Service.VacinasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacinas")
public class VacinasController {

    @Autowired
    private VacinasService service;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<VacinasDTO> listar() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public VacinasDTO findVacinas(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("nova-vacina")
    @ResponseStatus(code = HttpStatus.CREATED)
    public VacinasDTO salvaVacinas(@RequestBody VacinasDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public VacinasDTO atualiza(@PathVariable Long id, @RequestBody VacinasDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.delete(id);
    }
}
