package org.grakovne.mks.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._
import org.grakovne.mks.api.MksApiService
import play.api.libs.ws.ahc.AhcWSComponents

class MksapiLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new MksApiApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new MksApiApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[MksApiService])
}

abstract class MksApiApplication(context: LagomApplicationContext)
    extends LagomApplication(context)
    with AhcWSComponents {

  override lazy val lagomServer: LagomServer =
    serverFor[MksApiService](wire[MksApiServiceImpl])

}
