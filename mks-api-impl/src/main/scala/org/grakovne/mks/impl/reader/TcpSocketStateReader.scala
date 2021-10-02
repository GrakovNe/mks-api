package org.grakovne.mks.impl.reader
import java.io.DataInputStream
import java.io.PrintStream
import java.net.InetAddress
import java.net.Socket
import java.nio.charset.StandardCharsets
import java.time.Instant

import akka.stream.scaladsl.StreamConverters
import com.lightbend.lagom.scaladsl.pubsub.PubSubRegistry
import com.lightbend.lagom.scaladsl.pubsub.TopicId
import org.grakovne.mks.api.model.BedState
import org.grakovne.mks.api.model.MachineState

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future
import scala.io.BufferedSource

class TcpSocketStateReader(pubSub: PubSubRegistry) extends StateReader[Future] {

  private var socket: Socket = openConnection()

  override def readState(): Future[MachineState] =
    Future.successful({
      println(Instant.now)

      if (socket.isClosed || !socket.isConnected) {
        socket = openConnection()
      }

      lazy val in = new BufferedSource(socket.getInputStream()).getLines()
      val out = new PrintStream(socket.getOutputStream())

      out.println("M105\r\n")
      out.flush()


      val s: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()

      while (socket.getInputStream.available() > 0) {
        s.addOne(socket.getInputStream.read())
      }

      println(s.map(_.toChar).mkString(""))
      s.clear()

      MachineState(List.empty, BedState(100, 200), "in.readLine()")
    })

  private def openConnection(): Socket =
    new Socket(InetAddress.getByName("192.168.0.180"), 8080)
}
