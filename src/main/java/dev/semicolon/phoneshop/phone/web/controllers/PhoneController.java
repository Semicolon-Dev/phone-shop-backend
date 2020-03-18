package dev.semicolon.phoneshop.phone.web.controllers;

import dev.semicolon.phoneshop.phone.services.PhoneService;
import dev.semicolon.phoneshop.phone.web.dtos.PhonesPageDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(PhoneController.BASE_URL)
public class PhoneController {

    public static final String BASE_URL = "/phones";
    public static final String DEFAULT_PAGE = "1";
    public static final String DEFAULT_SIZE = "10";

    private final PhoneService phoneService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a page of phones", notes = "Given the page number and the amount of phones per " +
            "page, the API will get the page-th chunk of the given size from the whole collection.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "No errors occurred. IF such a page exists, the results will " +
            "be available"),
            @ApiResponse(code = 400, message = "Bad page or size parameter value(s)"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public PhonesPageDto getAll(@Positive @RequestParam(defaultValue = DEFAULT_PAGE) Integer page,
                                @Positive @RequestParam(defaultValue = DEFAULT_SIZE) Integer size) {

        log.info("Listing phones");

        PhonesPageDto phonesPage = phoneService.getPhones(page, size);

        log.info("Returning page #{} with {} phones", page, phonesPage.getPhones().size());
        log.debug("Response: {}", phonesPage);

        return phonesPage;
    }

}
