package dev.semicolon.phoneshop.phone.services;

import dev.semicolon.phoneshop.phone.web.dtos.PhonesPageDto;

public interface PhoneService {

    PhonesPageDto getPhones(Integer page, Integer size);

}
