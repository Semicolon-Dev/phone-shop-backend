package dev.semicolon.phoneshop.phone.bootstrap;

import dev.semicolon.phoneshop.phone.entities.Phone;
import dev.semicolon.phoneshop.phone.mappers.PhoneMapper;
import dev.semicolon.phoneshop.phone.repositories.PhoneRepository;
import dev.semicolon.phoneshop.phone.services.fonoapi.FonoApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PhoneDataLoader implements CommandLineRunner {

    private final FonoApiService fonoApiService;
    private final PhoneRepository phoneRepository;
    private final PhoneMapper phoneMapper;

    @Override
    public void run(String... args) throws Exception {
        // TODO: Load phones of various brands
        List<Phone> phonesToSave = fonoApiService.getLatest("samsung", 100)
                .stream()
                .map(phoneMapper::map)
                .collect(Collectors.toList());

        phoneRepository.saveAll(phonesToSave);
    }

}
