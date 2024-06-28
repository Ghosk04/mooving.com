package com.projetReseau.model.travel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor

public class TravelHistory {
    @PrimaryKey
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID id = UUID.randomUUID();
    private UUID driverID;
    private UUID vehicleID;

    private int duration;
    private UUID departureCityId;
    private UUID destinationCityId;
    private LocalDateTime startDate;

    private int tollPrice;
    private int ticketPrice;

    public TravelHistory(Travel travel) {
        this.id = travel.getId();
        this.driverID = travel.getDriverID();
        this.vehicleID = travel.getVehicleID();
        this.duration = travel.getDuration();
        this.departureCityId = travel.getDepartureCityId();
        this.destinationCityId = travel.getDestinationCityId();
        this.startDate = travel.getStartDate();
        this.tollPrice = travel.getTollPrice();
        this.ticketPrice = travel.getTicketPrice();
    }
}
