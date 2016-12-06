/*
 * Copyright 2016-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.apple;

import com.facebook.buck.model.Flavor;
import com.facebook.buck.model.Flavored;
import com.facebook.buck.rules.AbstractDescriptionArg;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.BuildRuleResolver;
import com.facebook.buck.rules.Description;
import com.facebook.buck.rules.NoopBuildRule;
import com.facebook.buck.rules.SourcePathResolver;
import com.facebook.buck.rules.TargetGraph;
import com.facebook.infer.annotation.SuppressFieldNotInitialized;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.Files;

import java.nio.file.Path;

/**
 * Description for a scenekit_assets rule, which identifies a assets directory
 * for use with Apple's SceneKit.
 */
public class SceneKitAssetsDescription implements
    Description<SceneKitAssetsDescription.Arg>,
    Flavored {

  private static final String SCENEKIT_ASSETS_EXTENSION = "scnassets";

  @Override
  public Arg createUnpopulatedConstructorArg() {
    return new Arg();
  }

  @Override
  public <A extends Arg> BuildRule createBuildRule(
      TargetGraph targetGraph,
      BuildRuleParams params,
      BuildRuleResolver resolver,
      A args) {
    String extension = Files.getFileExtension(args.path.getFileName().toString());
    Preconditions.checkArgument(SCENEKIT_ASSETS_EXTENSION.equals(extension));

    return new NoopBuildRule(
        params,
        new SourcePathResolver(resolver));
  }

  @SuppressFieldNotInitialized
  public static class Arg extends AbstractDescriptionArg {
    public Path path;
  }

  @Override
  public boolean hasFlavors(ImmutableSet<Flavor> flavors) {
    return true;
  }
}
