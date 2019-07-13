package com.onedsol.calculator.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.onedsol.calculator.api.CalculatorService

import scala.concurrent.{ExecutionContext, Future}

class CalculatorServiceImpl
  (implicit ec: ExecutionContext) extends CalculatorService {
  override def compute(expresion: String) = ServiceCall { _ =>
    Future { "Computed: " + Calculator.main(expresion) }
  }
}
