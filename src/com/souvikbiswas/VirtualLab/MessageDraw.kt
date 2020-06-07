package com.souvikbiswas.VirtualLab

import java.awt.*
import java.util.*
import javax.swing.JPanel

class MessageDraw(var dataStream: DataStream) : JPanel() {
    var listOfCoordinates: MutableList<DoubleArray> = ArrayList()
    fun generateMessage(maxWidth: Int, maxHeight: Int) {
        val message = dataStream.message
        println("The max height: $maxHeight")

        val eachSignalBitWidth = maxWidth / message!!.length.toDouble()
        println("Each Signal Bit Width: $eachSignalBitWidth")

        val startingPoint = DoubleArray(2)
        val endingPoint = DoubleArray(2)
        endingPoint[0] = 0.0
        endingPoint[1] = 0.0
        var previousBit = '0'
        var upp = false
        for (i in 0 until message.length) {
            val presentBit = message[i]
            upp = presentBit == '1'

            if (presentBit == previousBit) {
                startingPoint[0] = endingPoint[0]
                startingPoint[1] = endingPoint[1]
                endingPoint[0] = endingPoint[0] + eachSignalBitWidth

                val firstCoordinate = doubleArrayOf(startingPoint[0], startingPoint[1])
                val secondCoordinate = doubleArrayOf(endingPoint[0], endingPoint[1])

                listOfCoordinates.add(firstCoordinate)
                listOfCoordinates.add(secondCoordinate)
            } else {
                val bitWidth = if (upp) -eachSignalBitWidth else eachSignalBitWidth
                startingPoint[0] = endingPoint[0]
                startingPoint[1] = endingPoint[1]
                endingPoint[1] = endingPoint[1] + bitWidth

                val firstCoordinate = doubleArrayOf(startingPoint[0], startingPoint[1])
                val secondCoordinate = doubleArrayOf(endingPoint[0], endingPoint[1])

                listOfCoordinates.add(firstCoordinate)
                listOfCoordinates.add(secondCoordinate)
                
                startingPoint[0] = endingPoint[0]
                startingPoint[1] = endingPoint[1]
                endingPoint[0] = endingPoint[0] + eachSignalBitWidth

                val thirdCoordinate = doubleArrayOf(startingPoint[0], startingPoint[1])
                val fourthCoordinate = doubleArrayOf(endingPoint[0], endingPoint[1])

                listOfCoordinates.add(thirdCoordinate)
                listOfCoordinates.add(fourthCoordinate)
            }
            previousBit = presentBit
        }
        // repaint();
    }

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val g2 = g as Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

        val maxWidth = width
        val maxHeight = height

        generateMessage(maxWidth, maxHeight)

        g2.color = Color.BLACK
        g2.stroke = GRAPH_STROKE

        var counter = 0

        println("Size:" + listOfCoordinates.size)

        for (coordinate in listOfCoordinates) {
            val list = doubleArrayOf(coordinate[0], coordinate[1] + maxHeight * 0.8)

            listOfCoordinates[counter] = list

            println(listOfCoordinates[counter][0])
            println(listOfCoordinates[counter][1])

            counter++
        }
        println(listOfCoordinates.size)
        for (i in 1 until listOfCoordinates.size) {
            val x1 = listOfCoordinates[i - 1][0].toInt()
            val x2 = listOfCoordinates[i][0].toInt()
            val y1 = listOfCoordinates[i - 1][1].toInt()
            val y2 = listOfCoordinates[i][1].toInt()
            
            g2.drawLine(x1, y1, x2, y2)
        }
    }

    companion object {
        private const val serialVersionUID = 1L
        private val GRAPH_STROKE: Stroke = BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
    }

}