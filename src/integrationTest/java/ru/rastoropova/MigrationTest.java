package ru.rastoropova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class MigrationTest {
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
    private JdbcTemplate jdbcTemplate;

    @Test
    void shouldExecuteAllMigrations() {
        Integer countMigrations = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM databasechangelog", Integer.class);

        assertEquals(25, countMigrations);
    }

    @Test
    void ShouldCreateUserTable() {
        Integer count =  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'users'", Integer.class);

        assertEquals(1, count);
    }

    @Test
    void ShouldCreateBodyDetailsTable() {
        Integer count =  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'body_details'", Integer.class);

        assertEquals(1, count);
    }

    @Test
    void ShouldCreateEngineDetailsTable() {
        Integer count =  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'engine_details'", Integer.class);

        assertEquals(1, count);
    }

    @Test
    void ShouldCreateGearboxDetailsTable() {
        Integer count =  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'gearbox_details'", Integer.class);

        assertEquals(1, count);
    }

    @Test
    void ShouldCreateInteriorDetailsTable() {
        Integer count =  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'interior_details'", Integer.class);

        assertEquals(1, count);
    }

    @Test
    void ShouldCreateSteeringWheelDetailsTable() {
        Integer count =  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'steering_wheel_details'", Integer.class);

        assertEquals(1, count);
    }

    @Test
    void ShouldCreateWheelsDetailsTable() {
        Integer count =  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'wheels_details'", Integer.class);

        assertEquals(1, count);
    }

    @Test
    void ShouldCreateCarModelsTable() {
        Integer count =  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'car_models'", Integer.class);

        assertEquals(1, count);
    }

    @Test
    void ShouldCreateCarsTable() {
        Integer count =  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'cars'", Integer.class);

        assertEquals(1, count);
    }

    @Test
    void ShouldCreateCarPurchaseOrderTable() {
        Integer count =  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'car_purchase_order'", Integer.class);

        assertEquals(1, count);
    }

    @Test
    void ShouldCreateCarWithConfigurationPurchaseOrderTable() {
        Integer count =  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'car_with_configuration_purchase_order'", Integer.class);

        assertEquals(1, count);
    }

    @Test
    void ShouldCreateTestDriveOrderTable() {
        Integer count =  jdbcTemplate.queryForObject("SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'test_drive_order'", Integer.class);

        assertEquals(1, count);
    }
}
