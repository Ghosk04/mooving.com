package com.projetReseau.model.vehicule;

import com.projetReseau.model.States;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Table
@NoArgsConstructor
@AllArgsConstructor

public class Vehicle {
    @PrimaryKey
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID id = UUID.randomUUID();
    private short state = States.VehicleState.READY.value();
    private int capacity;
    private String categoryCode;
    private String chassisNumber;
    private String registrationNumber;

    private short gearBox = States.GearBox.MANUAL.value();
    private int revisionTime;
    private int revisionPrice;
    private int consumptionPrice;
    private LocalDate firstCirculation = null;
    private LocalDateTime lastTravelEndDateTime = null;
}
