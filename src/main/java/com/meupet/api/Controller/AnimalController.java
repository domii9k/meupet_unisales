package com.meupet.api.Controller;

import com.meupet.api.DTO.AnimalDTO;
import com.meupet.api.Service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<AnimalDTO> listar(){return service.listAll();}

    @RequestMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AnimalDTO findAnimal(@PathVariable Long id){return  service.findById(id);}

    @PostMapping("/novo-animal")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AnimalDTO salvaAnimal(@RequestBody AnimalDTO dto){return service.create(dto);}

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AnimalDTO atualiza(@PathVariable Long id, @RequestBody AnimalDTO dto){return service.update(id, dto);}

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){service.delete(id);}
}
