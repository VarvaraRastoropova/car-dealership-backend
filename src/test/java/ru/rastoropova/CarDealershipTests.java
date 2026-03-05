package ru.rastoropova;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rastoropova.business_logic.car_characteristics.*;
import ru.rastoropova.business_logic.cars.Car;
import ru.rastoropova.business_logic.cars.CarFilter;
import ru.rastoropova.business_logic.cars.CarInformation;
import ru.rastoropova.business_logic.cars.Configuration;
import ru.rastoropova.business_logic.details.*;
import ru.rastoropova.business_logic.orders.*;
import ru.rastoropova.business_logic.repositories.*;
import ru.rastoropova.business_logic.services.CarService;
import ru.rastoropova.business_logic.services.DetailService;
import ru.rastoropova.business_logic.services.OrderService;
import ru.rastoropova.business_logic.users.Role;
import ru.rastoropova.business_logic.users.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CarDealershipTests {
    private CarService carService;
    private CarRepository carRepository;

    private DetailService detailService;
    private BodyDetailRepository bodyDetailRepository;
    private EngineDetailRepository engineDetailRepository;
    private GearboxDetailRepository gearboxDetailRepository;
    private InteriorDetailRepository interiorDetailRepository;
    private SteeringWheelDetailRepository steeringWheelDetailRepository;
    private WheelsDetailRepository wheelsDetailRepository;

    private OrderService orderService;
    private CarPurchaseOrderRepository carPurchaseOrderRepository;
    private CarWithConfigurationPurchaseOrderRepository carWithConfigurationPurchaseOrderRepository;
    private TestDriveOrderRepository testDriveOrderRepository;

    private Configuration createConfiguration() {
        BodyDetail bodyDetail = new BodyDetail(1, 100, "body", BodyType.SEDAN, "BLACK");
        EngineDetail engineDetail = new EngineDetail(1, 200, "engine", 500, 4);
        GearboxDetail gearboxDetail = new GearboxDetail(1, 150, "gearbox", GearboxType.AUTOMATIC);
        InteriorDetail interiorDetail = new InteriorDetail(1, 50, "interior", "BLACK");
        SteeringWheelDetail steeringWheelDetail = new SteeringWheelDetail(1, 20, "steering wheel");
        WheelsDetail wheelsDetail = new WheelsDetail(1, 100, "wheels");
        return new Configuration(bodyDetail, engineDetail, gearboxDetail, interiorDetail, steeringWheelDetail, wheelsDetail);
    }

    private CarModel createCarModel(String name, String brand) {
        Configuration baseConfiguration = createConfiguration();
        return new CarModel(1, 10000, name, brand, baseConfiguration, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    private Car createCarBMW320i(int id) {
        Configuration baseConfiguration = createConfiguration();
        CarModel model = createCarModel("BMW320i", "BMW");
        return new Car(id, model, baseConfiguration);
    }

    private Car createCarBMW330i(int id) {
        Configuration baseConfiguration = createConfiguration();
        CarModel model = createCarModel("BMW330i", "BMW");
        return new Car(id, model, baseConfiguration);
    }

    private Car createCarPorsche911(int id) {
        Configuration baseConfiguration = createConfiguration();
        CarModel model = createCarModel("911", "PORSCHE");
        return new Car(id, model, baseConfiguration);
    }

    @BeforeEach
    public void setUp() {
        carRepository = mock(CarRepository.class);
        carService = new CarService(carRepository);

        bodyDetailRepository = mock(BodyDetailRepository.class);
        engineDetailRepository = mock(EngineDetailRepository.class);
        gearboxDetailRepository = mock(GearboxDetailRepository.class);
        interiorDetailRepository = mock(InteriorDetailRepository.class);
        steeringWheelDetailRepository = mock(SteeringWheelDetailRepository.class);
        wheelsDetailRepository = mock(WheelsDetailRepository.class);
        detailService = new DetailService(bodyDetailRepository, engineDetailRepository, gearboxDetailRepository,
                interiorDetailRepository, steeringWheelDetailRepository, wheelsDetailRepository);

        carPurchaseOrderRepository  = mock(CarPurchaseOrderRepository.class);
        carWithConfigurationPurchaseOrderRepository  = mock(CarWithConfigurationPurchaseOrderRepository.class);
        testDriveOrderRepository = mock(TestDriveOrderRepository.class);
        orderService = new OrderService(carPurchaseOrderRepository, carWithConfigurationPurchaseOrderRepository, testDriveOrderRepository);
    }

    @Test
    public void viewCar_shouldReturnCar_whenCarExists() {
        // Arrange
        Car car = createCarBMW320i(1);

        // Act
        when(carRepository.find(1)).thenReturn(car);

        // Assert
        assertEquals(car, carService.viewCar(1));
    }

    @Test
    public void viewCar_shouldThrowException_whenCarNotFound() {
        // Act
        when(carRepository.find(1)).thenReturn(null);

        // Assert
        assertThrows(RuntimeException.class, () -> carService.viewCar(1));
    }

    @Test
    public void viewCarList_shouldReturnCars() {
        // Arrange
        List<Car> cars = List.of(createCarPorsche911(1),  createCarBMW330i(2), createCarBMW320i(3));

        // Act
        when(carRepository.findAll()).thenReturn(cars);

        // Assert
        assertEquals(cars, carService.viewCarList());
        assertEquals(3, cars.size());
    }

    @Test
    public void viewFilterCarList_shouldReturnCars() {
        // Arrange
        CarFilter filter =  new CarFilter();
        filter.setBrand("BMW");
        List<Car> cars = List.of(createCarPorsche911(1),  createCarBMW330i(2), createCarBMW320i(3));

        // Act
        when(carRepository.findAll()).thenReturn(cars);
        carService.viewFilterCarList(filter);

        // Assert
        assertEquals(2, carService.viewFilterCarList(filter).size());
    }

    @Test
    public void orderCar_shouldThrowException_whenValidationIsNotCorrect() {
        // Arrange
        Car car = createCarBMW320i(1);
        CarModel model = car.getModel();
        User customer = new User(1, "Bob", Role.CUSTOMER);
        User carManager = new User(1, "Bob", Role.CAR_MANAGER);
        CarPurchaseOrder order = new CarPurchaseOrder(1, customer.getId(), carManager.getId(), model.getId(), model.getBasePrice());

        // Act
        when(carPurchaseOrderRepository.findAll()).thenReturn(new ArrayList<>());

        // Assert
        assertThrows(RuntimeException.class, () -> orderService.orderCar(customer, car));
    }

    @Test
    public void orderCarWithConfiguration_shouldThrowException_whenDetailNotFound() {
        // Arrange
        Car car = mock(Car.class);
        CarModel model = mock(CarModel.class);
        User customer = new User(1, "Bob", Role.CUSTOMER);
        Configuration configuration = mock(Configuration.class);

        // Act
        when(carWithConfigurationPurchaseOrderRepository.findAll()).thenReturn(new ArrayList<>());
        orderService.orderCarWithConfiguration(customer, model, configuration);

        // Assert
        verify(carWithConfigurationPurchaseOrderRepository, times(1)).save(any());
    }

    @Test
    public void signUpTestDrive_shouldCreateOrder_whenCarAvailable() {
        // Arrange
        Car car = createCarBMW320i(1);
        User customer = new User(1, "Bob", Role.CUSTOMER);
        User carManager = new User(1, "Bob", Role.CAR_MANAGER);
        carService.addTestDriveCar(carManager, car);

        // Act
        when(testDriveOrderRepository.findAll()).thenReturn(new ArrayList<>());
        orderService.signUpTestDrive(customer, car, "12.05.26", "12:45");

        // Assert
        verify(testDriveOrderRepository, times(1)).save(any(TestDriveOrder.class));
    }

    @Test
    public void removeTestDriveCar_shouldThrowException_whenUserIsNotCarManager() {
        // Arrange
        Car car = createCarBMW320i(1);
        User warehouseAdministrator = new User(1, "Bob", Role.WAREHOUSE_ADMINISTRATOR);

        // Assert
        assertThrows(RuntimeException.class, () -> carService.deleteTestDriveCar(warehouseAdministrator, car));
    }

    @Test
    public void viewOrderCars_shouldReturnOrder_whenUserIsSystemAdministrator() {
        // Arrange
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);
        List<CarPurchaseOrder> orders = List.of(
                new CarPurchaseOrder(1, 1, 1, 1, 100000),
                new CarPurchaseOrder(2, 1, 1, 1, 100000));

        // Act
        when(carPurchaseOrderRepository.findAll()).thenReturn(orders);

        // Assert
        assertEquals(orders, orderService.viewOrdersCars(systemAdministrator));
        assertEquals(2, orders.size());
    }

    @Test
    public void viewOrdersCarsWithConfiguration_shouldReturnOrder_whenUserIsSystemAdministrator() {
        // Arrange
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);
        List<CarWithConfigurationPurchaseOrder> orders = List.of(
                new CarWithConfigurationPurchaseOrder(1, 1, 1, 1, 100000, createConfiguration()),
                new CarWithConfigurationPurchaseOrder(2, 1, 1, 1, 100000, createConfiguration()));

        // Act
        when(carWithConfigurationPurchaseOrderRepository.findAll()).thenReturn(orders);

        // Assert
        assertEquals(orders, orderService.viewOrdersCarsWithConfiguration(systemAdministrator));
        assertEquals(2, orders.size());
    }

    @Test
    public void viewCarsForTestDrive_shouldReturnTestDriveCars() {
        // Arrange
        Car bmw = createCarBMW320i(1);
        Car porsche = createCarPorsche911(1);
        User carManager = new User(1, "Bob", Role.CAR_MANAGER);
        carService.addTestDriveCar(carManager, bmw);
        carService.addTestDriveCar(carManager, porsche);
        List<Car> cars = List.of(bmw, porsche);

        // Act
        when(carRepository.findAll()).thenReturn(cars);

        // Assert
        assertEquals(2, carService.viewCarsForTestDrive().size());
    }

    @Test
    public void deleteCarFromSystem_shouldReturnEmptyList_whenUserIsSystemAdministrator() {
        // Arrange
        Car bmw = createCarBMW320i(1);
        Car porsche = createCarPorsche911(1);
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);
        carService.addTestDriveCar(systemAdministrator, bmw);
        carService.addTestDriveCar(systemAdministrator, porsche);
        carService.deleteCarFromSystem(systemAdministrator, bmw);
        carService.deleteCarFromSystem(systemAdministrator, porsche);

        // Act
        when(carRepository.findAll()).thenReturn(List.of());

        // Assert
        assertEquals(0, carService.viewCarsForTestDrive().size());
    }

    @Test
    public void viewTestDriveApplications_shouldReturnTestDriveApplications() {
        // Arrange
        User carManager = new User(1, "Bob", Role.CAR_MANAGER);
        List<TestDriveOrder> orders = List.of(
                new TestDriveOrder(1, 1, 1, "14,12,26","12;20"),
                new TestDriveOrder(1, 1, 1, "14,12,26","12;20"));

        // Act
        when(orderService.viewTestDriveApplications(carManager)).thenReturn(orders);

        // Assert
        assertEquals(orders, orderService.viewTestDriveApplications(carManager));
        assertEquals(2, orderService.viewTestDriveApplications(carManager).size());
    }

    @Test
    public void addDetailToSystem_shouldSaveDetail_whenUserIsWarehouseAdmin() {
        // Arrange
        User warehouseAdministrator = new User(1, "Bob", Role.WAREHOUSE_ADMINISTRATOR);
        BodyDetail detail = new BodyDetail(1, 100, "body", BodyType.SEDAN, "BLACK");

        // Act
        detailService.addBodyDetailToSystem(warehouseAdministrator, detail);

        // Assert
        verify(bodyDetailRepository, times(1)).save(detail);
    }

    @Test
    public void addDetailToSystem_shouldThrowException_whenUserIsCustomer() {
        // Arrange
        User customer = new User(1, "Bob", Role.CUSTOMER);
        BodyDetail detail = new BodyDetail(1, 100, "body", BodyType.SEDAN, "BLACK");

        // Assert
        assertThrows(RuntimeException.class, () -> detailService.addBodyDetailToSystem(customer, detail));
    }

    @Test
    public void deleteDetailFromSystem_shouldDeleteDetail_whenUserIsSystemAdministrator() {
        // Arrange
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);
        BodyDetail detail = new BodyDetail(1, 100, "body", BodyType.SEDAN, "BLACK");

        // Act
        detailService.deleteBodyDetailFromSystem(systemAdministrator, detail);

        // Assert
        verify(bodyDetailRepository, times(1)).delete(detail);
    }

    @Test
    public void viewDetail_shouldThrowException_whenUserIsCustomer() {
        // Arrange
        User customer = new User(1, "Bob", Role.CUSTOMER);

        // Assert
        assertThrows(RuntimeException.class, () -> detailService.viewBodyDetail(customer, 1));
    }

    @Test
    public void viewDetail_shouldReturnDetail() {
        // Arrange
        BodyDetail detail = new BodyDetail(1, 100, "body", BodyType.SEDAN, "BLACK");
        User warehouseAdministrator = new User(1, "Bob", Role.WAREHOUSE_ADMINISTRATOR);

        // Act
        when(bodyDetailRepository.find(1)).thenReturn(detail);

        // Assert
        assertEquals(detail, detailService.viewBodyDetail(warehouseAdministrator, 1));
    }

    @Test
    public void viewDetailList_shouldReturnDetails() {
        // Arrange
        List<BodyDetail> details = List.of(
                new BodyDetail(1, 100, "body", BodyType.SEDAN, "BLACK"),
                new BodyDetail(2, 100, "body", BodyType.SEDAN, "BLACK"));
        User warehouseAdministrator = new User(1, "Bob", Role.WAREHOUSE_ADMINISTRATOR);

        // Act
        when(bodyDetailRepository.findAll()).thenReturn(details);

        // Assert
        assertEquals(2, details.size());
    }

    @Test
    public void changeInformation_shouldChangedCar() {
        // Arrange
        Car car = createCarBMW320i(1);
        CarInformation information = new CarInformation();
        information.setModel(createCarModel("310i", "BMW"));

        // Act
        car.changeInformation(car.getId(), information);

        // Assert
        assertEquals(car.getModel(), information.getModel());
    }

    @Test
    public void addCarToSystem_shouldCar_whenUserIsSystemAdministrator() {
        // Arrange
        Car car = createCarBMW320i(1);
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);

        // Act
        when(carRepository.find(car.getId())).thenReturn(null);
        carService.addCarToSystem(systemAdministrator, car);

        // Assert
        verify(carRepository, times(1)).find(car.getId());
    }

    @Test
    public void addCarToSystem_shouldThrowException_whenCarAlreadyExists() {
        // Arrange
        Car car = createCarBMW320i(1);
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);

        // Act
        when(carRepository.find(car.getId())).thenReturn(car);

        // Assert
        assertThrows(RuntimeException.class, () -> carService.addCarToSystem(systemAdministrator, car));
    }

    @Test
    public void addCarToSystem_shouldThrowException_whenUserIsCustomers() {
        // Arrange
        Car car = createCarBMW320i(1);
        User customer = new User(1, "Bob", Role.CUSTOMER);

        // Act
        when(carRepository.find(car.getId())).thenReturn(car);

        // Assert
        assertThrows(RuntimeException.class, () -> carService.addCarToSystem(customer, car));
    }

    @Test
    public void updateCarPurchaseOrder_shouldCompleted() {
        // Arrange
        CarPurchaseOrder order = new CarPurchaseOrder(2, 1, 1, 1, 100000);

        // Act
        order.approve();
        order.waitPayment();
        order.pay();
        order.ready();
        order.complete();

        // Assert
        assertEquals(CarPurchaseOrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    public void updateCarPurchaseOrder_shouldThrowException() {
        // Arrange
        CarPurchaseOrder order = new CarPurchaseOrder(2, 1, 1, 1, 100000);

        // Act
        order.approve();
        order.waitPayment();
        order.pay();

        // Assert
        assertThrows(RuntimeException.class, () -> order.cancel());;
    }

    @Test
    public void updateCarWithConfigurationPurchaseOrder_shouldCompleted() {
        // Arrange
        CarWithConfigurationPurchaseOrder order = new CarWithConfigurationPurchaseOrder(1, 1, 1, 1, 100000, createConfiguration());

        // Act
        order.approve();
        order.waitPayment();
        order.pay();
        order.waitDelivery();
        order.ready();
        order.complete();

        // Assert
        assertEquals(CarWithConfigurationPurchaseOrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    public void updateCarWithConfigurationPurchaseOrder_shouldThrowException() {
        // Arrange
        CarWithConfigurationPurchaseOrder order = new CarWithConfigurationPurchaseOrder(1, 1, 1, 1, 100000, createConfiguration());

        // Act
        order.approve();
        order.waitPayment();
        order.pay();

        // Assert
        assertThrows(RuntimeException.class, () -> order.cancel());;
    }

    @Test
    public void updateTestDriveOrder_shouldCompleted() {
        // Arrange
        TestDriveOrder order = new TestDriveOrder(1, 1, 1, "14,12,26","12;20");

        // Act
        order.approve();
        order.complete();

        // Assert
        assertEquals(TestDriveOrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    public void updateTestDriveOrder_shouldThrowException() {
        // Arrange
        TestDriveOrder order = new TestDriveOrder(1, 1, 1, "14,12,26","12;20");

        // Assert
        assertThrows(RuntimeException.class, () -> order.complete());;
    }

    @Test
    public void calculatePrice_shouldThrowException() {
        // Arrange
        Car car = createCarBMW320i(1);

        // Assert
        assertThrows(RuntimeException.class, () -> car.getConfiguration().calculatePrice(car.getModel()));
    }
}
