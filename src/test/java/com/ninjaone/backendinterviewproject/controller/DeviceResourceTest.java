package com.ninjaone.backendinterviewproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.BackendInterviewProjectApplication;
import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import com.ninjaone.backendinterviewproject.service.DeviceTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.ninjaone.backendinterviewproject.controller.DeviceResource.REQUEST_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BackendInterviewProjectApplication.class})
@WebMvcTest(DeviceResource.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class DeviceResourceTest {
    public static final Long ID = 12345L;
    private final ObjectMapper objectMapper;
    private final MockMvc mockMvc;

    private final DeviceRepository repository;

    @MockBean
    private final DeviceTypeService deviceTypeService;

    @MockBean
    private DeviceService service;

    private Device testEntity;

    @Autowired
    public DeviceResourceTest(ObjectMapper objectMapper,
                              MockMvc mockMvc,
                              DeviceRepository repository,
                              DeviceTypeService deviceTypeService) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
        this.repository = repository;
        this.deviceTypeService = deviceTypeService;
    }

    @BeforeEach
    void setup() {
        testEntity = Device.builder().id(ID).systemName("machine")
                .type(DeviceType.builder().typeName("MAC").build())
                .build();
    }

    @Test
    void getEntity() throws Exception {
        when(service.findOne(ID)).thenReturn(Optional.of(testEntity));

        mockMvc.perform(get(REQUEST_URL + ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.systemName", is("machine")));

        verify(service, VerificationModeFactory.times(1))
                .findOne(ID);
    }

    @Test
    void getEntity_InvalidID() throws Exception {
        mockMvc.perform(get(REQUEST_URL + 999L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Entity Not Found")));

        verify(service, VerificationModeFactory.times(1))
                .findOne(999L);
    }

    @Test
    void createEntity() throws Exception {
        when(service.save(any())).thenReturn(testEntity);
        testEntity.setId(null);
        String entityAsString = objectMapper.writeValueAsString(testEntity);
        mockMvc.perform(post(REQUEST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entityAsString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.type.typeName", is("MAC")))
                .andExpect(jsonPath("$.data.systemName", is("machine")));
    }

    @Test
    void createEntity_failsOnProvidedId() throws Exception {
        when(service.save(any())).thenReturn(testEntity);
        String entityAsString = objectMapper.writeValueAsString(testEntity);
        mockMvc.perform(post(REQUEST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entityAsString))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message", is("Error creating entity: Id must be null")));
    }

    @Test
    void createEntity_failsOnNonProvidedData() throws Exception {
        when(service.save(any())).thenReturn(testEntity);
        testEntity.setId(null);
        testEntity.setType(null);
        String entityAsString = objectMapper.writeValueAsString(testEntity);
        mockMvc.perform(post(REQUEST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entityAsString))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message", is("Invalid Device Type: Not Provided")));
    }

    @Test
    void deleteEntity() throws Exception {
        when(service.findOne(any())).thenReturn(Optional.of(testEntity));
        doNothing().when(service).delete(ID);
        mockMvc.perform(delete(REQUEST_URL + ID))
                .andExpect(status().isOk());
    }
}
