package com.rtblackboard.msvideoprocessingmanager.controller;

import com.rtblackboard.msvideoprocessingmanager.domain.configuration.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/api/v1/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationRepository repository;
    @PostMapping
    public ResponseEntity registerConfiguration(@RequestBody @Valid ConfigurationRegistrationDTO configurationRegistrationDTO, UriComponentsBuilder uriBuilder){

        var configuration = new Configuration(configurationRegistrationDTO);
        repository.save(configuration);
        var uri = uriBuilder.path("/api/v1/configuration/{id}").buildAndExpand(configuration.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataConfigurationDTO(configuration));

    }

    @GetMapping
    public ResponseEntity<Page<DataConfigurationDTO>> listAll(@PageableDefault(size = 30,page = 0,sort = "inputType") Pageable page){
        //Caso não estejamos usando paginação e o retorno do método for uma lista de configuracao usamos o código comentado.
        //return  repository.findAll(page).stream().map(DataConfigurationDTO::new).toList();
        //Find all busca sem filtro
        //return  repository.findAll(page).map(DataConfigurationDTO::new);
        //
        var returnPage =  repository.findAllByStatusEquals(page,"ACTIVE").map(DataConfigurationDTO::new);
        return ResponseEntity.ok(returnPage);
    }




    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid ConfigurationUpdateDTO configurationUpdateDTO){

        var config = repository.getReferenceById(configurationUpdateDTO.id());
        config.updateById(configurationUpdateDTO);


        return ResponseEntity.ok(new DataConfigurationDTO(config));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){

        //repository.deleteById(id);
        var config = repository.getReferenceById(id);
        config.detete();

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    @Transactional
    public DataConfigurationDTO getById(@PathVariable Long id){

        //repository.deleteById(id);
        var config = repository.getReferenceById(id);
        return new DataConfigurationDTO(config);



    }

}
