package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.model.ServicesByDevices;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.service.ServiceCostService;
import com.ninjaone.backendinterviewproject.service.ServicesByDevicesService;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * REST controller for calculating total customer cost
 */
@RestController
@RequestMapping(CalculateCostsResource.REQUEST_URL)
public class CalculateCostsResource {

    public static final String REQUEST_URL = "/api/v1/calculate-cost/";

    private final ServicesByDevicesService service;

    private final ServiceCostService serviceCosts;


    @GetMapping("/{customer}")
    private BigDecimal findOne(@PathVariable String customer) {
        //TODO: if REQs changes filter by customer is necessary
        List<ServicesByDevices> services = service.findAll();
        return services.stream()
                .map(e-> Pair.of(e.getDevice(), serviceCosts.calculateCost(e.getService(), e.getDevice().getType())))
                .collect(
                        Collectors.groupingBy(
                                Pair::getFirst,
                                Collectors.reducing(BigDecimal.ZERO, Pair::getSecond, BigDecimal::add)
                        )
                ).entrySet()// cost of services by device
                .stream()
                .map(Map.Entry::getValue)
                .map(e -> e.add(new BigDecimal(4))) // add fixed cost by device
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public CalculateCostsResource(ServicesByDevicesService service,
                                  ServiceCostService servicesCosts) {
        this.service = service;
        this.serviceCosts = servicesCosts;
    }

}
