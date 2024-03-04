package com.rtblackboard.msvideoprocessingmanager.controller;

import com.rtblackboard.msvideoprocessingmanager.configuration.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationRepository repository;
    @PostMapping
    public void registerConfiguration(@RequestBody @Valid ConfigurationRegistrationDTO configurationRegistrationDTO){
        repository.save(new Configuration(configurationRegistrationDTO));

    }

    @GetMapping
    public Page<DataConfigurationDTO> listAll(@PageableDefault(size = 30,page = 0,sort = "inputType") Pageable page){
        //Caso não estejamos usando paginação e o retorno do método for uma lista de configuracao usamos o código comentado.
        //return  repository.findAll(page).stream().map(DataConfigurationDTO::new).toList();
        //Find all busca sem filtro
        //return  repository.findAll(page).map(DataConfigurationDTO::new);
        //
        return  repository.findAllByStatusEquals(page,"ACTIVE").map(DataConfigurationDTO::new);

    }




    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid ConfigurationUpdateDTO configurationUpdateDTO){

        var config = repository.getReferenceById(configurationUpdateDTO.id());
        config.updateById(configurationUpdateDTO);



    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){

        //repository.deleteById(id);
        var config = repository.getReferenceById(id);
        config.detete();



    }

    @GetMapping("/{id}")
    @Transactional
    public DataConfigurationDTO getById(@PathVariable Long id){

        //repository.deleteById(id);
        var config = repository.getReferenceById(id);
        return new DataConfigurationDTO(config);



    }

}
