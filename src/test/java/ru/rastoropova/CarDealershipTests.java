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
        Car car1 = createCarBMW320i(1);
        when(carRepository.find(1)).thenReturn(car1);

        // Act
        Car car2 = carService.viewCar(1);

        // Assert
        assertEquals(car1, car2);
    }

    @Test
    public void viewCar_shouldThrowException_whenCarNotFound() {
        // Arrange
        when(carRepository.find(1)).thenReturn(null);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> carService.viewCar(1));
    }

    @Test
    public void viewCarList_shouldReturnCars() {
        // Arrange
        List<Car> cars1 = List.of(createCarPorsche911(1),  createCarBMW330i(2), createCarBMW320i(3));
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
        CarFilter filter =  new CarFilter();
        filter.setBrand("BMW");
        List<Car> cars = List.of(createCarPorsche911(1),  createCarBMW330i(2), createCarBMW320i(3));
        when(carRepository.findAll()).thenReturn(cars);

        // Act
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
        when(carPurchaseOrderRepository.findAll()).thenReturn(new ArrayList<>());

        // Act + Assert
        assertThrows(RuntimeException.class, () -> orderService.orderCar(customer, car.getId()));
    }

    @Test
    public void orderCarWithConfiguration_shouldThrowException_whenDetailNotFound() {
        // Arrange
        Car car = mock(Car.class);
        CarModel model = mock(CarModel.class);
        User customer = new User(1, "Bob", Role.CUSTOMER);
        Configuration configuration = mock(Configuration.class);
        when(carWithConfigurationPurchaseOrderRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
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
        when(testDriveOrderRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        orderService.signUpTestDrive(customer, car.getId(), "12.05.26", "12:45");

        // Assert
        verify(testDriveOrderRepository, times(1)).save(any(TestDriveOrder.class));
    }

    @Test
    public void removeTestDriveCar_shouldThrowException_whenUserIsNotCarManager() {
        // Arrange
        Car car = createCarBMW320i(1);
        User warehouseAdministrator = new User(1, "Bob", Role.WAREHOUSE_ADMINISTRATOR);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> carService.deleteTestDriveCar(warehouseAdministrator, car.getId()));
    }

    @Test
    public void viewOrderCars_shouldReturnOrder_whenUserIsSystemAdministrator() {
        // Arrange
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);
        List<CarPurchaseOrder> orders1 = List.of(
                new CarPurchaseOrder(1, 1, 1, 1, 100000),
                new CarPurchaseOrder(2, 1, 1, 1, 100000));
        when(carPurchaseOrderRepository.findAll()).thenReturn(orders1);

        // Act
        List<CarPurchaseOrder> orders2 = orderService.viewOrdersCars(systemAdministrator);

        // Assert
        assertEquals(orders1, orders2);
        assertEquals(2, orders1.size());
    }

    @Test
    public void viewOrdersCarsWithConfiguration_shouldReturnOrder_whenUserIsSystemAdministrator() {
        // Arrange
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);
        List<CarWithConfigurationPurchaseOrder> orders1 = List.of(
                new CarWithConfigurationPurchaseOrder(1, 1, 1, 1, 100000, createConfiguration()),
                new CarWithConfigurationPurchaseOrder(2, 1, 1, 1, 100000, createConfiguration()));
        when(carWithConfigurationPurchaseOrderRepository.findAll()).thenReturn(orders1);

        // Act
        List<CarWithConfigurationPurchaseOrder> orders2 = orderService.viewOrdersCarsWithConfiguration(systemAdministrator);

        // Assert
        assertEquals(orders1, orders2);
        assertEquals(2, orders1.size());
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
        when(carRepository.findAll()).thenReturn(cars);

        // Act + Assert
        assertEquals(2, carService.viewCarsForTestDrive().size());
    }

    @Test
    public void deleteCarFromSystem_shouldReturnEmptyList_whenUserIsSystemAdministrator() {
        // Arrange
        Car bmw = createCarBMW320i(1);
        Car porsche = createCarPorsche911(1);
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);
        when(carRepository.find(bmw.getId())).thenReturn(bmw);
        when(carRepository.find(porsche.getId())).thenReturn(porsche);
        carService.addTestDriveCar(systemAdministrator, bmw);
        carService.addTestDriveCar(systemAdministrator, porsche);
        carService.deleteCarFromSystem(systemAdministrator, bmw.getId());
        carService.deleteCarFromSystem(systemAdministrator, porsche.getId());
        when(carRepository.findAll()).thenReturn(List.of());

        // Act + Assert
        assertEquals(0, carService.viewCarsForTestDrive().size());
    }

    @Test
    public void viewTestDriveApplications_shouldReturnTestDriveApplications() {
        // Arrange
        User carManager = new User(1, "Bob", Role.CAR_MANAGER);
        List<TestDriveOrder> orders1 = List.of(
                new TestDriveOrder(1, 1, 1, "14,12,26","12;20"),
                new TestDriveOrder(1, 1, 1, "14,12,26","12;20"));
        when(orderService.viewTestDriveApplications(carManager)).thenReturn(orders1);

        // Act
       List<TestDriveOrder> orders2 = orderService.viewTestDriveApplications(carManager);

        // Assert
        assertEquals(orders1, orders2);
        assertEquals(2, orders2.size());
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

        // Act + Assert
        assertThrows(RuntimeException.class, () -> detailService.addBodyDetailToSystem(customer, detail));
    }

    @Test
    public void deleteDetailFromSystem_shouldDeleteDetail_whenUserIsSystemAdministrator() {
        // Arrange
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);
        BodyDetail detail = new BodyDetail(1, 100, "body", BodyType.SEDAN, "BLACK");
        when(bodyDetailRepository.find(detail.getId())).thenReturn(detail);

        // Act
        detailService.deleteBodyDetailFromSystem(systemAdministrator, detail.getId());

        // Assert
        verify(bodyDetailRepository, times(1)).delete(detail.getId());
    }

    @Test
    public void viewDetail_shouldThrowException_whenUserIsCustomer() {
        // Arrange
        User customer = new User(1, "Bob", Role.CUSTOMER);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> detailService.viewBodyDetail(customer, 1));
    }

    @Test
    public void viewDetail_shouldReturnDetail() {
        // Arrange
        BodyDetail detail1 = new BodyDetail(1, 100, "body", BodyType.SEDAN, "BLACK");
        User warehouseAdministrator = new User(1, "Bob", Role.WAREHOUSE_ADMINISTRATOR);
        when(bodyDetailRepository.find(1)).thenReturn(detail1);

        // Act
        BodyDetail detail2 = detailService.viewBodyDetail(warehouseAdministrator, 1);

        // Assert
        assertEquals(detail1, detail2);
    }

    @Test
    public void viewDetailList_shouldReturnDetails() {
        // Arrange
        List<BodyDetail> details = List.of(
                new BodyDetail(1, 100, "body", BodyType.SEDAN, "BLACK"),
                new BodyDetail(2, 100, "body", BodyType.SEDAN, "BLACK"));
        when(bodyDetailRepository.findAll()).thenReturn(details);

        // Act + Assert
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
        when(carRepository.find(car.getId())).thenReturn(null);

        // Act
        carService.addCarToSystem(systemAdministrator, car);

        // Assert
        verify(carRepository, times(1)).find(car.getId());
    }

    @Test
    public void addCarToSystem_shouldThrowException_whenCarAlreadyExists() {
        // Arrange
        Car car = createCarBMW320i(1);
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);
        when(carRepository.find(car.getId())).thenReturn(car);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> carService.addCarToSystem(systemAdministrator, car));
    }

    @Test
    public void addCarToSystem_shouldThrowException_whenUserIsCustomers() {
        // Arrange
        Car car = createCarBMW320i(1);
        User customer = new User(1, "Bob", Role.CUSTOMER);
        when(carRepository.find(car.getId())).thenReturn(car);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> carService.addCarToSystem(customer, car));
    }

    @Test
    public void updateCarPurchaseOrder_shouldCompleted() {
        // Arrange
        CarPurchaseOrder order = new CarPurchaseOrder(2, 1, 1, 1, 100000);
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
        CarPurchaseOrder order = new CarPurchaseOrder(2, 1, 1, 1, 100000);
        when(carPurchaseOrderRepository.find(order.getId())).thenReturn(order);

        // Act
        orderService.changeCarPurchaseOrderStatus(order.getId(), CarPurchaseOrderStatus.APPROVED);
        orderService.changeCarPurchaseOrderStatus(order.getId(), CarPurchaseOrderStatus.WAITING_PAYMENT);
        orderService.changeCarPurchaseOrderStatus(order.getId(), CarPurchaseOrderStatus.PAID);

        // Assert
        assertThrows(RuntimeException.class, () -> orderService.changeCarPurchaseOrderStatus(order.getId(), CarPurchaseOrderStatus.CANCELLED));;
    }

    @Test
    public void updateCarWithConfigurationPurchaseOrder_shouldCompleted() {
        // Arrange
        CarWithConfigurationPurchaseOrder order = new CarWithConfigurationPurchaseOrder(1, 1, 1, 1, 100000, createConfiguration());
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
        CarWithConfigurationPurchaseOrder order = new CarWithConfigurationPurchaseOrder(1, 1, 1, 1, 100000, createConfiguration());
        when(carWithConfigurationPurchaseOrderRepository.find(order.getId())).thenReturn(order);

        // Act
        orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.APPROVED);
        orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.WAITING_PAYMENT);
        orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.PAID);

        // Assert
        assertThrows(RuntimeException.class, () -> orderService.changeCarWithConfigurationPurchaseOrderStatus(order.getId(), CarWithConfigurationPurchaseOrderStatus.CANCELLED));;
    }

    @Test
    public void updateTestDriveOrder_shouldCompleted() {
        // Arrange
        TestDriveOrder order = new TestDriveOrder(1, 1, 1, "14,12,26","12;20");
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
        TestDriveOrder order = new TestDriveOrder(1, 1, 1, "14,12,26","12;20");

        // Act + Assert
        assertThrows(RuntimeException.class, () -> orderService.changeTestDriveOrderStatus(order.getId(), TestDriveOrderStatus.COMPLETED));;
    }

    @Test
    public void calculatePrice_shouldThrowException() {
        // Arrange
        Car car = createCarBMW320i(1);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> car.getConfiguration().calculatePrice(car.getModel()));
    }
}
