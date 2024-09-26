package com.meupet.api.Controller;

import com.meupet.api.DTO.UsuarioDTO;
import com.meupet.api.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<UsuarioDTO> listar() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UsuarioDTO findUsuario(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UsuarioDTO atualiza(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.delete(id);
    }
}
