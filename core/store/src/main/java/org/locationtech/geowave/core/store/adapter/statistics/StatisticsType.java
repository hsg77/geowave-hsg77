/**
 * Copyright (c) 2013-2019 Contributors to the Eclipse Foundation
 *
 * <p> See the NOTICE file distributed with this work for additional information regarding copyright
 * ownership. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Apache License, Version 2.0 which accompanies this distribution and is
 * available at http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package org.locationtech.geowave.core.store.adapter.statistics;

import java.util.Arrays;
import org.locationtech.geowave.core.index.ByteArray;
import org.locationtech.geowave.core.index.persist.Persistable;
import org.locationtech.geowave.core.store.api.StatisticsQueryBuilder;

/**
 * This is a marker class extending ByteArrayId that additionally provides type checking with a
 * generic.
 *
 * @param <R> The type of statistic
 */
public abstract class StatisticsType<R, B extends StatisticsQueryBuilder<R, B>> extends ByteArray
    implements
    Persistable {
  private static final long serialVersionUID = 1L;

  public StatisticsType() {
    super();
  }

  public StatisticsType(final byte[] id) {
    super(id);
  }

  public StatisticsType(final String id) {
    super(id);
  }

  public abstract B newBuilder();

  @Override
  public byte[] toBinary() {
    return bytes;
  }

  @Override
  public void fromBinary(final byte[] bytes) {
    this.bytes = bytes;
  }

  @Override
  public boolean equals(final Object obj) {
    // If all we know is the name of the stat type,
    // but not the class we need to override equals on
    // the base statistics type so that the
    // class does not need to match
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof StatisticsType)) {
      return false;
    }
    final StatisticsType<?, ?> other = (StatisticsType<?, ?>) obj;
    return Arrays.equals(bytes, other.getBytes());
  }
}
