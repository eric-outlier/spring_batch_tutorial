package cmmcloud.enums.service;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SkeletonEnum {

  SKELETON("SKLETON");

  private final String skeleton;

  SkeletonEnum(String skeleton) {
    this.skeleton = skeleton;
  }

  public String getSkeleton() {
    return skeleton;
  }

  private static Map<String, SkeletonEnum> ByName = Stream.of(values())
      .collect(Collectors.toMap(SkeletonEnum::getSkeleton, e -> e));

  public static SkeletonEnum valueOfName(String skeleton) {
    return ByName.get(skeleton);
  }

}
