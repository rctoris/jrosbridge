package edu.wpi.rail.jrosbridge;

import java.util.List;
import java.util.Map;

import javax.websocket.ClientEndpoint;
import javax.websocket.ClientEndpointConfig;

@ClientEndpoint
public class ClientConfigurator extends ClientEndpointConfig.Configurator{

  @Override
  public void beforeRequest(Map<String, List<String>> headers) {

    super.beforeRequest(headers);
  }

}
