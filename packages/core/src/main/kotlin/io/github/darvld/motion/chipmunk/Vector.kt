package io.github.darvld.motion.chipmunk

import java.lang.foreign.*
import java.lang.invoke.VarHandle

private const val VECTOR_FIELD_X = "x"
private const val VECTOR_FIELD_Y = "y"

/** Chipmunk's 2D vector type along with a handy 2D vector math lib. */
public val VectorLayout: StructLayout = MemoryLayout.structLayout(
  ValueLayout.JAVA_DOUBLE.withName(VECTOR_FIELD_X),
  ValueLayout.JAVA_DOUBLE.withName(VECTOR_FIELD_Y),
)

/** A [VarHandle] for a [VectorLayout]'s 'x' component, provided for convenience. */
public val VectorX: VarHandle = VectorLayout.varHandle(MemoryLayout.PathElement.groupElement(VECTOR_FIELD_X))

/** A [VarHandle] for a [VectorLayout]'s 'y' component, provided for convenience. */
public val VectorY: VarHandle = VectorLayout.varHandle(MemoryLayout.PathElement.groupElement(VECTOR_FIELD_Y))

/** Allocate a new [VectorLayout] and initialize it to the given values. */
@Suppress("nothing_to_inline")
public inline fun SegmentAllocator.vector(x: Double, y: Double): MemorySegment {
  return allocate(VectorLayout).also {
    VectorX.set(it, x)
    VectorY.set(it, y)
  }
}