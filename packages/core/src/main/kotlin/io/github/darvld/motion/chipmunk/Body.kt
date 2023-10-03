package io.github.darvld.motion.chipmunk

import io.github.darvld.motion.chipmunk.Interop.functionHandle
import io.github.darvld.motion.chipmunk.Interop.voidFunctionHandle
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

/** Allocate and initialize a cpBody. */
public val bodyNew: MethodHandle = functionHandle(
  name = "cpBodyNew",
  returnType = ValueLayout.ADDRESS,
  /*mass=*/ValueLayout.JAVA_DOUBLE,
  /*moment=*/ValueLayout.JAVA_DOUBLE,
)

/** Destroy and Free a body. */
public val bodyFree: MethodHandle = voidFunctionHandle(
  name = "cpBodyFree",
  /*self=*/ValueLayout.ADDRESS,
)

/** Get the position of the body. */
public val bodyGetPosition: MethodHandle = functionHandle(
  name = "cpBodyGetPosition",
  returnType = VectorLayout,
  /*self=*/ValueLayout.ADDRESS,
)

/** Set the position of the body. */
public val bodySetPosition: MethodHandle = voidFunctionHandle(
  name = "cpBodySetPosition",
  /*self=*/ValueLayout.ADDRESS,
  /*pos=*/VectorLayout,
)

/** Get the velocity of the body. */
public val bodyGetVelocity: MethodHandle = functionHandle(
  name = "cpBodyGetVelocity",
  returnType = VectorLayout,
  /*self=*/ValueLayout.ADDRESS,
)