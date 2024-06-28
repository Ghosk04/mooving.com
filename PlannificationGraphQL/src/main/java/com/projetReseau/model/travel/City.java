package com.projetReseau.model.travel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;


@Data
@Table
@NoArgsConstructor
@AllArgsConstructor

public class City {
    @PrimaryKey
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID id = UUID.randomUUID();
    private String name;

    @CassandraType(type = CassandraType.Name.LIST, typeArguments = CassandraType.Name.TEXT)
    List<String> quarters;
}

