package io.github.darvld.motion.chipmunk

import java.lang.foreign.FunctionDescriptor
import java.lang.foreign.Linker
import java.lang.foreign.MemoryLayout
import java.lang.foreign.SymbolLookup
import java.lang.invoke.MethodHandle
import java.util.concurrent.atomic.AtomicBoolean

internal object Interop {
  /** Internal thread-safe init flag. */
  private val initialized = AtomicBoolean()

  /**
   * Loads the required native libraries. This method is thread-safe, and is inert after the first call, so there is
   * no need for manual synchronization or state-keeping.
   */
  fun initialize() {
    // only run once across all threads
    if (!initialized.compareAndSet(false, true)) return
    System.loadLibrary("chipmunk")
  }

  /** Lazy [Linker] instance. */
  internal val linker: Linker by lazy {
    // make sure the library is loaded
    initialize()
    
    Linker.nativeLinker()
  }

  /** Lazy [SymbolLookup] instance. */
  internal val lookup: SymbolLookup by lazy {
    // make sure the library is loaded
    initialize()

    // default lookup using libraries imported by 'loadLibrary'
    SymbolLookup.loaderLookup()
  }

  internal fun voidFunctionHandle(name: String, vararg argumentTypes: MemoryLayout): MethodHandle {
    return functionHandle(name, FunctionDescriptor.ofVoid(*argumentTypes))
  }

  internal fun functionHandle(
    name: String,
    returnType: MemoryLayout,
    vararg argumentTypes: MemoryLayout,
  ): MethodHandle {
    return functionHandle(name, FunctionDescriptor.of(returnType, *argumentTypes))
  }

  internal fun functionHandle(name: String, descriptor: FunctionDescriptor): MethodHandle {
    return lookup.find(name).map { address ->
      linker.downcallHandle(address, descriptor)
    }.orElse(null) ?: error("Failed to load native function '$name'")
  }
}