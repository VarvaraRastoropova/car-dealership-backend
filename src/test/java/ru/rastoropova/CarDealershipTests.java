package ru.rastoropova;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.rastoropova.integration_layer.mappers.CarMapper;
import ru.rastoropova.integration_layer.mappers.CarMapperImpl;
import ru.rastoropova.service_layer.car_characteristics.*;
import ru.rastoropova.service_layer.cars.Car;
import ru.rastoropova.service_layer.cars.CarFilter;
import ru.rastoropova.service_layer.cars.Configuration;
import ru.rastoropova.service_layer.details.*;
import ru.rastoropova.service_layer.orders.*;
import ru.rastoropova.service_layer.repositories.*;
import ru.rastoropova.service_layer.services.CarService;
import ru.rastoropova.service_layer.services.DetailService;
import ru.rastoropova.service_layer.services.OrderService;
import ru.rastoropova.service_layer.users.Role;
import ru.rastoropova.service_layer.users.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarDealershipTests {
    private CarMapper carMapper = new CarMapperImpl();

    private CarService carService;
    private CarRepository carRepository;
    private CarModelRepository carModelRepository;

    private DetailService detailService;
    private BodyDetailRepository bodyDetailRepository;
    private EngineDetailRepository engineDetailRepository;
    private GearboxDetailRepository gearboxDetailRepository;
    private InteriorDetailRepository interiorDetailRepository;
    private SteeringWheelDetailRepository steeringWheelDetailRepository;
    private WheelsDetailRepository wheelsDetailRepository;

    private OrderService orderService;
    private UserRepository userRepository;
    private CarPurchaseOrderRepository carPurchaseOrderRepository;
    private CarWithConfigurationPurchaseOrderRepository carWithConfigurationPurchaseOrderRepository;
    private TestDriveOrderRepository testDriveOrderRepository;

    private Configuration createConfiguration() {
        UUID id = UUID.randomUUID();
        BodyDetail bodyDetail = new BodyDetail(id, 100, "body", BodyType.SEDAN, "BLACK");
        EngineDetail engineDetail = new EngineDetail(id, 200, "engine", 500, 4);
        GearboxDetail gearboxDetail = new GearboxDetail(id, 150, "gearbox", GearboxType.AUTOMATIC);
        InteriorDetail interiorDetail = new InteriorDetail(id, 50, "interior", "BLACK");
        SteeringWheelDetail steeringWheelDetail = new SteeringWheelDetail(id, 20, "steering wheel");
        WheelsDetail wheelsDetail = new WheelsDetail(id, 100, "wheels");
        return new Configuration(bodyDetail, engineDetail, gearboxDetail, interiorDetail, steeringWheelDetail, wheelsDetail);
    }

    private CarModel createCarModel(String name, String brand) {
        UUID id = UUID.randomUUID();
        Configuration baseConfiguration = createConfiguration();
        return new CarModel(id, 10000, name, brand, baseConfiguration, new HashSet<>(),
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    private Car createCarBMW320i(UUID id) {
        CarModel model = createCarModel("BMW320i", "BMW");
        return new Car(id, model, true);
    }

    private Car createCarBMW330i(UUID id) {
        CarModel model = createCarModel("BMW330i", "BMW");
        return new Car(id, model,true);
    }

    private Car createCarPorsche911(UUID id) {
        CarModel model = createCarModel("911", "PORSCHE");
        return new Car(id, model, true);
    }

    @BeforeEach
    public void setUp() {
        carModelRepository = mock(CarModelRepository.class);
        carRepository = mock(CarRepository.class);
        carService = new CarService(carModelRepository, carRepository, carMapper);

        bodyDetailRepository = mock(BodyDetailRepository.class);
        engineDetailRepository = mock(EngineDetailRepository.class);
        gearboxDetailRepository = mock(GearboxDetailRepository.class);
        interiorDetailRepository = mock(InteriorDetailRepository.class);
        steeringWheelDetailRepository = mock(SteeringWheelDetailRepository.class);
        wheelsDetailRepository = mock(WheelsDetailRepository.class);
        detailService = new DetailService(bodyDetailRepository, engineDetailRepository, gearboxDetailRepository,
                interiorDetailRepository, steeringWheelDetailRepository, wheelsDetailRepository);

        userRepository = mock(UserRepository.class);
        carPurchaseOrderRepository = mock(CarPurchaseOrderRepository.class);
        carWithConfigurationPurchaseOrderRepository = mock(CarWithConfigurationPurchaseOrderRepository.class);
        testDriveOrderRepository = mock(TestDriveOrderRepository.class);
        carModelRepository = mock(CarModelRepository.class);

        orderService = new OrderService(userRepository, carPurchaseOrderRepository, carWithConfigurationPurchaseOrderRepository,
                testDriveOrderRepository, carModelRepository, carRepository, bodyDetailRepository, engineDetailRepository,
                gearboxDetailRepository, interiorDetailRepository, steeringWheelDetailRepository, wheelsDetailRepository);
    }

    @Test
    public void viewCar_shouldReturnCar_whenCarExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        Car car1 = createCarBMW320i(id);
        when(carRepository.find(id)).thenReturn(car1);

        // Act
        Car car2 = carService.viewCar(id);

        // Assert
        assertEquals(car1, car2);
    }

    @Test
    public void viewCar_shouldThrowException_whenCarNotFound() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(carRepository.find(id)).thenReturn(null);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> carService.viewCar(id));
    }

    @Test
    public void viewCarList_shouldReturnCars() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        List<Car> cars1 = List.of(createCarPorsche911(id1),  createCarBMW330i(id2), createCarBMW320i(id3));
        when(carRepository.findAll()).thenReturn(cars1);

        // Act
        List<Car> cars2 = carService.viewCarList();

        // Assert
        assertEquals(cars1, cars2);
        assertEquals(3, cars1.size());
    }

    @Test
    public void viewFilterCarList_shouldReturnCars() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        List<Car> cars = List.of(createCarPorsche911(id1), createCarBMW330i(id2), createCarBMW320i(id3));
        CarFilter filter = new CarFilter();
        filter.setPrice(10000);
        when(carRepository.findByFilter(any(CarFilter.class))).thenReturn(cars);

        // Act
        List<Car> result = carService.viewFilterCarList(filter);

        // Assert
        assertEquals(3, result.size());
    }

    @Test
    public void orderCar_shouldThrowException_whenValidationIsNotCorrect() {
        // Arrange
        UUID id = UUID.randomUUID();
        Car car = createCarBMW320i(id);
        User customer = new User(id, "Bob", Role.CUSTOMER);
        when(carPurchaseOrderRepository.findAll()).thenReturn(new ArrayList<>());

        // Act + Assert
        assertThrows(RuntimeException.class, () -> orderService.orderCar(customer.getId(), car.getId()));
    }

    @Test
    public void signUpTestDrive_shouldCreateOrder_whenCarAvailable() {
        // Arrange
        UUID id = UUID.randomUUID();
        Car car = createCarBMW320i(id);
        User customer = new User(id, "Bob", Role.CUSTOMER);
        carService.addTestDriveCar(carMapper.toDTO(car));
        when(testDriveOrderRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        orderService.signUpTestDrive(customer.getId(), car.getId(), "12.05.26", "12:45");

        // Assert
        verify(testDriveOrderRepository, times(1)).save(any(TestDriveOrder.class));
    }

    @Test
    public void removeTestDriveCar_shouldThrowException_whenUserIsNotCarManager() {
        // Arrange
        UUID id = UUID.randomUUID();
        Car car = createCarBMW320i(id);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> carService.deleteTestDriveCar(car.getId()));
    }

    @Test
    public void viewOrderCars_shouldReturnOrder_whenUserIsSystemAdministrator() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        List<CarPurchaseOrder> orders1 = List.of(
                new CarPurchaseOrder(id1, id1, id1, id1, 100000),
                new CarPurchaseOrder(id2, id1, id1, id1, 100000));
        when(carPurchaseOrderRepository.findAll()).thenReturn(orders1);

        // Act
        List<CarPurchaseOrder> orders2 = orderService.viewOrdersCars();

        // Assert
        assertEquals(orders1, orders2);
        assertEquals(2, orders1.size());
    }

    @Test
    public void viewOrdersCarsWithConfiguration_shouldReturnOrder_whenUserIsSystemAdministrator() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        List<CarWithConfigurationPurchaseOrder> orders1 = List.of(
                new CarWithConfigurationPurchaseOrder(id1, id1, id1, id1, 100000, createConfiguration()),
                new CarWithConfigurationPurchaseOrder(id2, id1, id1, id1, 100000, createConfiguration()));
        when(carWithConfigurationPurchaseOrderRepository.findAll()).thenReturn(orders1);

        // Act
        List<CarWithConfigurationPurchaseOrder> orders2 = orderService.viewOrdersCarsWithConfiguration();

        // Assert
        assertEquals(orders1, orders2);
        assertEquals(2, orders1.size());
    }

    @Test
    public void viewCarsForTestDrive_shouldReturnTestDriveCars() {
        // Arrange
        UUID id = UUID.randomUUID();
        Car bmw = createCarBMW320i(id);
        Car porsche = createCarPorsche911(id);
        carService.addTestDriveCar(carMapper.toDTO(bmw));
        carService.addTestDriveCar(carMapper.toDTO(porsche));
        List<Car> cars = List.of(bmw, porsche);
        when(carRepository.findAll()).thenReturn(cars);

        // Act + Assert
        assertEquals(2, carService.viewCarsForTestDrive().size());
    }

    @Test
    public void deleteCarFromSystem_shouldReturnEmptyList_whenUserIsSystemAdministrator() {
        // Arrange
        UUID id = UUID.randomUUID();
        Car bmw = createCarBMW320i(id);
        Car porsche = createCarPorsche911(id);
        when(carRepository.find(bmw.getId())).thenReturn(bmw);
        when(carRepository.find(porsche.getId())).thenReturn(porsche);
        carService.addTestDriveCar(carMapper.toDTO(bmw));
        carService.addTestDriveCar(carMapper.toDTO(porsche));
        carService.deleteCarFromSystem(bmw.getId());
        carService.deleteCarFromSystem(porsche.getId());
        when(carRepository.findAll()).thenReturn(List.of());

        // Act + Assert
        assertEquals(0, carService.viewCarsForTestDrive().size());
    }

    @Test
    public void viewTestDriveApplications_shouldReturnTestDriveApplications() {
        // Arrange
        UUID id = UUID.randomUUID();
        List<TestDriveOrder> orders1 = List.of(
                new TestDriveOrder(id, id, id, "14,12,26","12;20"),
                new TestDriveOrder(id, id, id, "14,12,26","12;20"));
        when(orderService.viewTestDriveApplications()).thenReturn(orders1);

        // Act
       List<TestDriveOrder> orders2 = orderService.viewTestDriveApplications();

        // Assert
        assertEquals(orders1, orders2);
        assertEquals(2, orders2.size());
    }

    @Test
    public void addDetailToSystem_shouldSaveDetail_whenUserIsWarehouseAdmin() {
        // Arrange
        UUID id = UUID.randomUUID();
        BodyDetail detail = new BodyDetail(id, 100, "body", BodyType.SEDAN, "BLACK");

        // Act
        detailService.addBodyDetailToSystem(detail);

        // Assert
        verify(bodyDetailRepository, times(1)).save(detail);
    }

    @Test
    public void deleteDetailFromSystem_shouldDeleteDetail_whenUserIsSystemAdministrator() {
        // Arrange
        UUID id = UUID.randomUUID();
        BodyDetail detail = new BodyDetail(id, 100, "body", BodyType.SEDAN, "BLACK");
        when(bodyDetailRepository.find(detail.getId())).thenReturn(detail);

        // Act
        detailService.deleteBodyDetailFromSystem(detail.getId());

        // Assert
        verify(bodyDetailRepository, times(1)).delete(detail.getId());
    }

    @Test
    public void viewDetail_shouldThrowException_whenUserIsCustomer() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act + Assert
        assertThrows(RuntimeException.class, () -> detailService.viewBodyDetail(id));
    }

    @Test
    public void viewDetail_shouldReturnDetail() {
        // Arrange
        UUID id = UUID.randomUUID();
        BodyDetail detail1 = new BodyDetail(id, 100, "body", BodyType.SEDAN, "BLACK");
        when(bodyDetailRepository.find(id)).thenReturn(detail1);

        // Act
        BodyDetail detail2 = detailService.viewBodyDetail(id);

        // Assert
        assertEquals(detail1, detail2);
    }

    @Test
    public void viewDetailList_shouldReturnDetails() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        List<BodyDetail> details = List.of(
                new BodyDetail(id1, 100, "body", BodyType.SEDAN, "BLACK"),
                new BodyDetail(id2, 100, "body", BodyType.SEDAN, "BLACK"));
        when(bodyDetailRepository.findAll()).thenReturn(details);

        // Act + Assert
        assertEquals(2, details.size());
    }

    @Test
    public void addCarToSystem_shouldCar_whenUserIsSystemAdministrator() {
        // Arrange
        UUID id = UUID.randomUUID();
        Car car = createCarBMW320i(id);
        when(carRepository.find(car.getId())).thenReturn(null);

        // Act
        carService.addCarToSystem(carMapper.toDTO(car));

        // Assert
        verify(carRepository, times(1)).find(car.getId());
    }

    @Test
    public void addCarToSystem_shouldThrowException_whenCarAlreadyExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        Car car = createCarBMW320i(id);
        when(carRepository.find(car.getId())).thenReturn(car);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> carService.addCarToSystem(carMapper.toDTO(car)));
    }

    @Test
    public void addCarToSystem_shouldThrowException_whenUserIsCustomers() {
        // Arrange
        UUID id = UUID.randomUUID();
        Car car = createCarBMW320i(id);
        when(carRepository.find(car.getId())).thenReturn(car);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> carService.addCarToSystem(carMapper.toDTO(car)));
    }

    @Test
    public void updateCarPurchaseOrder_shouldCompleted() {
        // Arrange
        UUID id = UUID.randomUUID();
        CarPurchaseOrder order = new CarPurchaseOrder(id, id, id, id, 100000);
        when(carPurchaseOrderRepository.find(order.getId())).thenReturn(order);

        // Act
        orderService.changeCarPurchaseOrderStatus(order.getId(), CarPurchaseOrderStatus.APPROVED);
        orderService.changeCarPurchaseOrderStatus(order.getId(), CarPurchaseOrderStatus.WAITING_PAYMENT);
        orderService.changeCarPurchaseOrderStatus(order.getId(), CarPurchaseOrderStatus.PAID);
        orderService.changeCarPurchaseOrderStatus(order.getId(), CarPurchaseOrderStatus.READY);
        orderService.changeCarPurchaseOrderStatus(order.getId(), CarPurchaseOrderStatus.COMPLETED);

        // Assert
        assertEquals(CarPurchaseOrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    public void updateCarPurchaseOrder_shouldThrowException() {
        // Arrange
        UUID id = UUID.randomUUID();
        CarPurchaseOrder order = new CarPurchaseOrder(id, id, id, id, 100000);
        when(carPurchaseOrderRepository.find(order.getId())).thenReturn(order);

        // Act
        orderService.changeCarPurchaseOrderStatus(order.getId(), CarPurchaseOrderStatus.APPROVED);
        orderService.changeCarPurchaseOrderStatus(order.getId(), CarPurchaseOrderStatus.WAITING_PAYMENT);
        orderService.changeCarPurchaseOrderStatus(order.getId(), CarPurchaseOrderStatus.PAID);

        // Assert
        assertThrows(RuntimeException.class, () -> orderService.changeCarPurchaseOrderStatus(order.getId(), CarPurchaseOrderStatus.CANCELLED));
    }

    @Test
    public void updateCarWithConfigurationPurchaseOrder_shouldCompleted() {
        // Arrange
        UUID id = UUID.randomUUID();
        CarWithConfigurationPurchaseOrder order = new CarWithConfigurationPurchaseOrder(id, id, id, id, 100000, createConfiguration());
        when(carWithConfigurationPurchaseOrderRepository.find(order.getId())).thenReturn(order);

        // Act
        orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.APPROVED);
        orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.WAITING_PAYMENT);
        orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.PAID);
        orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.WAITING_DELIVERY);
        orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.READY);
        orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.COMPLETED);

        // Assert
        assertEquals(CarWithConfigurationPurchaseOrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    public void updateCarWithConfigurationPurchaseOrder_shouldThrowException() {
        // Arrange
        UUID id = UUID.randomUUID();
        CarWithConfigurationPurchaseOrder order = new CarWithConfigurationPurchaseOrder(id, id, id, id, 100000, createConfiguration());
        when(carWithConfigurationPurchaseOrderRepository.find(order.getId())).thenReturn(order);

        // Act
        orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.APPROVED);
        orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.WAITING_PAYMENT);
        orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.PAID);

        // Assert
        assertThrows(RuntimeException.class, () -> orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.CANCELLED));
    }

    @Test
    public void updateTestDriveOrder_shouldCompleted() {
        // Arrange
        UUID id = UUID.randomUUID();
        TestDriveOrder order = new TestDriveOrder(id, id, id, "14,12,26","12;20");
        when(testDriveOrderRepository.find(order.getId())).thenReturn(order);

        // Act
        orderService.changeTestDriveOrderStatus(order.getId(), TestDriveOrderStatus.APPROVED);
        orderService.changeTestDriveOrderStatus(order.getId(), TestDriveOrderStatus.COMPLETED);

        // Assert
        assertEquals(TestDriveOrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    public void updateTestDriveOrder_shouldThrowException() {
        // Arrange
        UUID id = UUID.randomUUID();
        TestDriveOrder order = new TestDriveOrder(id, id, id, "14,12,26","12;20");

        // Act + Assert
        assertThrows(RuntimeException.class, () -> orderService.changeTestDriveOrderStatus(order.getId(), TestDriveOrderStatus.COMPLETED));
    }

    @Test
    public void calculatePrice_shouldThrowException() {
        // Arrange
        UUID id = UUID.randomUUID();
        Car car = createCarBMW320i(id);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> car.getModel().getBaseConfiguration().calculatePrice(car.getModel()));
    }
}
