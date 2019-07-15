package com.onedsol.calculator.impl

import com.lightbend.lagom.scaladsl.server.LocalServiceLocator
import com.lightbend.lagom.scaladsl.testkit.ServiceTest
import com.onedsol.calculator.api.CalculatorService
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}

class CalculatorServiceSpec extends AsyncWordSpec with Matchers with BeforeAndAfterAll {
  private val server = ServiceTest.startServer(ServiceTest.defaultSetup) { ctx =>
    new CalculatorApplication(ctx) with LocalServiceLocator
  }

  val client = server.serviceClient.implement[CalculatorService]

  override protected def afterAll() = server.stop()

  "Hello service" should {
    "say hello" in {
      client.hello("Alice").invoke().map { answer =>
        answer should ===("Hello, Alice")
      }
    }
  }
}
