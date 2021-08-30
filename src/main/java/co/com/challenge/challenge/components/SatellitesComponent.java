package co.com.challenge.challenge.components;

import co.com.challenge.challenge.constant.SatellitesConstant;
import co.com.challenge.challenge.mappers.SatelliteMapper;
import co.com.challenge.challenge.models.LineEquation;
import co.com.challenge.challenge.models.PointModel;
import co.com.challenge.challenge.models.SatellitesModel;
import co.com.challenge.challenge.models.SatellitesRequest;
import co.com.challenge.challenge.models.SatellitesResponse;
import co.com.challenge.challenge.repository.SatelliteRepository;
import co.com.challenge.challenge.utils.MathUtils;
import co.com.challenge.challenge.utils.StringUtils;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SatellitesComponent {

  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SatellitesComponent.class);
  private static final String KENOBI = "kenobi";
  private static final String SKYWALKER = "skywalker";
  private static final String SATO = "sato";
  private final SatelliteRepository repository;
  private final SatelliteMapper mapper;

  @Autowired
  public SatellitesComponent(SatelliteRepository repository, SatelliteMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public SatellitesResponse getLocationInfo() {
    LOGGER.info("init get Location without parameters");
    SatellitesRequest request =
        SatellitesRequest.builder()
            .satellites(readStoredInformation())
            .build();
    return calculateLocation(request);
  }

  public SatellitesResponse getLocationInfo(SatellitesRequest request) {
    return calculateLocation(request);
  }

  private SatellitesResponse calculateLocation(final SatellitesRequest request) {
    LOGGER.info("init get Location with request: {}", request);
    final PointModel location = processLocation(request);
    final String message = getMessage(request);

    if(location == null || message == null) {
      return SatellitesResponse.builder().build();
    }

    return
        SatellitesResponse.builder()
            .position(location)
            .message(message)
            .build();
  }

  private String getMessage(final SatellitesRequest request) {
    return StringUtils.processMessage(request);
  }

  private SatellitesModel[] readStoredInformation() {
    LOGGER.info("init read stored information");
    SatellitesModel[] satellitesList = new SatellitesModel[3];

    if(repository.findById(KENOBI).isPresent()) {
      satellitesList[0] = mapper.buildSatellitesModel(repository.findById(KENOBI).get());
    }else{
      return new SatellitesModel[0];
    }
    if(repository.findById(SKYWALKER).isPresent()) {
      satellitesList[1] = mapper.buildSatellitesModel(repository.findById(SKYWALKER).get());
    }else{
      return new SatellitesModel[0];
    }
    if(repository.findById(SATO).isPresent()) {
      satellitesList[2] = mapper.buildSatellitesModel(repository.findById(SATO).get());
    }else{
      return new SatellitesModel[0];
    }
    return satellitesList;
  }

  private SatellitesModel readRequestInformation(
      final SatellitesRequest request,
      final String satelliteName) {

    if(Arrays.stream(request.getSatellites()).anyMatch(Objects::isNull)) {
      return  null;
    }

    final Optional<SatellitesModel> requestInfoOptional =
        Arrays.stream(request.getSatellites()).filter(
            item-> item.getName().equals(satelliteName))
            .findFirst();

    return requestInfoOptional.orElse(null);
  }

  private PointModel processLocation(final SatellitesRequest request) {
    LOGGER.info("init processLocation");

    final SatellitesModel inputKenobi = readRequestInformation(request,KENOBI);
    final SatellitesModel inputSkywalker = readRequestInformation(request,SKYWALKER);
    final SatellitesModel inputSato = readRequestInformation(request,SATO);

    if (validateSatelliteRequest(inputKenobi) ||
        validateSatelliteRequest(inputSkywalker) ||
        validateSatelliteRequest(inputSato)) {
      return null;
    }

    final PointModel pointKenobi =  SatellitesConstant.getKenobiLocation();
    final PointModel pointSkywalker =  SatellitesConstant.getSkywalkerLocation();
    final PointModel pointSato =  SatellitesConstant.getSatoLocation();

    LineEquation equation01 =
        MathUtils.circumferenceEquation(
            pointKenobi,
            inputKenobi.getDistance());

    LineEquation equation02 =
        MathUtils.circumferenceEquation(
            pointSato,
            inputSato.getDistance());

    LineEquation equation03 =
        MathUtils.circumferenceEquation(
            pointSkywalker,
            inputSkywalker.getDistance());

    LineEquation finalEquation =
        MathUtils.addEquations(equation01,equation02);

    LineEquation finalEquation2 =
        MathUtils.addEquations(equation01,equation03);
MathUtils.addEquations(equation01,equation02,equation03);

    MathUtils.sumEquations(finalEquation,finalEquation2);

    return
        MathUtils.findPointX(
            pointKenobi,inputKenobi.getDistance(),
            pointSkywalker,inputSkywalker.getDistance(),
            pointSato,inputSato.getDistance(),
            finalEquation2);
  }

  private boolean validateSatelliteRequest(SatellitesModel satelliteInfo) {
    return
        satelliteInfo == null;
  }

}


