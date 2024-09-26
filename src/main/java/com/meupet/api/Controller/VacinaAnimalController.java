package com.meupet.api.Controller;

import com.meupet.api.DTO.VacinaAnimalDTO;
import com.meupet.api.Service.VacinaAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacinacoes")
public class VacinaAnimalController {

    @Autowired
    private VacinaAnimalService service;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<VacinaAnimalDTO> listar() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public VacinaAnimalDTO findVacinaAnimal(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("nova-vacinacao")
    @ResponseStatus(code = HttpStatus.CREATED)
    public VacinaAnimalDTO salvaVacinaAnimal(@RequestBody VacinaAnimalDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public VacinaAnimalDTO atualiza(@PathVariable Long id, @RequestBody VacinaAnimalDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.delete(id);
    }
}
