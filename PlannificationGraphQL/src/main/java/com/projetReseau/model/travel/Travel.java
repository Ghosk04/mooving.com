package com.projetReseau.model.travel;

import com.projetReseau.model.States;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Travel {
    @PrimaryKey
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID id = UUID.randomUUID();
    private UUID agencyId = null;
    private UUID itineraryId = null;

    private UUID vehicleID;
    private UUID driverID = null;
    private short state = States.TravelState.NOT_PUBLISHED.value();

    private UUID departureCityId;
    private String departureQuarter;

    private UUID destinationCityId;
    private String destinationQuarter;

    private int duration;
    double totalCost = 0.;
    private LocalDateTime startDate;

    private int tollPrice;
    private int ticketPrice;

    @CassandraType(type = CassandraType.Name.LIST, typeArguments = CassandraType.Name.TEXT)
    private List<UUID> stopPoints = new ArrayList<>();
}
