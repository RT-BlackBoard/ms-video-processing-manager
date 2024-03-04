package com.rtblackboard.msvideoprocessingmanager.configuration;


import jakarta.validation.constraints.NotNull;


public record ConfigurationUpdateDTO(
                                    @NotNull
                                    Long id,
                                     String inputType,

                                     String command) {
}
