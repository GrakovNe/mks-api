package org.grakovne.mks.impl

import akka.Done
import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.ServiceCall
import org.grakovne.mks.api.MksApiService
import org.grakovne.mks.api.model.BedState
import org.grakovne.mks.api.model.MachineState
import org.grakovne.mks.impl.reader.StateReader

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

class MksApiServiceImpl(stateReader: StateReader[Future])
    extends MksApiService {
  override def fetchState: ServiceCall[NotUsed, Source[MachineState, NotUsed]] =
    ServiceCall { _ =>
      Future.successful(
        Source
          .tick(0.second, 3.second, NotUsed)
          .mapMaterializedValue(_ => NotUsed)
          .mapAsync(1)(_ => stateReader.readState())
      )
    }

  override def fetchRawState: ServiceCall[NotUsed, String] = ???
}
