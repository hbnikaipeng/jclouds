/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.trmk.vcloud_0_8.internal;

import static org.jclouds.rest.RestContextFactory.contextSpec;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;

import org.jclouds.http.HttpRequest;
import org.jclouds.http.functions.ParseSax;
import org.jclouds.rest.RestClientTest;
import org.jclouds.rest.RestContextSpec;
import org.jclouds.rest.internal.RestAnnotationProcessor;
import org.jclouds.trmk.vcloud_0_8.xml.SupportedVersionsHandler;
import org.testng.annotations.Test;

import com.google.inject.TypeLiteral;

/**
 * Tests behavior of {@code VCloudVersionsAsyncClient}
 * 
 * @author Adrian Cole
 */
// NOTE:without testName, this will not call @Before* and fail w/NPE during surefire
@Test(groups = "unit", testName = "VCloudVersionsAsyncClientTest")
public class TerremarkVCloudVersionsAsyncClientTest extends RestClientTest<TerremarkVCloudVersionsAsyncClient> {

   public void testVersions() throws SecurityException, NoSuchMethodException, IOException {
      Method method = TerremarkVCloudVersionsAsyncClient.class.getMethod("getSupportedVersions");
      HttpRequest request = processor.createRequest(method);

      assertEquals(request.getRequestLine(), "GET http://localhost:8080/versions HTTP/1.1");
      assertNonPayloadHeadersEqual(request, "");
      assertPayloadEquals(request, null, null, false);

      assertResponseParserClassEquals(method, request, ParseSax.class);
      assertSaxResponseParserClassEquals(method, SupportedVersionsHandler.class);
      assertExceptionParserClassEquals(method, null);

      checkFilters(request);
   }

   @Override
   protected void checkFilters(HttpRequest request) {
      assertEquals(request.getFilters().size(), 0);
   }

   @Override
   protected TypeLiteral<RestAnnotationProcessor<TerremarkVCloudVersionsAsyncClient>> createTypeLiteral() {
      return new TypeLiteral<RestAnnotationProcessor<TerremarkVCloudVersionsAsyncClient>>() {
      };
   }

   @Override
   public RestContextSpec<TerremarkVCloudVersionsClient, TerremarkVCloudVersionsAsyncClient> createContextSpec() {
      return contextSpec("test", "http://localhost:8080", "1", "", "", "identity", "credential",
               TerremarkVCloudVersionsClient.class, TerremarkVCloudVersionsAsyncClient.class);
   }

}
