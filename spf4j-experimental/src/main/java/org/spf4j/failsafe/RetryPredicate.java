/*
 * Copyright (c) 2001-2017, Zoltan Farkas All Rights Reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Additionally licensed with:
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.spf4j.failsafe;

import java.util.concurrent.Callable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A retry predicate.
 * @author Zoltan Farkas
 */
public interface RetryPredicate<T, C extends Callable> {

  /**
   * Get the RetryDecision for the result value returned by Callable C.
   * @param value the operation result.
   * @param what the operation.
   * @return
   */
  @Nonnull
  RetryDecision<C> getDecision(@Nullable T value, @Nonnull C what);


  /**
   * Factory method for a predicate. Predicates can be stateful or not (default).
   * @return a new instance of predicate.
   */
  @Nonnull
  default RetryPredicate<T, C> newInstance() {
    return this;
  }


  /**
   * Simple predicate that does not retry anything.
   */
  RetryPredicate NORETRY = new RetryPredicate<Object, Callable<Object>>() {

    @Override
    public RetryDecision<Callable<Object>> getDecision(final Object value,
            final Callable<Object> what) {
      return RetryDecision.abort();
    }

  };

}