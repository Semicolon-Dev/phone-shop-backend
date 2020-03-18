package dev.semicolon.phoneshop.phone.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.semicolon.phoneshop.phone.entities.Phone;
import dev.semicolon.phoneshop.phone.services.PhoneService;
import dev.semicolon.phoneshop.phone.web.dtos.PhonesPageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PhoneController.class)
class PhoneControllerTest {

    @MockBean
    private PhoneService phoneService;

    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAll_should_return_requested_page_with_requested_size() throws Exception {
        // given
        int page = 2;
        int size = 1;
        PhonesPageDto expectedResponse = buildPhonesPageDto(page, size);

        // when
        when(phoneService.getPhones(page, size)).thenReturn(expectedResponse);

        // then
        mockMvc.perform(get(PhoneController.BASE_URL)
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonMapper.writeValueAsString(expectedResponse)));

        verify(phoneService, times(1)).getPhones(page, size);
        verifyNoMoreInteractions(phoneService);
    }

    @Test
    public void getAll_should_use_default_paging_params_when_none_are_provided() throws Exception {
        // given
        int page = Integer.parseInt(PhoneController.DEFAULT_PAGE);
        int size = Integer.parseInt(PhoneController.DEFAULT_SIZE);
        PhonesPageDto expectedResponse = buildPhonesPageDto(page, size);

        // when
        when(phoneService.getPhones(page, size)).thenReturn(expectedResponse);

        // then
        mockMvc.perform(get(PhoneController.BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonMapper.writeValueAsString(expectedResponse)));

        verify(phoneService, times(1)).getPhones(page, size);
        verifyNoMoreInteractions(phoneService);
    }

    @Test
    public void getAll_should_return_bad_request_when_page_param_is_less_than_one() throws Exception {
        mockMvc.perform(get(PhoneController.BASE_URL)
                .param("page", "0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAll_should_return_bad_request_when_size_param_is_less_than_one() throws Exception {
        mockMvc.perform(get(PhoneController.BASE_URL)
                .param("size", "0"))
                .andExpect(status().isBadRequest());
    }

    private PhonesPageDto buildPhonesPageDto(int page, int listSize) {
        List<Phone> phones = new ArrayList<>(listSize);

        for (int i = 0; i < listSize; i++) {
            phones.add(Phone.builder()
                    .id((long) i)
                    .name("Phone " + i)
                    .price(new BigDecimal("99.99"))
                    .build());
        }

        return PhonesPageDto.builder()
                .phones(phones)
                .currentPage(page)
                .build();
    }

}