package com.souvikbiswas.VirtualLab

import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfWriter
import java.awt.Font
import java.io.FileOutputStream
import java.io.IOException

class PDFGenerator {
    fun createPDF() {
        try {
            val document = Document()
            PdfWriter.getInstance(document, FileOutputStream(FILE))
            document.open()
            addMetaData(document)
            addTitlePage(document)
            //            addContent(document);
            document.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val FILE = "/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/users.pdf"
        private val catFont: com.itextpdf.text.Font = Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 20f,
                com.itextpdf.text.Font.BOLD, BaseColor.BLACK)
        private val ssubFont: com.itextpdf.text.Font = Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 14f,
                com.itextpdf.text.Font.BOLD, BaseColor.BLACK)
        private val textFont: com.itextpdf.text.Font = Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12f,
                com.itextpdf.text.Font.NORMAL)
        private val subFont: com.itextpdf.text.Font = Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 16f,
                com.itextpdf.text.Font.BOLD, BaseColor.MAGENTA)

        // TODO: Modify
        private fun addMetaData(document: Document) {
            document.addTitle("Virtual Lab Report")
            document.addSubject("Virtual Lab")
            document.addKeywords("Java, Virtual, Lab, QPSK, Waveform")
            document.addAuthor("Souvik Biswas")
            document.addCreator("Souvik Biswas")
        }

        @Throws(DocumentException::class)
        private fun addTitlePage(document: Document) {
            val preface = Paragraph()
            // We add one empty line
            addEmptyLine(preface, 1)
            // Lets write a big header
            preface.add(Paragraph("Virtual Lab Report (QPSK Waveform)", catFont))
            addEmptyLine(preface, 1)
            preface.add(Paragraph("Experiment No: 1", textFont))
            preface.add(Paragraph("Date: Fri Apr 17 21:11:54 IST 2020", textFont))
            preface.add(Paragraph("Name: Souvik Biswas", textFont))
            preface.add(Paragraph("Email: sbis1999@gmail.com", textFont))
            addEmptyLine(preface, 1)
            preface.add(Paragraph("INPUTS:", subFont))
            addEmptyLine(preface, 1)
            preface.add(Paragraph("Binary Data Stream: 1011010101", textFont))
            preface.add(Paragraph("Frequency: 20", textFont))
            preface.add(Paragraph("Peak-to-Peak Amplitude: 3", textFont))
            addEmptyLine(preface, 1)
            preface.add(Paragraph("WAVES", ssubFont))
            addEmptyLine(preface, 1)
            preface.add(Paragraph("Digital Data Waveform", textFont))
            addEmptyLine(preface, 1)

            // TODO: Display PNG
            try {
                val img = Image.getInstance("/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/message1.png")
                img.scalePercent(50f)
                preface.add(img)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            addEmptyLine(preface, 1)
            preface.add(Paragraph("Carrier Waveform", textFont))
            addEmptyLine(preface, 1)

            // TODO: Display PNG
            try {
                val img = Image.getInstance("/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/carrier1.png")
                img.scalePercent(50f)
                preface.add(img)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            addEmptyLine(preface, 1)
            preface.add(Paragraph("OUTPUTS:", subFont))
            addEmptyLine(preface, 1)
            preface.add(Paragraph("Modulated QPSK Waveform", textFont))
            addEmptyLine(preface, 1)

            // TODO: Display PNG
            try {
                val img = Image.getInstance("/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/qpsk1.png")
                img.scalePercent(50f)
                preface.add(img)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            document.add(preface)
            document.newPage()
            val preface2 = Paragraph()
            addEmptyLine(preface2, 1)
            preface2.add(Paragraph("Experiment No: 2", textFont))
            preface2.add(Paragraph("Date: Fri Apr 17 21:13:27 IST 2020", textFont))
            preface2.add(Paragraph("Name: Souvik Biswas", textFont))
            preface2.add(Paragraph("Email: sbis1999@gmail.com", textFont))
            addEmptyLine(preface2, 1)
            preface2.add(Paragraph("INPUT:", subFont))
            addEmptyLine(preface2, 1)
            preface2.add(Paragraph("QPSK Waveform", textFont))
            addEmptyLine(preface2, 1)
            try {
                val img = Image.getInstance("/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/qpsk2.png")
                img.scalePercent(40f)
                preface2.add(img)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            addEmptyLine(preface2, 1)
            preface2.add(Paragraph("OUTPUTS:", subFont))
            addEmptyLine(preface2, 1)
            preface2.add(Paragraph("Binary Data Stream: 1011010101", textFont))
            preface2.add(Paragraph("Frequency: 20", textFont))
            preface2.add(Paragraph("Peak-to-Peak Amplitude: 3", textFont))
            addEmptyLine(preface2, 1)
            preface2.add(Paragraph("WAVES", ssubFont))
            addEmptyLine(preface2, 1)
            preface2.add(Paragraph("BPSK I-Channel Waveform", textFont))
            addEmptyLine(preface2, 1)
            try {
                val img = Image.getInstance("/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/bpski2.png")
                img.scalePercent(50f)
                preface2.add(img)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            addEmptyLine(preface2, 1)
            preface2.add(Paragraph("BPSK Q-Channel Waveform", textFont))
            addEmptyLine(preface2, 1)
            try {
                val img = Image.getInstance("/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/bpskq2.png")
                img.scalePercent(50f)
                preface2.add(img)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            preface2.add(Paragraph("Demodulated Digital Data Waveform", textFont))
            addEmptyLine(preface2, 1)

            // TODO: Display PNG
            try {
                val img = Image.getInstance("/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/message2.png")
                img.scalePercent(40f)
                preface2.add(img)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            document.add(preface2)
            document.newPage()
            val preface3 = Paragraph()
            addEmptyLine(preface3, 1)
            preface3.add(Paragraph("Experiment No: 3", textFont))
            preface3.add(Paragraph("Date: Fri Apr 17 21:19:49 IST 2020", textFont))
            preface3.add(Paragraph("Name: Soumi Bardhan", textFont))
            preface3.add(Paragraph("Email: soumibardhan10@gmail.com", textFont))
            addEmptyLine(preface3, 1)
            preface3.add(Paragraph("INPUTS:", subFont))
            addEmptyLine(preface3, 1)
            preface3.add(Paragraph("Binary Data Stream: 10110010", textFont))
            preface3.add(Paragraph("Frequency: 25", textFont))
            preface3.add(Paragraph("Peak-to-Peak Amplitude: 2", textFont))
            addEmptyLine(preface3, 1)
            preface3.add(Paragraph("WAVES", ssubFont))
            addEmptyLine(preface3, 1)
            preface3.add(Paragraph("Digital Data Waveform", textFont))
            addEmptyLine(preface3, 1)

            // TODO: Display PNG
            try {
                val img = Image.getInstance("/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/message3.png")
                img.scalePercent(50f)
                preface3.add(img)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            addEmptyLine(preface3, 1)
            preface3.add(Paragraph("Carrier Waveform", textFont))
            addEmptyLine(preface3, 1)

            // TODO: Display PNG
            try {
                val img = Image.getInstance("/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/carrier3.png")
                img.scalePercent(50f)
                preface3.add(img)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            addEmptyLine(preface3, 1)
            preface3.add(Paragraph("OUTPUTS:", subFont))
            addEmptyLine(preface3, 1)
            preface3.add(Paragraph("Modulated QPSK Waveform", textFont))
            addEmptyLine(preface3, 1)

            // TODO: Display PNG
            try {
                val img = Image.getInstance("/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/qpsk3.png")
                img.scalePercent(50f)
                preface3.add(img)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            document.add(preface3)
            document.newPage()
            val preface4 = Paragraph()
            addEmptyLine(preface4, 1)
            preface4.add(Paragraph("Experiment No: 4", textFont))
            preface4.add(Paragraph("Date: Fri Apr 17 21:22:37 IST 2020", textFont))
            preface4.add(Paragraph("Name: Soumi Bardhan", textFont))
            preface4.add(Paragraph("Email: soumibardhan10@gmail.com", textFont))
            addEmptyLine(preface4, 1)
            preface4.add(Paragraph("INPUTS:", subFont))
            addEmptyLine(preface4, 1)
            preface4.add(Paragraph("Binary Data Stream: 10110010", textFont))
            preface4.add(Paragraph("Frequency: 25", textFont))
            preface4.add(Paragraph("Peak-to-Peak Amplitude: 4", textFont))
            addEmptyLine(preface4, 1)
            preface4.add(Paragraph("WAVES", ssubFont))
            addEmptyLine(preface4, 1)
            preface4.add(Paragraph("Digital Data Waveform", textFont))
            addEmptyLine(preface4, 1)

            // TODO: Display PNG
            try {
                val img = Image.getInstance("/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/message4.png")
                img.scalePercent(50f)
                preface4.add(img)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            addEmptyLine(preface4, 1)
            preface4.add(Paragraph("Carrier Waveform", textFont))
            addEmptyLine(preface4, 1)

            // TODO: Display PNG
            try {
                val img = Image.getInstance("/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/carrier4.png")
                img.scalePercent(50f)
                preface4.add(img)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            addEmptyLine(preface4, 1)
            preface4.add(Paragraph("OUTPUTS:", subFont))
            addEmptyLine(preface4, 1)
            preface4.add(Paragraph("Modulated QPSK Waveform", textFont))
            addEmptyLine(preface4, 1)

            // TODO: Display PNG
            try {
                val img = Image.getInstance("/Users/souvikbiswas/IdeaProjects/VirtuaLab/assets/qpsk4.png")
                img.scalePercent(50f)
                preface4.add(img)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            document.add(preface4)
            document.newPage()
        }

        private fun addEmptyLine(paragraph: Paragraph, number: Int) {
            for (i in 0 until number) {
                paragraph.add(Paragraph(" "))
            }
        }
    }
}