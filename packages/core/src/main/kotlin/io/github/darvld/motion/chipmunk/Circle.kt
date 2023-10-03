package io.github.darvld.motion.chipmunk

import io.github.darvld.motion.chipmunk.Interop.functionHandle
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

/**
 * Calculate the moment of inertia for a circle; r1 and r2 are the inner and outer diameters. A solid circle has
 * an inner diameter of 0.
 */
public val momentForCircle: MethodHandle = functionHandle(
  name = "cpMomentForCircle",
  returnType = ValueLayout.JAVA_DOUBLE,
  /*m=*/ValueLayout.JAVA_DOUBLE,
  /*r1=*/ValueLayout.JAVA_DOUBLE,
  /*r2=*/ValueLayout.JAVA_DOUBLE,
  /*offset=*/VectorLayout,
)