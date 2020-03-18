package dev.semicolon.phoneshop.phone.web.dtos;

import dev.semicolon.phoneshop.phone.entities.Phone;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhonesPageDto {

    @ApiModelProperty("Total number of records in the phone collection")
    private Long total;

    @ApiModelProperty("Currently displaying page")
    private Integer currentPage;

    @ApiModelProperty("Total number of pages")
    private Integer totalPages;

    @ApiModelProperty("Page contents")
    private Collection<Phone> phones;

}
