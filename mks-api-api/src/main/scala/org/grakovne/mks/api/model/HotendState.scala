package org.grakovne.mks.api.model

import play.api.libs.json.Json

case class HotendState(currentTemperature: Int, expectedTemperature: Int)

object HotendState {
  implicit val format = Json.format[HotendState]
}
