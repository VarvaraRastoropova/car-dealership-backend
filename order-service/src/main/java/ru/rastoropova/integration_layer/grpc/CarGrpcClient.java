package ru.rastoropova.integration_layer.grpc;

import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.rastoropova.grpc.CarServiceGrpc;
import ru.rastoropova.grpc.Cars;
import ru.rastoropova.integration_layer.dto.CarDTO;
import ru.rastoropova.integration_layer.exceptions.StorageServiceUnavailableException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class CarGrpcClient {
    @GrpcClient("storage-service")
    private CarServiceGrpc.CarServiceBlockingStub carServiceStub;

    public List<CarDTO> getAvailableCars() {
        try {
            Cars.GetCarsResponse response = carServiceStub
                    .withDeadlineAfter(5, TimeUnit.SECONDS)
                    .getAvailableCars(Cars.GetCarsRequest.newBuilder().build());
            return response.getCarsList().stream()
                    .map(car -> new CarDTO(
                            car.getId(),
                            car.getAvailableForTestDrive(),
                            car.getPrice()))
                    .toList();
        } catch (StatusRuntimeException e) {
            throw new StorageServiceUnavailableException("Error: Storage service unavailable.");
        }
    }

    public CarDTO getCarById(UUID id) {
        try {
            Cars.CarResponse response = carServiceStub
                    .withDeadlineAfter(5, TimeUnit.SECONDS)
                    .getCarById(Cars.GetCarByIdRequest.newBuilder().setId(id.toString()).build());
            return new CarDTO(
                    response.getId(),
                    response.getAvailableForTestDrive(),
                    response.getPrice());
        } catch (StatusRuntimeException e) {
            throw new StorageServiceUnavailableException("Error: Storage service unavailable.");
        }
    }
}
