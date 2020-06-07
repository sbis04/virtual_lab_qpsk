# Virtual Laboratory (QPSK Modulation)

This is a Virtual Laboratory where students can perform various **Quadrature Phase Shift Keying (QPSK)** Wave Modulation experiments.

## Screenshots

<h4 align="center">Sign In & Sign Up Dialog Boxes</h4>
<p align="center">
  <img width=400 src="https://github.com/sbis04/virtual_lab_qpsk/raw/master/screenshots/sign_in.png" alt="Sign In" /> 
  <img width=400 src="https://github.com/sbis04/virtual_lab_qpsk/raw/master/screenshots/sign_up.png" alt="Sign Up" />
</p>

<h4 align="center">QPSK Modulated Waveform</h4>
<p align="center">
  <img src="https://github.com/sbis04/virtual_lab_qpsk/raw/master/screenshots/modulated.png" alt="Modulated Waveform" />
</p>

<h4 align="center">QPSK Demodulated Waveform</h4>
<p align="center">
  <img src="https://github.com/sbis04/virtual_lab_qpsk/raw/master/screenshots/demodulated.png" alt="Demodulated Waveform" />
</p>

<h4 align="center">Report</h4>
<p align="center">
  <img width=400 src="https://github.com/sbis04/virtual_lab_qpsk/raw/master/screenshots/report_1.png" alt="Report Page 1" />
  <img width=400 src="https://github.com/sbis04/virtual_lab_qpsk/raw/master/screenshots/report_2.png" alt="Report Page 2" />
</p>


## Inputs

1. Digital data stream (Message Signal)
2. Carrier signal (High Frequency Signal)
   * Frequency
   * Peak to peak amplitude

## Outputs

1. Waveform of digital data
2. Carrier frequency waveform
3. Modulated waveform
4. Demodulated waveform (when Demodulation button is clicked)

## Database

**MySQL Database** is used for storing the user information (required for **authentication** during the sign-up and login process) and for storing the information about the waveforms.

The database contains two tables:

* **user_info** (where all user information are stored)
* **exp_detail** (where all the details related to the waveforms are stored)

### Table user_info

This table contains five columns:

* id (PRIMARY KEY)
* first
* last
* email
* password

### Table exp_detail

This table contains eight columns:

* id (PRIMARY KEY)
* date
* name
* email
* data_stream
* frequency
* amplitude
* type

## License

Copyright (c) 2020 Souvik Biswas

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
