package dev.semicolon.phoneshop.phone.services.impl;

import dev.semicolon.phoneshop.phone.entities.Phone;
import dev.semicolon.phoneshop.phone.repositories.PhoneRepository;
import dev.semicolon.phoneshop.phone.web.dtos.PhonesPageDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class PhoneServiceImplTest {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneServiceImpl phoneService;

    @Test
    public void getPhones_should_return_a_page_of_phones() {
        // given
        int page = 1;
        int size = 10;
        List<Phone> phones = listOfPhones(size);

        // when
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        when(phoneRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(phones));

        // then
        PhonesPageDto result = phoneService.getPhones(page, size);

        assertThat(result.getPhones(), is(equalTo(phones)));
        assertThat(result.getCurrentPage(), is(equalTo(1)));
        assertThat(result.getTotalPages(), is(equalTo(1)));
        assertThat(result.getTotal(), is(equalTo((long) phones.size())));

        verify(phoneRepository, times(1)).findAll(pageRequest);
        verifyNoMoreInteractions(phoneRepository);
    }

    @Test
    public void getPhones_should_return_no_results_when_page_is_above_size_partitions() {
        // given
        int page = 2;
        int size = 10;

        // when
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        when(phoneRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(Collections.emptyList()));

        // then
        PhonesPageDto result = phoneService.getPhones(page, size);

        assertThat(result.getPhones().isEmpty(), is(equalTo(true)));
        assertThat(result.getCurrentPage(), is(equalTo(2)));

        verify(phoneRepository, times(1)).findAll(pageRequest);
        verifyNoMoreInteractions(phoneRepository);
    }

    private List<Phone> listOfPhones(int size) {
        List<Phone> phones = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            phones.add(Phone.builder()
                    .id((long) i)
                    .name("Phone " + i)
                    .price(new BigDecimal("99.99"))
                    .build());
        }
        return phones;
    }

}