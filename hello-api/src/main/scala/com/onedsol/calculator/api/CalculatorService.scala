package com.onedsol.calculator.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

trait CalculatorService extends Service {
  def compute(expresion: String): ServiceCall[NotUsed, String]

  override final def descriptor = {
    import Service._
    // @formatter:off
    named("compute")
      .withCalls(
        pathCall("/api/compute/:expresion", compute _)
      )
      .withAutoAcl(true)
    // @formater:on
  }
}
