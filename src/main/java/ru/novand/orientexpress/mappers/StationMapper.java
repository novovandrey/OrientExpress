package ru.novand.orientexpress.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.novand.orientexpress.domain.dto.StationDTO;
import ru.novand.orientexpress.domain.entities.Station;

@Mapper
public interface StationMapper {

    StationMapper INSTANCE = Mappers.getMapper( StationMapper.class );

    @Mappings({
            @Mapping(source = "idStation", target = "id"),
            @Mapping(source = "stationname", target = "stationname")
    })
    StationDTO stationToStationDTO(Station station);

}
