package io.github.darvld.motion.chipmunk

import io.github.darvld.motion.chipmunk.Interop.functionHandle
import io.github.darvld.motion.chipmunk.Interop.voidFunctionHandle
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

/** Allocate and initialize a segment shape. */
public val segmentShapeNew: MethodHandle = functionHandle(
  name = "cpSegmentShapeNew",
  returnType = ValueLayout.ADDRESS,
  /*body=*/ValueLayout.ADDRESS,
  /*a=*/VectorLayout,
  /*b=*/VectorLayout,
  /*radius=*/ValueLayout.JAVA_DOUBLE,
)

/** Destroy and Free a shape. */
public val shapeFree: MethodHandle = voidFunctionHandle(
  name = "cpShapeFree",
  /*self=*/ValueLayout.ADDRESS,
)

/** Allocate and initialize a circle shape. */
public val circleShapeNew: MethodHandle = functionHandle(
  name = "cpCircleShapeNew",
  returnType = ValueLayout.ADDRESS,
  /*body=*/ValueLayout.ADDRESS,
  /*radius=*/ValueLayout.JAVA_DOUBLE,
  /*offset=*/VectorLayout,
)

/** Set the friction of this shape. */
public val shapeSetFriction: MethodHandle = voidFunctionHandle(
  name = "cpShapeSetFriction",
  /*self=*/ValueLayout.ADDRESS,
  /*friction=*/ValueLayout.JAVA_DOUBLE,
)