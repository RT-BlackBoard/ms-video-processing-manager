package com.rtblackboard.msvideoprocessingmanager.configuration;



public record DataConfigurationDTO(Long id, String inputType,String command,String status) {

    public DataConfigurationDTO (Configuration configuration){

        this(configuration.getId(),configuration.getInputType(),configuration.getCommand(),configuration.getStatus());

    }
}
