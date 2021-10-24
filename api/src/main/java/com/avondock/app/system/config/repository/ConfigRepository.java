package com.avondock.app.system.config.repository;

import com.avondock.app.system.config.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, Long> {
}
