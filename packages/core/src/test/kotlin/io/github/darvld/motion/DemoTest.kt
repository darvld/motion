package io.github.darvld.motion

import io.github.darvld.motion.chipmunk.*
import org.junit.jupiter.api.Test
import java.lang.foreign.Arena

class DemoTest {
  @Test fun testDemo() {
    // preload the native lib
    Interop.initialize()

    Arena.openConfined().run {
      // allocate a zero vector and a gravity vector
      val zero = vector(0.0, 0.0)
      val gravity = vector(0.0, -100.0)

      // create a new empty space
      val space = spaceNew()
      spaceSetGravity(space, gravity)

      // Add a static line segment shape for the ground.
      // We'll make it slightly tilted so the ball will roll off.
      // We attach it to a static body to tell Chipmunk it shouldn't be movable.
      val ground = segmentShapeNew(spaceGetStaticBody(space), vector(-20.0, 5.0), vector(20.0, -5.0), 0.0)
      shapeSetFriction(ground, 1.0)
      spaceAddShape(space, ground)

      // Now let's make a ball that falls onto the line and rolls off.
      // First we need to make a cpBody to hold the physical properties of the object.
      // These include the mass, position, velocity, angle, etc. of the object.
      // Then we attach collision shapes to the cpBody to give it a size and shape.
      val radius = 5.0
      val mass = 1.0

      // The moment of inertia is like mass for rotation
      // Use the cpMomentFor*() functions to help you approximate it.
      val moment = momentForCircle(mass, 0, radius, zero)

      // The cpSpaceAdd*() functions return the thing that you are adding.
      // It's convenient to create and add an object in one line.
      val ballBody = spaceAddBody(space, bodyNew(mass, moment))
      bodySetPosition(ballBody, vector(0.0, 15.0))

      // Now we create the collision shape for the ball.
      // You can create multiple collision shapes that point to the same body.
      // They will all be attached to the body and move around to follow it.
      val ballShape = spaceAddShape(space, circleShapeNew(ballBody, radius, zero))
      shapeSetFriction(ballShape, 0.7)

      // Now that it's all set up, we simulate all the objects in the space by
      // stepping forward through time in small increments called steps.
      // It is *highly* recommended to use a fixed size time step.
      val timeStep = 1.0 / 60.0
      var time = 0.0
      while (time < 2) {
        val pos = bodyGetPosition(this, ballBody)
        val vel = bodyGetVelocity(this, ballBody)

        println(
          "Time is %.2f. ballBody is at (%.2f, %.2f). Its velocity is (%.2f, %.2f)".format(
            time,
            VectorX.get(pos) as Double,
            VectorY.get(pos) as Double,
            VectorX.get(vel) as Double,
            VectorY.get(vel) as Double
          )
        )

        spaceStep(space, timeStep)
        time += timeStep
      }

      shapeFree(ballShape)
      bodyFree(ballBody)
      shapeFree(ground)
      spaceFree(space)
    }
  }
}