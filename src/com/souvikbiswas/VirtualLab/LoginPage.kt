package com.souvikbiswas.VirtualLab

import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.border.EmptyBorder

class LoginPage {
    var appTitle: String? = null
    var mainFrame: JFrame? = null
    var loginPane: JTabbedPane? = null
    var signInPanel: JPanel? = null
    var signUpPanel: JPanel? = null
    var emailPanel: JPanel? = null
    var passwordPanel: JPanel? = null
    var emailLabel: JLabel? = null
    var passwordLabel: JLabel? = null
    var emailTextField: JTextField? = null
    var passwordTextField: JTextField? = null
    var namePanel: JPanel? = null
    var firstNameLabel: JLabel? = null
    var lastNameLabel: JLabel? = null
    var firstNameTextField: JTextField? = null
    var lastNameTextField: JTextField? = null
    var loadEmailTextField: JTextField? = null
    var loadPasswordPanel: JPanel? = null
    var signUpPasswordLabel: JLabel? = null
    var signUpEmailLabel: JLabel? = null
    var confirmPasswordLabel: JLabel? = null
    var loaderPasswordTextField: JTextField? = null
    var confirmPasswordTextField: JTextField? = null
    var loginButton: JButton? = null
    var signUpButton: JButton? = null
    var userInfo = UserInfo()
    fun createGUI() {
        appTitle = "Virtual Lab"
        mainFrame = JFrame(appTitle)
        loginPane = JTabbedPane()
        //        loginPane.setBounds(50,50,100,100);
        signInPanel = JPanel()
        signUpPanel = JPanel()
        emailPanel = JPanel()
        emailPanel!!.border = EmptyBorder(20, 20, 20, 20)
        passwordPanel = JPanel()
        passwordPanel!!.border = EmptyBorder(20, 20, 20, 20)
        emailLabel = JLabel("Email Address")
        passwordLabel = JLabel("Password")
        emailPanel!!.add(emailLabel)
        passwordPanel!!.add(passwordLabel)
        emailTextField = JTextField()
        passwordTextField = JPasswordField()
        loginButton = JButton("LOGIN")
        loginButton!!.addActionListener(LoginListener())
        signInPanel!!.layout = GridLayout(5, 1)
        signInPanel!!.add(emailPanel)
        signInPanel!!.add(emailTextField)
        signInPanel!!.add(passwordPanel)
        signInPanel!!.add(passwordTextField)
        signInPanel!!.add(loginButton)

        // SIGN UP
        namePanel = JPanel()
        firstNameLabel = JLabel("First Name")
        lastNameLabel = JLabel("Last Name")
        firstNameTextField = JTextField()
        lastNameTextField = JTextField()
        namePanel!!.layout = GridLayout(2, 2)
        namePanel!!.add(firstNameLabel)
        namePanel!!.add(lastNameLabel)
        namePanel!!.add(firstNameTextField)
        namePanel!!.add(lastNameTextField)
        loadEmailTextField = JTextField()
        loaderPasswordTextField = JPasswordField()
        confirmPasswordLabel = JLabel("Confirm Password")
        confirmPasswordTextField = JPasswordField()
        loadPasswordPanel = JPanel()
        signUpPasswordLabel = JLabel("Password")
        signUpEmailLabel = JLabel("Email")
        loadPasswordPanel!!.layout = GridLayout(2, 2)
        loadPasswordPanel!!.add(signUpPasswordLabel)
        loadPasswordPanel!!.add(confirmPasswordLabel)
        loadPasswordPanel!!.add(loaderPasswordTextField)
        loadPasswordPanel!!.add(confirmPasswordTextField)
        signUpButton = JButton("Sign Up")
        signUpButton!!.addActionListener(SignUpListener())
        signUpPanel!!.layout = GridLayout(5, 1)
        signUpPanel!!.add(namePanel)
        signUpPanel!!.add(signUpEmailLabel)
        signUpPanel!!.add(loadEmailTextField)
        signUpPanel!!.add(loadPasswordPanel)
        signUpPanel!!.add(signUpButton)
        loginPane!!.add("SIGN IN", signInPanel)
        loginPane!!.add("SIGN UP", signUpPanel)
        mainFrame!!.add(loginPane)
        mainFrame!!.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        mainFrame!!.setSize(300, 400)
        mainFrame!!.setLocationRelativeTo(null)
        mainFrame!!.isVisible = true
    }

    internal inner class LoginListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            val email = emailTextField!!.text
            val password = passwordTextField!!.text
            var userEmail = ""
            var userPassword = ""
            var isPresent = false

            // Check whether present in SQL
            val sqlUtil = SQLUtil()
            sqlUtil.createTable()
            val allUserInfo = sqlUtil.userLoginInfo
            for (eachUserInfo in allUserInfo!!) {
                userEmail = eachUserInfo!![0]
                userPassword = eachUserInfo[1]
                if (userEmail == email) {
                    isPresent = true
                    break
                }
            }
            if (isPresent) {
                if (password == userPassword) {
                    println("Logged in successfully!")

                    // Navigate to the dashboard
                    val dashboardPage = DashboardPage()
                    mainFrame!!.isVisible = false
                    dashboardPage.createGUI()
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Wrong password!", "Error",
                            JOptionPane.ERROR_MESSAGE)
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "You are not registered. Please sign up first.", "Error",
                        JOptionPane.ERROR_MESSAGE)
            }
        }
    }

    internal inner class SignUpListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            val firstName = firstNameTextField!!.text
            val lastName = lastNameTextField!!.text
            val email = loadEmailTextField!!.text
            val password = loaderPasswordTextField!!.text
            val confirmPassword = confirmPasswordTextField!!.text
            if (password == confirmPassword) {
                var isAlreadyPresent = false

                // Check whether present in SQL
                val sqlUtil = SQLUtil()
                sqlUtil.createTable()
                val allUserInfo = sqlUtil.userLoginInfo
                for (eachUserInfo in allUserInfo!!) {
                    val userEmail = eachUserInfo!![0]
                    if (userEmail == email) {
                        isAlreadyPresent = true
                        break
                    }
                }
                if (!isAlreadyPresent) {
                    sqlUtil.insertRow(firstName, lastName, email, password)
                    JOptionPane.showMessageDialog(mainFrame, "You are successfully registered!", "Signed Up",
                            JOptionPane.INFORMATION_MESSAGE)
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Email ID is already registered. Please try to login.", "Error",
                            JOptionPane.ERROR_MESSAGE)
                }
            }
        }
    }
}