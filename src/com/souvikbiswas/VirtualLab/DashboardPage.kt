package com.souvikbiswas.VirtualLab

import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import java.io.IOException
import java.util.regex.Pattern
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.border.EmptyBorder

class DashboardPage {
    var dataStream = DataStream()
    var messageWave: MessageDraw? = null
    var carrierWave: CarrierDraw? = null
    var qpskWave: QPSKDraw? = null
    var bpskiWave: BPSKIDraw? = null
    var bpskqWave: BPSKQDraw? = null
    var appTitle: String? = null
    var mainFrame: JFrame? = null
    var wavePanel: JPanel? = null
    var titleLabel: JLabel? = null
    var titlePanel: JPanel? = null
    var controllerPanel: JPanel? = null
    var dataPanel: JPanel? = null
    var carrierPanel: JPanel? = null
    var twoButtonPanel: JPanel? = null
    var mainButtonPanel: JPanel? = null
    var dataLabel: JLabel? = null
    var messageField: JTextField? = null
    var carrierSignalLabel: JLabel? = null
    var carrierTitlePanel: JPanel? = null
    var frequencyLabel: JLabel? = null
    var frequencyField: JTextField? = null
    var amplitudeLabel: JLabel? = null
    var amplitudeField: JTextField? = null
    var resultButton: JButton? = null
    var resetButton: JButton? = null
    var demodulationButton: JButton? = null
    var reportButton: JButton? = null
    var signOutButton: JButton? = null
    var repaintCount = 0

    // TODO: Slider to be implemented
    private val adjustCycles = JSlider(20, 50, 20)
    fun splitMessage() {
        val str = dataStream.message
        val evenString = StringBuilder()
        val oddString = StringBuilder()
        for (i in 0 until str!!.length) {
            if ((i + 1) % 2 == 0) {
                evenString.append(str[i])
            } else {
                oddString.append(str[i])
            }
        }
        dataStream.setEvenString(evenString.toString())
        dataStream.setOddString(oddString.toString())
    }

    fun createGUI() {
        appTitle = "Virtual Lab"
        mainFrame = JFrame(appTitle)
        wavePanel = JPanel()
        wavePanel!!.border = EmptyBorder(20, 20, 20, 20)
        wavePanel!!.background = Color.WHITE
        titleLabel = JLabel()
        titlePanel = JPanel()
        titleLabel!!.text = "DIGITAL MODULATION USING QPSK"
        signOutButton = JButton("Sign Out")
        signOutButton!!.addActionListener(SignOutListener())
        titlePanel!!.add(BorderLayout.CENTER, titleLabel)
        titlePanel!!.add(BorderLayout.EAST, signOutButton)
        //        titlePanel.add(titleLabel);
        titlePanel!!.border = EmptyBorder(10, 10, 10, 10)

        // controller var
        controllerPanel = JPanel()
        dataPanel = JPanel()
        carrierPanel = JPanel()
        twoButtonPanel = JPanel()
        mainButtonPanel = JPanel()
        dataLabel = JLabel("Digital Data Stream")
        messageField = JTextField()
        carrierSignalLabel = JLabel("CARRIER SIGNAL")
        carrierTitlePanel = JPanel()
        carrierTitlePanel!!.add(carrierSignalLabel)
        frequencyLabel = JLabel("Enter Frequency")
        frequencyField = JTextField()
        amplitudeLabel = JLabel("Enter Peak-to-Peak Amplitude")
        amplitudeField = JTextField()
        resultButton = JButton("RESULT")
        resetButton = JButton("RESET")
        demodulationButton = JButton("DEMODULATION")
        reportButton = JButton("REPORT")
        resultButton!!.addActionListener(ResultListener())
        resetButton!!.addActionListener(ResetListener())
        demodulationButton!!.addActionListener(DemodulationListener())
        reportButton!!.addActionListener(ReportListener())

        // For Displaying waves
        wavePanel!!.background = Color.white

        // For the controller gui
        dataPanel!!.layout = GridLayout(2, 1)
        dataPanel!!.add(dataLabel)
        dataPanel!!.add(messageField)
        dataPanel!!.border = EmptyBorder(0, 0, 50, 0)
        carrierPanel!!.layout = GridLayout(5, 1)
        carrierPanel!!.add(carrierTitlePanel)
        carrierPanel!!.add(frequencyLabel)
        carrierPanel!!.add(frequencyField)
        carrierPanel!!.add(amplitudeLabel)
        carrierPanel!!.add(amplitudeField)
        carrierPanel!!.border = EmptyBorder(0, 0, 50, 0)
        twoButtonPanel!!.layout = GridLayout(1, 2)
        twoButtonPanel!!.add(resultButton)
        twoButtonPanel!!.add(resetButton)
        mainButtonPanel!!.layout = GridLayout(3, 1)
        mainButtonPanel!!.add(twoButtonPanel)
        mainButtonPanel!!.add(demodulationButton)
        mainButtonPanel!!.add(reportButton)
        controllerPanel!!.layout = BoxLayout(controllerPanel, BoxLayout.Y_AXIS)
        controllerPanel!!.add(dataPanel)
        controllerPanel!!.add(carrierPanel)
        controllerPanel!!.add(mainButtonPanel)
        controllerPanel!!.border = EmptyBorder(0, 10, 10, 10)

        // mainFrame.add(wavePanel);
        mainFrame!!.contentPane.add(BorderLayout.NORTH, titlePanel)
        mainFrame!!.contentPane.add(BorderLayout.CENTER, wavePanel)
        mainFrame!!.contentPane.add(BorderLayout.EAST, controllerPanel)
        mainFrame!!.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        mainFrame!!.setSize(1280, 720)
        mainFrame!!.setLocationRelativeTo(null)
        mainFrame!!.isVisible = true
    }

    internal inner class SignOutListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            mainFrame!!.isVisible = false
            val loginPage = LoginPage()
            loginPage.createGUI()
        }
    }

    internal inner class ResetListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            wavePanel!!.removeAll()
            wavePanel!!.revalidate()
            wavePanel!!.repaint()
        }
    }

    internal inner class DemodulationListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            wavePanel!!.layout = GridLayout(4, 1)
            val currentBinaryString = messageField!!.text
            val currentFrequency = frequencyField!!.text.toInt()
            val currentAmplitude = amplitudeField!!.text.toInt()
            val isBinary = Pattern.matches("[0-1]+", currentBinaryString)
            if (isBinary) {
                dataStream.message = currentBinaryString
                splitMessage()
                messageWave = MessageDraw(dataStream)
                carrierWave = CarrierDraw(dataStream, currentFrequency, currentAmplitude)
                qpskWave = QPSKDraw(dataStream, currentFrequency, currentAmplitude)
                bpskiWave = BPSKIDraw(dataStream, currentFrequency, currentAmplitude)
                bpskqWave = BPSKQDraw(dataStream, currentFrequency, currentAmplitude)
                messageWave!!.border = EmptyBorder(0, 10, 0, 10)
                qpskWave!!.border = EmptyBorder(0, 10, 0, 10)
                bpskiWave!!.border = EmptyBorder(0, 10, 0, 10)
                bpskqWave!!.border = EmptyBorder(0, 10, 0, 10)
                wavePanel!!.removeAll()
                wavePanel!!.add(qpskWave)
                wavePanel!!.add(bpskiWave)
                wavePanel!!.add(bpskqWave)
                wavePanel!!.add(messageWave)
                wavePanel!!.revalidate()
                wavePanel!!.repaint()
                repaintCount++
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Please enter a binary string", "Error",
                        JOptionPane.ERROR_MESSAGE)
            }
        }
    }

    internal inner class ResultListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            wavePanel!!.layout = GridLayout(3, 1)
            val currentBinaryString = messageField!!.text
            val currentFrequency = frequencyField!!.text.toInt()
            val currentAmplitude = amplitudeField!!.text.toInt()
            val isBinary = Pattern.matches("[0-1]+", currentBinaryString)
            if (isBinary) {
                dataStream.message = currentBinaryString
                splitMessage()
                messageWave = MessageDraw(dataStream)
                carrierWave = CarrierDraw(dataStream, currentFrequency, currentAmplitude)
                qpskWave = QPSKDraw(dataStream, currentFrequency, currentAmplitude)
                messageWave!!.border = EmptyBorder(0, 10, 0, 10)
                carrierWave!!.border = EmptyBorder(0, 10, 0, 10)
                qpskWave!!.border = EmptyBorder(0, 10, 0, 10)
                wavePanel!!.removeAll()
                wavePanel!!.add(messageWave)
                wavePanel!!.add(carrierWave)
                wavePanel!!.add(qpskWave)
                wavePanel!!.revalidate()
                wavePanel!!.repaint()
                repaintCount++
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Please enter a binary string", "Error",
                        JOptionPane.ERROR_MESSAGE)
            }
        }
    }

    internal inner class ReportListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            val w = messageWave!!.width
            val h = messageWave!!.height
            generatePNG("message", messageWave)
            generatePNG("carrier", carrierWave)
            generatePNG("qpsk", qpskWave)
        }

        fun generatePNG(fileName: String, panel: JPanel?) {
            val path = "/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/" + fileName + "5" + ".png"
            val file = File(path)
            if (!file.exists()) {
                try {
                    file.createNewFile()
                    val image = Robot().createScreenCapture(Rectangle(panel!!.locationOnScreen.x, panel.locationOnScreen.y, panel.width, panel.height))
                    ImageIO.write(image, "png", file)
                } catch (ioException: IOException) {
                    ioException.printStackTrace()
                } catch (ioException: AWTException) {
                    ioException.printStackTrace()
                }
            }
            val pdfGenerator = PDFGenerator()
            pdfGenerator.createPDF()
        }
    }
}