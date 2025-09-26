package br.com.corporate.corporate_maintenance_system.repository;

import br.com.corporate.corporate_maintenance_system.model.MaintenanceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceOrderRepository extends JpaRepository<MaintenanceOrder, Long> {
}