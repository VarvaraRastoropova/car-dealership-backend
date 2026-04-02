package ru.rastoropova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import ru.rastoropova.persistence_layer.entities.BodyDetailEntity;
import ru.rastoropova.persistence_layer.repositories.BodyDetailJpaRepository;
import ru.rastoropova.service_layer.car_characteristics.BodyType;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class BodyDetailJpaRepositoryTest {
    private static final DockerImageName ANON_IMAGE =  DockerImageName.parse("registry.gitlab.com/dalibo/postgresql_anonymizer:stable").asCompatibleSubstituteFor("postgres");

    @Container
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>(ANON_IMAGE)
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
    }

    @Autowired
    private BodyDetailJpaRepository bodyDetailJpaRepository;

    @Test
    void shouldSave() {
        BodyDetailEntity entity = new BodyDetailEntity();
        entity.setName("Name");
        entity.setPrice(1000);
        entity.setColor("Color");
        entity.setType(BodyType.SEDAN);

        bodyDetailJpaRepository.save(entity);

        assertNotNull(entity.getId());
    }

    @Test
    void shouldFind() {
        BodyDetailEntity entity = new BodyDetailEntity();
        entity.setName("Name");
        entity.setPrice(1000);
        entity.setColor("Color");
        entity.setType(BodyType.SEDAN);
        bodyDetailJpaRepository.save(entity);

        BodyDetailEntity findEntity = bodyDetailJpaRepository.findById(entity.getId()).orElse(null);

        assertNotNull(findEntity);
    }

    @Test
    void shouldFindAll() {
        BodyDetailEntity entity = new BodyDetailEntity();
        entity.setName("Name");
        entity.setPrice(1000);
        entity.setColor("Color");
        entity.setType(BodyType.SEDAN);
        bodyDetailJpaRepository.save(entity);

        List<BodyDetailEntity> entities = bodyDetailJpaRepository.findAll();

        assertFalse(entities.isEmpty());
    }

    @Test
    void shouldDelete() {
        BodyDetailEntity entity = new BodyDetailEntity();
        entity.setName("Name");
        entity.setPrice(1000);
        entity.setColor("Color");
        entity.setType(BodyType.SEDAN);
        bodyDetailJpaRepository.save(entity);

        bodyDetailJpaRepository.deleteById(entity.getId());
        BodyDetailEntity findEntity = bodyDetailJpaRepository.findById(entity.getId()).orElse(null);

        assertNull(findEntity);
    }
}
