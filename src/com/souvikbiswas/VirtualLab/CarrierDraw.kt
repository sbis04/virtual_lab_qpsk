package com.souvikbiswas.VirtualLab

import java.awt.*
import javax.swing.JPanel

class CarrierDraw(var dataStream: DataStream, frequency: Int, amplitude: Int) : JPanel() {
    private val amplitude: Double
    private val pts = IntArray(points)
    private val sines = DoubleArray(points)
    fun setCyclesSine(newCycles: Double) {
        for (i in 0 until points) {
            var radians: Double
            radians = Math.PI / points * i * 2 * newCycles
            sines[i] = Math.sin(radians)
        }
        repaint()
    }

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val g2 = g as Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        val maxWidth = width
        val hstep = maxWidth.toDouble() / points.toDouble()
        val maxHeight = height
        g2.color = Color.BLACK
        g2.stroke = GRAPH_STROKE

        // Sine signals
        for (i in 0 until points) {
            pts[i] = (amplitude * sines[i] * maxHeight / 2 * .25 + maxHeight / 2 * .8).toInt()
        }
        g2.color = Color.BLACK
        g2.stroke = GRAPH_STROKE
        for (i in 1 until points) {
            val x1 = ((i - 1) * hstep).toInt()
            val x2 = (i * hstep).toInt()
            val y1 = pts[i - 1]
            val y2 = pts[i]
            g2.drawLine(x1, y1, x2, y2)
        }
    }

    companion object {
        private const val serialVersionUID = 1L
        private const val points = 1000
        private val GRAPH_STROKE: Stroke = BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
    }

    init {
        this.amplitude = amplitude * 0.5
        for (i in 0 until points) {
            pts[i] = i
        }
        setCyclesSine(frequency.toDouble())
    }
}