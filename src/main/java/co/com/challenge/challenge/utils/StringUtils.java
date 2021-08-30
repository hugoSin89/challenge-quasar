package co.com.challenge.challenge.utils;

import co.com.challenge.challenge.models.SatellitesModel;
import co.com.challenge.challenge.models.SatellitesRequest;
import java.util.Arrays;
import java.util.Objects;

public class StringUtils {

  private StringUtils() {}

  public static final String BLANK_SPACE = " ";
  public static final String EMPTY = "";

  public static String processMessage(final SatellitesRequest request) {

    if(Arrays.stream(request.getSatellites()).anyMatch(Objects::isNull)) {
      return  null;
    }

    SatellitesModel[] satellitesItems = request.getSatellites();
    String[] messageList = new String[5];

    for(SatellitesModel item : satellitesItems) {
      for(int i=0; i< 5; i++) {
        if(!item.getMessage()[i].isEmpty()) {
          messageList[i] = item.getMessage()[i] + BLANK_SPACE;
        }
      }
    }

    for(int i=0; i< messageList.length; i++) {
      if(messageList[i] == null) {
        messageList[i] = "";
      }
    }

    return validateMessage(messageList);
  }

  private static String validateMessage(final String[] messageList) {

    return
        Arrays.asList(messageList).contains(BLANK_SPACE)
            ? null
            : String.join(" ", messageList).trim();
  }

}


