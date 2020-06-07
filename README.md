# Virtual Laboratory (QPSK Modulation)

This is a Virtual Laboratory where students can perform various **Quadrature Phase Shift Keying (QPSK)** Wave Modulation experiments.

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

