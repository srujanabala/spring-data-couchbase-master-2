/*
 * Copyright 2012-2015 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.data.couchbase.monitor;

import java.net.InetAddress;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.bucket.BucketInfo;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Exposes basic client information.
 *
 * @author Michael Nitschinger
 * @author Simon Baslé
 */
@ManagedResource(description = "Client Information")
public class ClientInfo {

  private final Bucket bucket;
  private final BucketInfo info;

  public ClientInfo(final Bucket bucket) {
    this.bucket = bucket;
    this.info = bucket.bucketManager().info();
  }

  @ManagedAttribute(description = "Hostnames of connected nodes")
  public String getHostNames() {
    StringBuilder result = new StringBuilder();
    for (InetAddress node : info.nodeList()) {
      result.append(node.toString()).append(",");
    }
    return result.toString();
  }

  @ManagedAttribute(description = "Number of connected nodes")
  public int getNumberOfNodes() {
    return info.nodeCount();
  }

  //TODO obtain count of available nodes vs unavailable ones and expose it

}
