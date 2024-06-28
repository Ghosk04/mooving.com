package com.projetReseau.model.vehicule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @PrimaryKey
    private String code;

    private String name;
    private String description = null;

}
