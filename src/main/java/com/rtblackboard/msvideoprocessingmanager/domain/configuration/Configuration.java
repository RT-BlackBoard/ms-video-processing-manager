package com.rtblackboard.msvideoprocessingmanager.domain.configuration;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "configuration")
@Entity(name = "Configuration")
public class Configuration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String inputType;

    private String command;

    private String status;
    public Configuration(ConfigurationRegistrationDTO configurationRegistrationDTO) {
        this.status = "ACTIVE";
        this.command = configurationRegistrationDTO.command();
        this.inputType = configurationRegistrationDTO.inputType();
    }

    public void updateById(ConfigurationUpdateDTO configurationUpdateDTO) {

        if(configurationUpdateDTO.command()!=null){
            this.command = configurationUpdateDTO.command();
        }

        if(configurationUpdateDTO.inputType()!=null){
            this.inputType = configurationUpdateDTO.inputType();
        }

    }

    public void detete() {
        this.status = "DELETED";
    }
}
