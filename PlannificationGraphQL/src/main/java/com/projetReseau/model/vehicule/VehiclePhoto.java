package com.projetReseau.model.vehicule;

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

public class VehiclePhoto {
    @PrimaryKey
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID id = UUID.randomUUID();

    private String url;
    private UUID vehicleID;
    private String description;
    private LocalDateTime uploadDateTime = LocalDateTime.now();
}
