package ru.rastoropova.integration_layer.grpc;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.rastoropova.grpc.CarServiceGrpc;
import ru.rastoropova.grpc.Cars;
import ru.rastoropova.service_layer.cars.Car;
import ru.rastoropova.service_layer.services.CarService;
import java.util.List;
import java.util.UUID;

@GrpcService
@AllArgsConstructor
public class CarGrpcService extends CarServiceGrpc.CarServiceImplBase {
    private CarService carService;

    @Override
    public void getAvailableCars(Cars.GetCarsRequest request, StreamObserver<Cars.GetCarsResponse> responseObserver) {
        List<Car> cars = carService.viewCarList().stream()
                .filter(Car::isAvailableForTestDrive)
                .toList();
        Cars.GetCarsResponse.Builder responseBuilder = Cars.GetCarsResponse.newBuilder();
        for (Car car : cars) {
            responseBuilder.addCars(toGrpcResponse(car));
        }
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getCarById(Cars.GetCarByIdRequest request, StreamObserver<Cars.CarResponse> responseObserver) {
        UUID id = UUID.fromString(request.getId());
        Car car = carService.viewCar(id);
        responseObserver.onNext(toGrpcResponse(car));
        responseObserver.onCompleted();
    }

    private Cars.CarResponse toGrpcResponse(Car car) {
        return Cars.CarResponse.newBuilder()
                .setId(car.getId().toString())
                .setModelName(car.getModel().getName())
                .setBrand(car.getModel().getBrand())
                .setPrice(car.getModel().getBasePrice())
                .setAvailableForTestDrive(car.isAvailableForTestDrive())
                .build();
    }
}
