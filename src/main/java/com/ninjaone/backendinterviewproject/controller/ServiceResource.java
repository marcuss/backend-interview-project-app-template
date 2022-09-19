package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.BaseResponse;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Objects;

/**
 * REST controller for managing {@link com.ninjaone.backendinterviewproject.model.Service}.
 */
@RestController
@RequestMapping(ServiceResource.REQUEST_URL)
public class ServiceResource {

    public static final String REQUEST_URL = "/api/v1/services/";

    private final ServiceService service;

    public ServiceResource(ServiceService service) {
        this.service = service;
    }

    /**
     * {@code POST}  {@link ServiceResource#REQUEST_URL} : Create a new service.
     *
     * @param entity the service to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new service,
     * or with status {@code 400 (Bad Request)} if the service has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping
    public ResponseEntity<BaseResponse<Service>> create(@RequestBody Service entity) {
        if (entity.getId() != null) {
            return new ResponseEntity<>(
                    new BaseResponse<>("Error creating entity: Id must be null", entity),
                    HttpStatus.BAD_REQUEST
            );
        }
        if (service.findByServiceName(entity.getServiceName()).isPresent()){
            return new ResponseEntity<>(
                    new BaseResponse<>("Error creating entity: can not be duplicated", entity),
                    HttpStatus.BAD_REQUEST
            );
        }
        try {
            return new ResponseEntity<>(
                    new BaseResponse<>("Success.", service.save(entity)),
                    HttpStatus.CREATED
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>(
                    new BaseResponse<>("Unexpected server error", entity),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    /**
     * {@code PUT}  {@link ServiceResource#REQUEST_URL}/:id : Updates an existing service.
     *
     * @param id     the id of the service to update.
     * @param entity the service to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with the body of the updated service if all is ok,
     * or with status {@code 400 (Bad Request)} if the service is not valid including a wrong device type,
     * or with status {@code 404 (Not Found)} if the service id is null or not found in DB,
     * or with status {@code 500 (Internal Server Error)} if the service couldn't be updated.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Service>> update(@PathVariable final Long id,
                                                 @RequestBody Service entity) {
        if (entity.getId() == null) {
            return new ResponseEntity<>(new BaseResponse<>("Invalid id: null"), HttpStatus.NOT_FOUND);
        }

        if (!Objects.equals(id, entity.getId())) {
            return new ResponseEntity<>(new BaseResponse<>("Can not modify the entity ID"), HttpStatus.CONFLICT);
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

    /**
     * {@code GET}  {@link ServiceResource#REQUEST_URL}/:id : get the "id" service.
     *
     * @param id the id of the service to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the service,
     * or with status {@code 404 (Not Found)} if the service id is null or not found in DB
     */
    @GetMapping("{id}")
    public ResponseEntity<BaseResponse<Service>> findOne(@PathVariable Long id) {
        return service.findOne(id)
                .map(
                        value -> new ResponseEntity<>(new BaseResponse<>("Success.", value), HttpStatus.OK)
                )
                .orElseGet(
                        () -> new ResponseEntity<>(new BaseResponse<>("Entity Not Found"), HttpStatus.NOT_FOUND)
                );
    }

    /**
     * {@code DELETE}  {@link ServiceResource#REQUEST_URL}/:id : delete the "id" service.
     *
     * @param id DB identifier of the desired device.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with the body of the found device if all is ok,
     * or with status {@code 404 (Not Found)} if the entity id is null or not found in DB
     */
    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse<Service>> delete(@PathVariable Long id) {
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
}
