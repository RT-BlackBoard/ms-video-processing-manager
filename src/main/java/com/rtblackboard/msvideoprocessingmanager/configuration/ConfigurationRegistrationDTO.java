package com.rtblackboard.msvideoprocessingmanager.configuration;

import jakarta.validation.constraints.NotBlank;


//TODO  Adicionar  List<Filter> filters no record
public record ConfigurationRegistrationDTO (
        @NotBlank
        String inputType,
        @NotBlank
        String command) {
}
