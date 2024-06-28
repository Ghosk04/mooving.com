package com.projetReseau.configuration;

import com.projetReseau.resolver.*;
import graphql.GraphQL;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.kickstart.tools.SchemaParser;
import graphql.kickstart.tools.SchemaParserBuilder;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    private final CityResolver cityResolver;
    private final DriverResolver driverResolver;
    private final PlanningHistoryResolver planningHistoryResolver;
    private final TravelResolver travelResolver;
    private final TravelHistoryResolver travelHistoryResolver;
    private final VehicleResolver vehicleResolver;
    private final PlanningResolver planningResolver;

    @Autowired
    public GraphQLConfig(
            CityResolver cityResolver,
            DriverResolver driverResolver,
            PlanningHistoryResolver planningHistoryResolver,
            TravelResolver travelResolver,
            TravelHistoryResolver travelHistoryResolver,
            VehicleResolver vehicleResolver,
            PlanningResolver planningResolver) {

        this.cityResolver = cityResolver;
        this.driverResolver = driverResolver;
        this.planningHistoryResolver = planningHistoryResolver;
        this.travelResolver = travelResolver;
        this.travelHistoryResolver = travelHistoryResolver;
        this.vehicleResolver = vehicleResolver;
        this.planningResolver = planningResolver;
    }

    @Bean
    public GraphQLScalarType date() {
        return ExtendedScalars.Date;
    }

    @Bean
    public GraphQLScalarType dateTime() {
        return ExtendedScalars.DateTime;
    }

    @Bean
    public GraphQL graphQL(GraphQLSchema graphQLSchema) {
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    @Bean
    public GraphQLSchema graphQLSchema() {
        SchemaParserBuilder schemaParserBuilder = SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(
                        (GraphQLResolver<?>) cityResolver,
                        (GraphQLResolver<?>) driverResolver,
                        (GraphQLResolver<?>) planningResolver,
                        (GraphQLResolver<?>)planningHistoryResolver,
                        (GraphQLResolver<?>) travelHistoryResolver,
                        (GraphQLResolver<?>) travelResolver,
                        (GraphQLResolver<?>) vehicleResolver);

        return schemaParserBuilder.build().makeExecutableSchema();
    }
}
