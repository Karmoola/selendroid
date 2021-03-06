/*
 * Copyright 2012-2013 eBay Software Foundation and selendroid committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.selendroid.server.handler;

import io.selendroid.exceptions.AndroidDeviceException;
import io.selendroid.server.BaseSelendroidServerHandler;
import io.selendroid.server.Response;
import io.selendroid.server.model.SelendroidStandaloneDriver;

import java.util.logging.Logger;

import org.json.JSONException;
import io.selendroid.server.SelendroidResponse;
import org.webbitserver.HttpRequest;

public class DeleteSessionHandler extends BaseSelendroidServerHandler {
  private static final Logger log = Logger.getLogger(DeleteSessionHandler.class.getName());

  public DeleteSessionHandler(HttpRequest request, String mappedUri) {
    super(request, mappedUri);
  }

  @Override
  public Response handle() throws JSONException {
    log.info("delete session command");
    SelendroidStandaloneDriver driver = getSelendroidDriver();
    String sessionId = getSessionId();
    try {
      driver.stopSession(sessionId);
    } catch (AndroidDeviceException e) {
      log.severe("Error occured while stopping the emulator.");
    }

    return new SelendroidResponse(sessionId, "");
  }
}
