package com.projetReseau.model.planning;

import com.projetReseau.model.States;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class PlanningHistory {
    @PrimaryKey
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID id;

    private String name;
    @CassandraType(type = CassandraType.Name.SET, typeArguments = CassandraType.Name.UUID)
    private Set<UUID> plannedTravels = null;
    private LocalDate endDate;
    private LocalDate startDate;
    private short state = States.PlanningState.NOT_PUBLISHED.value();
    private LocalDateTime creationDate;

    public PlanningHistory(Planning planning) {
        this.id = planning.getId();
        this.name = planning.getName();
        this.plannedTravels = planning.getPlannedTravels();
        this.endDate = planning.getEndDate();
        this.startDate = planning.getStartDate();
        this.state = planning.getState();
        this.creationDate = LocalDateTime.now();
    }
}
