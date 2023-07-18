/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.iotdb.db.queryengine.plan.analyze.cache.schema.lastcache.value;

import org.apache.iotdb.tsfile.read.TimeValuePair;
import org.apache.iotdb.tsfile.utils.TsPrimitiveType;

public class LastCacheValue implements ILastCacheValue {

  private long timestamp;

  private TsPrimitiveType value;

  public LastCacheValue(long timestamp, TsPrimitiveType value) {
    this.timestamp = timestamp;
    this.value = value;
  }

  @Override
  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public TsPrimitiveType getValue() {
    return value;
  }

  @Override
  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public void setValue(TsPrimitiveType value) {
    this.value = value;
  }

  @Override
  public TimeValuePair getTimeValuePair() {
    return new TimeValuePair(timestamp, value);
  }

  /**
   * Total basic 24B
   *
   * <ul>
   *   <li>LastCacheContainer Object header, 8B
   *   <li>long timestamp, 8B
   *   <li>TsPrimitiveType reference, 8B
   * </ul>
   */
  @Override
  public int estimateSize() {
    return 24 + (value == null ? 0 : value.getSize());
  }
}
