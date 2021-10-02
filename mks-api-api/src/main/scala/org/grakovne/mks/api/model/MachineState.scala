package org.grakovne.mks.api.model

import play.api.libs.json.Json

//T:210 /210 B:70 /70 T0:210 /210 T1:0 /0 @:0 B@:0
case class MachineState(hotends: List[HotendState], bed: BedState, raw: String)

object MachineState {
  implicit val format = Json.format[MachineState]
}
