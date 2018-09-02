package ru.novand.orientexpress.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.novand.orientexpress.domain.dto.ScheduleDTO;
import ru.novand.orientexpress.domain.entities.TrainTariff;

@Mapper
public interface TrainTariffToScheduleDTOMapper {

    TrainTariffToScheduleDTOMapper INSTANCE = Mappers.getMapper( TrainTariffToScheduleDTOMapper.class );

    @Mappings({
            @Mapping(source = "train.trainCode", target = "traincode"),
            @Mapping(source = "arrivalstation.stationname", target = "depstationname"),
            @Mapping(source = "departurestation.stationname", target = "arrstationname"),
            @Mapping(source = "tariff", target = "doubleIterator")
    })

    ScheduleDTO trainTariffToScheduleDTO(TrainTariff trainTariff);

}
