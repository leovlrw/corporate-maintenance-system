package br.com.corporate.corporate_maintenance_system.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaintenanceOrder {

    public enum Urgency {
        BAIXA("Baixa"), MEDIA("Média"), ALTA("Alta");
        private final String displayName;
        Urgency(String displayName) { this.displayName = displayName; }
        public String getDisplayName() { return displayName; }
    }

    public enum MaintenanceType {
        PREVENTIVA("Preventiva"), CORRETIVA("Corretiva"), PREDITIVA("Preditiva");
        private final String displayName;
        MaintenanceType(String displayName) { this.displayName = displayName; }
        public String getDisplayName() { return displayName; }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String applicantName;

    @Column(length = 500, nullable = false)
    private String issueDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaintenanceType maintenanceType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Urgency urgency;

    @Column(nullable = false)
    private LocalDateTime openingDate = LocalDateTime.now();

    @Column
    private LocalDateTime completionDate;
}