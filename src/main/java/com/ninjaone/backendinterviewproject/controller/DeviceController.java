package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.BaseResponse;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DeviceController.REQUEST_URL)
public class DeviceController {
    public static final String REQUEST_URL = "/device/";

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteServiceEntity(@PathVariable Long id) {
        deviceService.deleteEntity(id);
    }

    @GetMapping("/{id}")
    private ResponseEntity<BaseResponse<Device>> getDeviceEntity(@PathVariable Long id) {
        return deviceService.getEntity(id)
                .map(
                        value -> new ResponseEntity<>(new BaseResponse<>("Success.", value), HttpStatus.OK)
                )
                .orElseGet(
                        () -> new ResponseEntity<>(new BaseResponse<>("Device Not Found"), HttpStatus.NOT_FOUND)
                );
    }

    @PostMapping
    private ResponseEntity<BaseResponse<Device>> postSampleEntity(@RequestBody Device device) {
        if (device.getId() != null) {
            return new ResponseEntity<>(
                    new BaseResponse<>("Error creating device", device),
                    HttpStatus.BAD_REQUEST
            );
        }
        deviceService.saveEntity(device);
        return new ResponseEntity<>(
                new BaseResponse<>("Success.", device),
                HttpStatus.CREATED
        );
    }

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
}
