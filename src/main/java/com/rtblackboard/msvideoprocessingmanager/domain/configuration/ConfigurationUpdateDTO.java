package com.rtblackboard.msvideoprocessingmanager.domain.configuration;


import jakarta.validation.constraints.NotNull;


public record ConfigurationUpdateDTO(
                                    @NotNull
                                    Long id,
                                     String inputType,

                                     String command) {
}
