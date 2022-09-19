package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.BaseResponse;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.service.DeviceTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;


/**
 * REST controller for managing {@link com.ninjaone.backendinterviewproject.model.Device}.
 */
@RestController
@RequestMapping(DeviceResource.REQUEST_URL)
public class DeviceResource {

    public static final String REQUEST_URL = "/api/v1/devices/";
    private final DeviceService service;
    private final DeviceTypeService deviceTypeService;

    public DeviceResource(DeviceService service, DeviceTypeService deviceTypeService) {
        this.service = service;
        this.deviceTypeService = deviceTypeService;
    }

    /**
     * {@code POST}  {@link DeviceResource#REQUEST_URL}: Creates a new device.
     *
     * @param entity the Device to be created
     * @return the {@link ResponseEntity} with status {@code 201 (CREATED)} and the body of the created device,
     * or with status {@code 404 (Not Found)} if the device id is NOT null since it will be autogenerated,
     * or with status {@code 500 (Internal Server Error)} if something goes unexpectedly wrong
     */
    @PostMapping
    private ResponseEntity<BaseResponse<Device>> create(@RequestBody Device entity) {
        if (entity.getId() != null) {
            return new ResponseEntity<>(
                    new BaseResponse<>("Error creating device: Id must be null", entity),
                    HttpStatus.BAD_REQUEST
            );
        }
        if (entity.getType() != null) {
            Optional<DeviceType> deviceType = deviceTypeService.findOneByName(entity.getType().getTypeName());
            deviceType.ifPresentOrElse(
                    value -> entity.setType(value),
                    () -> entity.setType(deviceTypeService.save(entity.getType()))
            );
        }
        else {
            return new ResponseEntity<>(new BaseResponse<>("Invalid Device Type: Not Provided"), HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(
                    new BaseResponse<>("Success.", service.save(entity)),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new BaseResponse<>("Unexpected server error", entity),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    /**
     * {@code DELETE}  {@link DeviceResource#REQUEST_URL}/{id} : deletes an existing device.
     *
     * @param id DB identifier of the desired device.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with the body of the found device if all is ok,
     * or with status {@code 404 (Not Found)} if the entity id is null or not found in DB
     */
    @DeleteMapping("/{id}")
    private ResponseEntity<BaseResponse<Device>> delete(@PathVariable Long id) {
        return service.findOne(id)
                .map(value ->
                        {
                            service.delete(id);
                            return new ResponseEntity<>(new BaseResponse<>("Success.", value), HttpStatus.OK);
                        }
                )
                .orElseGet(
                        () -> new ResponseEntity<>(new BaseResponse<>("Device Not Found"), HttpStatus.NOT_FOUND)
                );
    }

    /**
     * {@code GET}  {@link DeviceResource#REQUEST_URL}/{id} : get an existing device.
     *
     * @param id DB identifier of the desired device.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with the body of the found device, if all is ok
     * or with status {@code 404 (Not Found)} if the device id is null or not found in DB
     */
    @GetMapping("/{id}")
    private ResponseEntity<BaseResponse<Device>> findOne(@PathVariable Long id) {
        return service.findOne(id)
                .map(
                        value -> new ResponseEntity<>(new BaseResponse<>("Success.", value), HttpStatus.OK)
                )
                .orElseGet(
                        () -> new ResponseEntity<>(new BaseResponse<>("Entity Not Found"), HttpStatus.NOT_FOUND)
                );
    }

    /**
     * {@code PUT} {@link DeviceResource#REQUEST_URL}/{id} : Updates an existing device.
     *
     * @param id     the id of the device to update.
     * @param entity the device to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with the body of the updated device if all is ok,
     * or with status {@code 400 (Bad Request)} if the device is not valid including a wrong device type,
     * or with status {@code 404 (Not Found)} if the device id is null or not found in DB,
     * or with status {@code 500 (Internal Server Error)} if the device couldn't be updated.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Device>> update(@PathVariable final Long id,
                                                       @RequestBody Device entity) {
        if (entity.getId() == null) {
            return new ResponseEntity<>(new BaseResponse<>("Invalid id: null"), HttpStatus.NOT_FOUND);
        }

        if (entity.getType() != null) {
            Optional<DeviceType> deviceType = deviceTypeService.findOneByName(entity.getType().getTypeName());
            entity.setType(deviceType.orElseThrow(() -> new IllegalArgumentException("Device Type Not Found")));
        } else {
            return new ResponseEntity<>(new BaseResponse<>("Invalid Device Type: Not Provided"), HttpStatus.BAD_REQUEST);
        }

        if (!Objects.equals(id, entity.getId())) {
            return new ResponseEntity<>(new BaseResponse<>("Can not modify entity ID"), HttpStatus.CONFLICT);
        }

        if (!service.existsById(id)) {
            return new ResponseEntity<>(new BaseResponse<>("Invalid id: Not Found"), HttpStatus.NOT_FOUND);
        }

        try {
            return new ResponseEntity<>(new BaseResponse<>("Success.", service.save(entity)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseResponse<>("Unexpected Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
