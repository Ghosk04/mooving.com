package com.projetReseau.model.driver;

import com.projetReseau.model.States;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;


@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @PrimaryKey
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID id = UUID.randomUUID();
    private short state = States.DriverState.READY.value();
    private String name;
    private String surname;
    private String residence;

    private short gearBox = States.GearBox.ALL.value();
    private String license;

    private int hourlyVol; // in hour
    private int hourlySalary;
    private int elapsedWorkingTime = 0; // in minutes
    private int lastTravelDuration = 0; // in minutes
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDateTime lastTravelEndDateTime = null;

    @CassandraType(type = CassandraType.Name.MAP, typeArguments = {CassandraType.Name.TEXT, CassandraType.Name.DOUBLE})
    private HashMap<String, Double> routeExperience = new HashMap<>();

    @CassandraType(type = CassandraType.Name.MAP, typeArguments = {CassandraType.Name.TEXT, CassandraType.Name.DOUBLE})
    private HashMap<String, Double> vehicleCategoryExperience = new HashMap<>();
}
