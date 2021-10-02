package org.grakovne.mks.impl

import akka.Done
import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.ServiceCall
import org.grakovne.mks.api.GreetingMessage
import org.grakovne.mks.api.MksApiService

import scala.concurrent.Future

class MksApiServiceImpl extends MksApiService {
  override def hello(id: String): ServiceCall[NotUsed, String] =
    ServiceCall { _ => Future.successful("hello") }

}
