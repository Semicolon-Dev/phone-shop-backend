package dev.semicolon.phoneshop.phone.services.impl;

import dev.semicolon.phoneshop.phone.entities.Phone;
import dev.semicolon.phoneshop.phone.repositories.PhoneRepository;
import dev.semicolon.phoneshop.phone.services.PhoneService;
import dev.semicolon.phoneshop.phone.web.dtos.PhonesPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    @Override
    public PhonesPageDto getPhones(Integer page, Integer size) {
        Page<Phone> phonesPage = phoneRepository.findAll(PageRequest.of(page - 1, size));
        return PhonesPageDto.builder()
                .phones(phonesPage.getContent())
                .currentPage(page)
                .totalPages(phonesPage.getTotalPages())
                .total(phonesPage.getTotalElements())
                .build();
    }

}
