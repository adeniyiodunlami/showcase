package com.orion.app;

import com.dto.SampleDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleService extends JpaRepository<SampleDto, Integer> {}

