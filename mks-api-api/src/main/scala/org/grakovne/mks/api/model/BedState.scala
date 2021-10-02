package org.grakovne.mks.api.model

import play.api.libs.json.Json

case class BedState(currentTemperature: Int, expectedTemperature: Int)

object BedState {
  implicit val format = Json.format[BedState]
}
