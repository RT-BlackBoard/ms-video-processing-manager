package com.rtblackboard.msvideoprocessingmanager.configuration;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    Page<Configuration>  findAllByStatusEquals(Pageable page, String status);
}
