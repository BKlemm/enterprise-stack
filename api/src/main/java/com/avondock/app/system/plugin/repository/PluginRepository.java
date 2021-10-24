package com.avondock.app.system.plugin.repository;

import com.avondock.app.system.plugin.model.Plugin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PluginRepository extends JpaRepository<Plugin, Long> {
}
