package com.projetreseau.intermediaire.Configuration;


import com.projetreseau.intermediaire.PositionResolver;
import graphql.GraphQL;
import graphql.kickstart.tools.SchemaParser;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {
    private final PositionResolver positionResolver;
    public GraphQLScalarType date() {
        return ExtendedScalars.Date;
    }

    @Autowired
    public GraphQLConfig(PositionResolver positionResolver)  {
        this.positionResolver = positionResolver;
    }


    @Bean
    public GraphQLScalarType dateTime() {
        return ExtendedScalars.DateTime;
    }


    public GraphQLScalarType time()  {
        return ExtendedScalars.Date;
    }

    @Bean
    public GraphQL graphQL(GraphQLSchema graphQLSchema) {
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    @Bean
    public GraphQLSchema graphQLSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(positionResolver)
                .build()
                .makeExecutableSchema();
    }
}


