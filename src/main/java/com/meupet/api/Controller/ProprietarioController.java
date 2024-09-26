package com.meupet.api.Controller;

import com.meupet.api.DTO.ProprietarioDTO;
import com.meupet.api.Service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    @Autowired
    private ProprietarioService service;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ProprietarioDTO> listar() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ProprietarioDTO findProprietario(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/novo-proprietario")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProprietarioDTO salvaProprietario(@RequestBody ProprietarioDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ProprietarioDTO atualiza(@PathVariable Long id, @RequestBody ProprietarioDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.delete(id);
    }
}
