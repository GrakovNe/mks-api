package org.grakovne.mks.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.Descriptor
import com.lightbend.lagom.scaladsl.api.Service
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.deser.MessageSerializer._
import org.grakovne.mks.api.model.MachineState

trait MksApiService extends Service {

  def fetchState: ServiceCall[NotUsed, Source[MachineState, NotUsed]]
  def fetchRawState: ServiceCall[NotUsed, String]

  override final def descriptor: Descriptor = {
    import Service._
    named("mks-api")
      .withCalls(
        pathCall("/api/v1/state", fetchState _),
        pathCall("/api/v1/state/raw", fetchRawState _)
      )
      .withAutoAcl(true)
  }
}
