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

package org.springframework.data.couchbase.core.query;

import com.couchbase.client.java.query.consistency.ScanConsistency;
import com.couchbase.client.java.view.Stale;

/**
 * Enumeration of different consistency configurations to be used by the queries generated by the
 * framework.
 *
 * Each consistency can be translated to a {@link Stale} (for the {@link #viewConsistency() views})
 * and {@link ScanConsistency} (for the {@link #n1qlConsistency() N1QL queries}).
 *
 * @author Simon Baslé
 */
public enum Consistency {

  /** READ_YOUR_OWN_WRITES is {@link Stale#FALSE} and {@link ScanConsistency#STATEMENT_PLUS} */
  READ_YOUR_OWN_WRITES(Stale.FALSE, ScanConsistency.STATEMENT_PLUS),
  /** STRONGLY_CONSISTENT is {@link Stale#FALSE} and {@link ScanConsistency#REQUEST_PLUS} */
  STRONGLY_CONSISTENT(Stale.FALSE, ScanConsistency.REQUEST_PLUS),
  /** UPDATE_AFTER is {@link Stale#UPDATE_AFTER} and {@link ScanConsistency#NOT_BOUNDED} */
  UPDATE_AFTER(Stale.UPDATE_AFTER, ScanConsistency.NOT_BOUNDED),
  /** EVENTUALLY_CONSISTENT is {@link Stale#TRUE} and {@link ScanConsistency#NOT_BOUNDED} */
  EVENTUALLY_CONSISTENT(Stale.TRUE, ScanConsistency.NOT_BOUNDED);

  /**
   * The static default Consistency ({@link #READ_YOUR_OWN_WRITES}).
   */
  public static final Consistency DEFAULT_CONSISTENCY = READ_YOUR_OWN_WRITES;

  private final Stale viewConsistency;
  private final ScanConsistency n1qlConsistency;

  Consistency(Stale viewConsistency, ScanConsistency n1qlConsistency) {
    this.viewConsistency = viewConsistency;
    this.n1qlConsistency = n1qlConsistency;
  }

  /**
   * Returns the {@link Stale view consistency} corresponding to this {@link Consistency}.
   *
   * @return the view consistency.
   */
  public Stale viewConsistency() {
    return viewConsistency;
  }

  /**
   * Returns the {@link ScanConsistency N1QL consistency} corresponding to this {@link Consistency}.
   *
   * @return the N1QL consistency.
   */
  public ScanConsistency n1qlConsistency() {
    return n1qlConsistency;
  }
}
