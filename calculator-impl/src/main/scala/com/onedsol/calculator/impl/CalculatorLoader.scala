package com.onedsol.calculator.impl

import com.lightbend.lagom.scaladsl.client.ConfigurationServiceLocatorComponents
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomApplicationLoader}
import com.onedsol.calculator.api.CalculatorService
import play.api.libs.ws.ahc.AhcWSComponents
import play.api.mvc.EssentialFilter
import play.filters.cors.CORSComponents

class CalculatorLoader extends LagomApplicationLoader {
  override def load(context: LagomApplicationContext): LagomApplication =
    new CalculatorApplication(context) with ConfigurationServiceLocatorComponents

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new CalculatorApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[CalculatorService])
}

abstract class CalculatorApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents
    with CORSComponents
{
  override lazy val httpFilters: Seq[EssentialFilter] = Seq(corsFilter) // 2) enable it
  override lazy val lagomServer = serverFor[CalculatorService](new CalculatorServiceImpl)
}
