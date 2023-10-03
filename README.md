# Motion

Kotlin bindings for the [Chipmunk](https://chipmunk-physics.net/) physics engine
using [Panama](https://openjdk.java.net/projects/panama/).

> [!IMPORTANT]
> This project is highly experimental and is not intended to be used in production.

The [`core`](packages/core) package is designed to be as low-level as possible, closely resembling the original C API.
On top of that low-level code more idiomatic abstractions can be built, such as light wrappers using value classes.

## Building

A recent JDK is required to build the project (due to Panama), [JDK 21](https://openjdk.java.net) is recommended.

## Shared library dependencies

Currently, libchipmunk is not bundled with this project, and must be present in the user's `/usr/lib` directory in order
for it to be picked up correctly; this will soon change.