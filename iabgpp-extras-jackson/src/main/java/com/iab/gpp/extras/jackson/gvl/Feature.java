package com.iab.gpp.extras.jackson.gvl;

import java.util.List;
import java.util.Optional;

/*-
 * #%L
 * IAB TCF Java GVL Jackson
 * %%
 * Copyright (C) 2020 IAB Technology Laboratory, Inc
 * %%
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
 * #L%
 */

/*
 * List of Features the Vendor may utilize when performing some declared Purposes processing
 */
public class Feature implements com.iab.gpp.extras.gvl.Feature {

  private int id;
  private String name;
  private String description;
  private String descriptionLegal;
  private List<String> illustrations;

  /**
   * A feature id
   *
   * @return feature id
   */
  @Override
  public int getId() {
    return id;
  }

  /**
   * Name of the feature
   *
   * @return feature name string
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Description of the feature
   *
   * @return feature description string
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Legal description of the feature
   *
   * @return legal description string
   */
  @Override
  public Optional<String> getDescriptionLegal() {
    return Optional.ofNullable(descriptionLegal);
  }

  /**
   * illustrations
   *
   * @return illustrations
   */
  @Override
  public Optional<List<String>> getIllustrations() {
    return Optional.ofNullable(illustrations);
  }
}
