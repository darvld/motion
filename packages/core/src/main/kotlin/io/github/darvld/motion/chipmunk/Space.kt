package io.github.darvld.motion.chipmunk

import io.github.darvld.motion.chipmunk.Interop.functionHandle
import io.github.darvld.motion.chipmunk.Interop.voidFunctionHandle
import java.lang.foreign.ValueLayout
import java.lang.invoke.MethodHandle

/** Allocate and initialize a cpSpace. */
public val spaceNew: MethodHandle = functionHandle(
  name = "cpSpaceNew",
  returnType = ValueLayout.ADDRESS
)

/** Destroy and Free a shape. */
public val spaceFree: MethodHandle = voidFunctionHandle(
  name = "cpSpaceFree",
  /*self=*/ValueLayout.ADDRESS,
)

/** Gravity to pass to rigid bodies when integrating velocity. */
public val spaceGetGravity: MethodHandle = functionHandle(
  name = "cpSpaceGetGravity",
  returnType = VectorLayout,
  /*self=*/ValueLayout.ADDRESS,
)

/** Gravity to pass to rigid bodies when integrating velocity. */
public val spaceSetGravity: MethodHandle = voidFunctionHandle(
  name = "cpSpaceSetGravity",
  /*self=*/ValueLayout.ADDRESS,
  /*gravity=*/VectorLayout,
)

/**
 * The Space provided static body for a given cpSpace. This is merely provided for convenience and you are not
 * required to use it.
 */
public val spaceGetStaticBody: MethodHandle = functionHandle(
  name = "cpSpaceGetStaticBody",
  returnType = ValueLayout.ADDRESS,
  /*self=*/ValueLayout.ADDRESS,
)

public val spaceAddShape: MethodHandle = functionHandle(
  name = "cpSpaceAddShape",
  returnType = ValueLayout.ADDRESS,
  /*self=*/ValueLayout.ADDRESS,
  /*shape=*/ValueLayout.ADDRESS,
)

/** Add a rigid body to the simulation. */
public val spaceAddBody: MethodHandle = functionHandle(
  name = "cpSpaceAddBody",
  returnType = ValueLayout.ADDRESS,
  /*self=*/ValueLayout.ADDRESS,
  /*body=*/ValueLayout.ADDRESS,
)

/** Step the space forward in time by dt. */
public val spaceStep: MethodHandle = voidFunctionHandle(
  name = "cpSpaceStep",
  /*self=*/ValueLayout.ADDRESS,
  /*dt=*/ValueLayout.JAVA_DOUBLE
)