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
    private DetailRepository detailRepository;

    private OrderService orderService;
    private CarPurchaseOrderRepository carPurchaseOrderRepository;
    private CarWithConfigurationPurchaseOrderRepository carWithConfigurationPurchaseOrderRepository;
    private TestDriveOrderRepository testDriveOrderRepository;

    private Configuration createConfiguration() {
        BodyDetail bodyDetail = new BodyDetail(1, 100, "body", BodyType.SEDAN, Color.BLACK);
        EngineDetail engineDetail = new EngineDetail(1, 200, "engine", EnginePower.HP_500, EngineVolume.V_4);
        GearboxDetail gearboxDetail = new GearboxDetail(1, 150, "gearbox", GearboxType.AUTOMATIC);
        InteriorDetail interiorDetail = new InteriorDetail(1, 50, "interior", Color.BLACK);
        SteeringWheelDetail steeringWheelDetail = new SteeringWheelDetail(1, 20, "steering wheel");
        WheelsDetail wheelsDetail = new WheelsDetail(1, 100, "wheels");
        return new Configuration(bodyDetail, engineDetail, gearboxDetail, interiorDetail, steeringWheelDetail, wheelsDetail);
    }

    private CarModel createCarModel(String name, CarBrand brand) {
        Configuration baseConfiguration = createConfiguration();
        return new CarModel(1, 10000, name, brand, baseConfiguration, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    private Car createCarBMW320i(int id) {
        Configuration baseConfiguration = createConfiguration();
        CarModel model = createCarModel("BMW320i", CarBrand.BMW);
        return new Car(id, model, baseConfiguration);
    }

    private Car createCarBMW330i(int id) {
        Configuration baseConfiguration = createConfiguration();
        CarModel model = createCarModel("BMW330i", CarBrand.BMW);
        return new Car(id, model, baseConfiguration);
    }

    private Car createCarPorsche911(int id) {
        Configuration baseConfiguration = createConfiguration();
        CarModel model = createCarModel("911", CarBrand.PORSCHE);
        return new Car(id, model, baseConfiguration);
    }

    @BeforeEach
    public void setUp() {
        carRepository = mock(CarRepository.class);
        carService = new CarService(carRepository);

        detailRepository = mock(DetailRepository.class);
        detailService = new DetailService(detailRepository);

        carPurchaseOrderRepository  = mock(CarPurchaseOrderRepository.class);
        carWithConfigurationPurchaseOrderRepository  = mock(CarWithConfigurationPurchaseOrderRepository.class);
        testDriveOrderRepository = mock(TestDriveOrderRepository.class);
        orderService = new OrderService(carPurchaseOrderRepository, carWithConfigurationPurchaseOrderRepository, testDriveOrderRepository);
    }

    @Test
    public void viewCar_shouldReturnCar_whenCarExists() {
        Car car = createCarBMW320i(1);

        when(carRepository.find(1)).thenReturn(car);

        assertEquals(car, carService.viewCar(1));
    }

    @Test
    public void viewCar_shouldThrowException_whenCarNotFound() {
        when(carRepository.find(1)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> carService.viewCar(1));
    }

    @Test
    public void viewCarList_shouldReturnCars() {
        List<Car> cars = List.of(createCarPorsche911(1),  createCarBMW330i(2), createCarBMW320i(3));

        when(carRepository.findAll()).thenReturn(cars);

        assertEquals(cars, carService.viewCarList());
        assertEquals(3, cars.size());
    }

    @Test
    public void viewFilterCarList_shouldReturnCars() {
        CarFilter filter =  new CarFilter();
        filter.setBrand(CarBrand.BMW);
        List<Car> cars = List.of(createCarPorsche911(1),  createCarBMW330i(2), createCarBMW320i(3));

        when(carRepository.findAll()).thenReturn(cars);
        carService.viewFilterCarList(filter);

        assertEquals(2, carService.viewFilterCarList(filter).size());
    }

    @Test
    public void orderCar_shouldThrowException_whenValidationIsNotCorrect() {
        Car car = createCarBMW320i(1);
        CarModel model = car.getModel();
        User customer = new User(1, "Bob", Role.CUSTOMER);
        User carManager = new User(1, "Bob", Role.CAR_MANAGER);
        CarPurchaseOrder order = new CarPurchaseOrder(1, customer.getId(), carManager.getId(), model.getId(), model.getBasePrice());

        when(carPurchaseOrderRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(RuntimeException.class, () -> orderService.orderCar(customer, car));
    }

    @Test
    public void orderCarWithConfiguration_shouldThrowException_whenDetailNotFound() {
        Car car = mock(Car.class);
        CarModel model = mock(CarModel.class);
        User customer = new User(1, "Bob", Role.CUSTOMER);
        Configuration configuration = mock(Configuration.class);

        when(carWithConfigurationPurchaseOrderRepository.findAll()).thenReturn(new ArrayList<>());
        orderService.orderCarWithConfiguration(customer, model, configuration);

        verify(carWithConfigurationPurchaseOrderRepository, times(1)).save(any());
    }

    @Test
    public void signUpTestDrive_shouldCreateOrder_whenCarAvailable() {
        Car car = createCarBMW320i(1);
        User customer = new User(1, "Bob", Role.CUSTOMER);
        User carManager = new User(1, "Bob", Role.CAR_MANAGER);
        carService.addTestDriveCar(carManager, car);

        when(testDriveOrderRepository.findAll()).thenReturn(new ArrayList<>());
        orderService.signUpTestDrive(customer, car, "12.05.26", "12:45");
        verify(testDriveOrderRepository, times(1)).save(any(TestDriveOrder.class));
    }

    @Test
    public void removeTestDriveCar_shouldThrowException_whenUserIsNotCarManager() {
        Car car = createCarBMW320i(1);
        User customer = new User(1, "Bob", Role.CUSTOMER);
        User warehouseAdministrator = new User(1, "Bob", Role.WAREHOUSE_ADMINISTRATOR);

        assertThrows(RuntimeException.class, () -> carService.deleteTestDriveCar(warehouseAdministrator, car));
    }

    @Test
    public void viewOrderCars_shouldReturnOrder_whenUserIsSystemAdministrator() {
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);
        List<CarPurchaseOrder> orders = List.of(
                new CarPurchaseOrder(1, 1, 1, 1, 100000),
                new CarPurchaseOrder(2, 1, 1, 1, 100000));

        when(carPurchaseOrderRepository.findAll()).thenReturn(orders);

        assertEquals(orders, orderService.viewOrdersCars(systemAdministrator));
        assertEquals(2, orders.size());
    }

    @Test
    public void viewOrdersCarsWithConfiguration_shouldReturnOrder_whenUserIsSystemAdministrator() {
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);
        List<CarWithConfigurationPurchaseOrder> orders = List.of(
                new CarWithConfigurationPurchaseOrder(1, 1, 1, 1, 100000, createConfiguration()),
                new CarWithConfigurationPurchaseOrder(2, 1, 1, 1, 100000, createConfiguration()));

        when(carWithConfigurationPurchaseOrderRepository.findAll()).thenReturn(orders);

        assertEquals(orders, orderService.viewOrdersCarsWithConfiguration(systemAdministrator));
        assertEquals(2, orders.size());
    }

    @Test
    public void viewCarsForTestDrive_shouldReturnTestDriveCars() {
        Car bmw = createCarBMW320i(1);
        Car porsche = createCarPorsche911(1);
        User carManager = new User(1, "Bob", Role.CAR_MANAGER);
        carService.addTestDriveCar(carManager, bmw);
        carService.addTestDriveCar(carManager, porsche);
        List<Car> cars = List.of(bmw, porsche);

        when(carRepository.findAll()).thenReturn(cars);

        assertEquals(2, carService.viewCarsForTestDrive().size());
    }

    @Test
    public void deleteCarFromSystem_shouldReturnEmptyList_whenUserIsSystemAdministrator() {
        Car bmw = createCarBMW320i(1);
        Car porsche = createCarPorsche911(1);
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);
        carService.addTestDriveCar(systemAdministrator, bmw);
        carService.addTestDriveCar(systemAdministrator, porsche);
        carService.deleteCarFromSystem(systemAdministrator, bmw);
        carService.deleteCarFromSystem(systemAdministrator, porsche);

        when(carRepository.findAll()).thenReturn(List.of());

        assertEquals(0, carService.viewCarsForTestDrive().size());
    }

    @Test
    public void viewTestDriveApplications_shouldReturnTestDriveApplications() {
        User carManager = new User(1, "Bob", Role.CAR_MANAGER);
        List<TestDriveOrder> orders = List.of(
                new TestDriveOrder(1, 1, 1, "14,12,26","12;20"),
                new TestDriveOrder(1, 1, 1, "14,12,26","12;20"));

        when(orderService.viewTestDriveApplications(carManager)).thenReturn(orders);

        assertEquals(orders, orderService.viewTestDriveApplications(carManager));
        assertEquals(2, orderService.viewTestDriveApplications(carManager).size());
    }

    @Test
    public void addDetailToSystem_shouldSaveDetail_whenUserIsWarehouseAdmin() {
        User warehouseAdministrator = new User(1, "Bob", Role.WAREHOUSE_ADMINISTRATOR);
        Detail detail = new BodyDetail(1, 100, "body", BodyType.SEDAN, Color.BLACK);

        detailService.addDetailToSystem(warehouseAdministrator, detail);

        verify(detailRepository, times(1)).save(detail);
    }

    @Test
    public void addDetailToSystem_shouldThrowException_whenUserIsCustomer() {
        User customer = new User(1, "Bob", Role.CUSTOMER);
        Detail detail = new BodyDetail(1, 100, "body", BodyType.SEDAN, Color.BLACK);

        assertThrows(RuntimeException.class, () -> detailService.addDetailToSystem(customer, detail));
    }

    @Test
    public void deleteDetailFromSystem_shouldDeleteDetail_whenUserIsSystemAdministrator() {
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);
        Detail detail = new BodyDetail(1, 100, "body", BodyType.SEDAN, Color.BLACK);

        detailService.deleteDetailFromSystem(systemAdministrator, detail);

        verify(detailRepository, times(1)).delete(detail);
    }

    @Test
    public void viewDetail_shouldThrowException_whenUserIsCustomer() {
        User customer = new User(1, "Bob", Role.CUSTOMER);

        assertThrows(RuntimeException.class, () -> detailService.viewDetail(customer, 1));
    }

    @Test
    public void viewDetail_shouldReturnDetail() {
        Detail detail = new BodyDetail(1, 100, "body", BodyType.SEDAN, Color.BLACK);
        User warehouseAdministrator = new User(1, "Bob", Role.WAREHOUSE_ADMINISTRATOR);

        when(detailRepository.find(1)).thenReturn(detail);

        assertEquals(detail, detailService.viewDetail(warehouseAdministrator, 1));
    }

    @Test
    public void viewDetailList_shouldReturnDetails() {
        List<Detail> details = List.of(
                new BodyDetail(1, 100, "body", BodyType.SEDAN, Color.BLACK),
                new EngineDetail(1, 200, "engine", EnginePower.HP_500, EngineVolume.V_4));
        User warehouseAdministrator = new User(1, "Bob", Role.WAREHOUSE_ADMINISTRATOR);

        when(detailRepository.findAll()).thenReturn(details);

        assertEquals(details, detailService.viewDetailList(warehouseAdministrator));
        assertEquals(2, details.size());
    }

    @Test
    public void changeInformation_shouldChangedCar() {
        Car car = createCarBMW320i(1);
        CarInformation information = new CarInformation();
        information.setModel(createCarModel("310i", CarBrand.BMW));

        car.changeInformation(car.getId(), information);

        assertEquals(car.getModel(), information.getModel());
    }

    @Test
    public void addCarToSystem_shouldCar_whenUserIsSystemAdministrator() {
        Car car = createCarBMW320i(1);
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);

        when(carRepository.find(car.getId())).thenReturn(null);
        carService.addCarToSystem(systemAdministrator, car);

        verify(carRepository, times(1)).find(car.getId());
    }

    @Test
    public void addCarToSystem_shouldThrowException_whenCarAlreadyExists() {
        Car car = createCarBMW320i(1);
        User systemAdministrator = new User(1, "Bob", Role.SYSTEM_ADMINISTRATOR);

        when(carRepository.find(car.getId())).thenReturn(car);

        assertThrows(RuntimeException.class, () -> carService.addCarToSystem(systemAdministrator, car));
    }

    @Test
    public void addCarToSystem_shouldThrowException_whenUserIsCustomers() {
        Car car = createCarBMW320i(1);
        User customer = new User(1, "Bob", Role.CUSTOMER);

        when(carRepository.find(car.getId())).thenReturn(car);

        assertThrows(RuntimeException.class, () -> carService.addCarToSystem(customer, car));
    }

    @Test
    public void updateCarPurchaseOrder_shouldCompleted() {
        CarPurchaseOrder order = new CarPurchaseOrder(2, 1, 1, 1, 100000);
        order.approve();
        order.waitPayment();
        order.pay();
        order.ready();
        order.complete();

        assertEquals(CarPurchaseOrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    public void updateCarPurchaseOrder_shouldThrowException() {
        CarPurchaseOrder order = new CarPurchaseOrder(2, 1, 1, 1, 100000);
        order.approve();
        order.waitPayment();
        order.pay();

        assertThrows(RuntimeException.class, () -> order.cancel());;
    }

    @Test
    public void updateCarWithConfigurationPurchaseOrder_shouldCompleted() {
        CarWithConfigurationPurchaseOrder order = new CarWithConfigurationPurchaseOrder(1, 1, 1, 1, 100000, createConfiguration());
        order.approve();
        order.waitPayment();
        order.pay();
        order.waitDelivery();
        order.ready();
        order.complete();

        assertEquals(CarWithConfigurationPurchaseOrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    public void updateCarWithConfigurationPurchaseOrder_shouldThrowException() {
        CarWithConfigurationPurchaseOrder order = new CarWithConfigurationPurchaseOrder(1, 1, 1, 1, 100000, createConfiguration());
        order.approve();
        order.waitPayment();
        order.pay();

        assertThrows(RuntimeException.class, () -> order.cancel());;
    }

    @Test
    public void updateTestDriveOrder_shouldCompleted() {
        TestDriveOrder order = new TestDriveOrder(1, 1, 1, "14,12,26","12;20");
        order.approve();
        order.complete();

        assertEquals(TestDriveOrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    public void updateTestDriveOrder_shouldThrowException() {
        TestDriveOrder order = new TestDriveOrder(1, 1, 1, "14,12,26","12;20");

        assertThrows(RuntimeException.class, () -> order.complete());;
    }

    @Test
    public void calculatePrice_shouldThrowException() {
        Car car = createCarBMW320i(1);

        assertThrows(RuntimeException.class, () -> car.getConfiguration().calculatePrice(car.getModel()));
    }
}
