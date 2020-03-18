package dev.semicolon.phoneshop.phone.mappers;

import dev.semicolon.phoneshop.phone.entities.Phone;
import dev.semicolon.phoneshop.phone.services.fonoapi.model.FonoPhone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mapper
public interface PhoneMapper {

    @Mapping(target = "price", qualifiedByName = "mapFonoPrice")
    Phone map(FonoPhone phone);

    default BigDecimal mapFonoPrice(String price) {
        final BigDecimal defaultValue = BigDecimal.valueOf(99.90);

        if (price == null) {
            return defaultValue;
        }

        Pattern p = Pattern.compile("[0-9]+([.,][0-9]+)");
        Matcher m = p.matcher(price);

        BigDecimal parsedPrice = null;
        if (m.find()) {
            String matchPrice = m.group();
            if (matchPrice.contains(",")) {
                matchPrice = matchPrice.replace(",", "");
                parsedPrice = new BigDecimal(matchPrice);
                parsedPrice = parsedPrice.multiply(new BigDecimal(0.019));
            } else {
                parsedPrice = new BigDecimal(matchPrice);
            }
            parsedPrice = parsedPrice.setScale(2, RoundingMode.CEILING);
        }

        return Optional.ofNullable(parsedPrice).orElse(defaultValue);
    }
}
