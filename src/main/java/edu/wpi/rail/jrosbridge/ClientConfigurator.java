package edu.wpi.rail.jrosbridge;

import java.util.List;
import java.util.Map;

import javax.websocket.ClientEndpoint;
import javax.websocket.ClientEndpointConfig;

@ClientEndpoint
public class ClientConfigurator extends ClientEndpointConfig.Configurator{

  @Override
  public void beforeRequest(Map<String, List<String>> headers) {
    String origin = headers.get("Origin").get(0);
    //headers.put("Origin", Arrays.asList("ws://"+origin));
    
    super.beforeRequest(headers);
  }

}
