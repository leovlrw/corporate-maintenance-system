package br.com.corporate.corporate_maintenance_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class MaintenanceOrder {

    public enum Urgency {
        BAIXA("Baixa"),
        MEDIA("Média"),
        ALTA("Alta");

        private final String displayName;

        Urgency(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public enum MaintenanceType {
        PREVENTIVA("Preventiva"),
        CORRETIVA("Corretiva"),
        PREDITIVA("Preditiva");

        private final String displayName;

        MaintenanceType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do solicitante é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome do solicitante deve ter entre 3 e 100 caracteres.")
    @Column(length = 100, nullable = false)
    private String applicantName;

    @NotBlank(message = "A descrição do problema é obrigatória.")
    @Size(min = 10, max = 500, message = "A descrição do problema deve ter entre 10 e 500 caracteres.")
    @Column(length = 500, nullable = false)
    private String issueDescription;

    @NotNull(message = "O tipo de manutenção é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaintenanceType maintenanceType;

    @NotNull(message = "O nível de urgência é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Urgency urgency;

    @Column(nullable = false)
    private LocalDateTime openingDate = LocalDateTime.now();

    @Column(nullable = true)
    private LocalDateTime completionDate;
}