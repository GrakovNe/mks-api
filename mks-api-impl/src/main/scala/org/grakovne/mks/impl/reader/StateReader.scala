package org.grakovne.mks.impl.reader

import org.grakovne.mks.api.model.MachineState

import scala.language.higherKinds

trait StateReader[F[_]] {

  def readState(): F[MachineState]

}
