package com.spring.ai.service;


import com.spring.ai.repository.RoleRepository;
import com.spring.ai.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class SchemaService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public String getSchemaContext() {
        Metamodel metamodel = entityManager.getMetamodel();
        StringBuilder schema = new StringBuilder();

        // Get all entities
        for (EntityType<?> entityType : metamodel.getEntities()) {
            String tableName = entityType.getName();
            schema.append("Table: ").append(tableName).append("\n");

            // Get columns
            schema.append("Columns:\n");
            for (Attribute<?, ?> attribute : entityType.getAttributes()) {
                schema.append("- ")
                        .append(attribute.getName())
                        .append(" (")
                        .append(attribute.getJavaType().getSimpleName())
                        .append(")\n");
            }

            // Add sample data
            schema.append("Sample Data:\n");
            switch (tableName.toLowerCase()) {
                case "user":
                    schema.append(getUserSamples());
                    break;
                case "role":
                    schema.append(getRoleSamples());
                    break;
            }
            schema.append("\n\n");
        }

        return schema.toString();
    }

    private String getUserSamples() {
        return userRepository.findAll().stream()
                .limit(3)
                .map(u -> String.format(
                        "User[id=%d, name='%s', email='%s', created_at=%s]",
                        u.getId(), u.getUsername(), u.getEmail(), u.getCreatedAt()))
                .collect(Collectors.joining("\n"));
    }

    private String getRoleSamples() {
        return roleRepository.findAll().stream()
                .limit(3)
                .map(r -> String.format(
                        "Role[id=%d, name='%s']",
                        r.getId(), r.getName()))
                .collect(Collectors.joining("\n"));
    }
}