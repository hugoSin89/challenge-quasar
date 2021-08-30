package co.com.challenge.challenge.mappers;

import co.com.challenge.challenge.entity.SatelliteEntity;
import co.com.challenge.challenge.models.SatellitesModel;
import org.springframework.stereotype.Component;

@Component
public class SatelliteMapper {


    public SatellitesModel buildSatellitesModel(final SatelliteEntity entity) {
        return
            SatellitesModel.builder()
                .name(entity.getSatelliteName())
                .build();
    }

}
