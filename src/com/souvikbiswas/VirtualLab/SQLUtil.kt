package com.souvikbiswas.VirtualLab

import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class SQLUtil {
    // SQL URL: jdbc:mysql://localhost:3306
    val connection: Connection?
        get() {
            try {
                val driver = "com.mysql.cj.jdbc.Driver"
                val url = "jdbc:mysql://localhost:3306/user_db"
                Class.forName(driver)
                val connection = DriverManager.getConnection(url, "souvikbiswas", "12345")
                println("Connected to SQL")
                return connection
            } catch (e: Exception) {
                println(e)
            }
            return null
        }

    // Working
    fun createTable() {
        try {
            val connection = connection!!
            val create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user_info(id int NOT NULL AUTO_INCREMENT, first varchar(255), last varchar(255), email varchar(255), password varchar(255), PRIMARY KEY(id))")
            create.executeUpdate()
        } catch (e: Exception) {
            println(e)
        } finally {
            println("Table successfully created")
        }
    }

    fun createDetailTable() {
        try {
            val connection = connection!!
            val create = connection.prepareStatement("CREATE TABLE IF NOT EXISTS exp_detail(id int NOT NULL AUTO_INCREMENT, date varchar(255), name varchar(255), email varchar(255), data_stream varchar(255), frequency float, amplitude float, type int, PRIMARY KEY(id))")
            create.executeUpdate()
        } catch (e: Exception) {
            println(e)
        } finally {
            println("Table successfully created")
        }
    }

    fun deleteDetailTable() {
        try {
            val connection = connection!!
            val deleteRows = connection.prepareStatement("DELETE FROM exp_detail")
            deleteRows.executeUpdate()
            val deleteTable = connection.prepareStatement("DROP TABLE exp_detail")
            deleteTable.executeUpdate()
        } catch (e: Exception) {
            println(e)
        } finally {
            println("Detail Table deleted successfully")
        }
    }

    // Working
    fun deleteTable() {
        try {
            val connection = connection!!
            val deleteRows = connection.prepareStatement("DELETE FROM user_info")
            deleteRows.executeUpdate()
            val deleteTable = connection.prepareStatement("DROP TABLE user_info")
            deleteTable.executeUpdate()
        } catch (e: Exception) {
            println(e)
        } finally {
            println("Table deleted successfully")
        }
    }

    fun insertRowDetail(date: String, name: String, email: String, message: String, frequency: Float, amplitude: Float, modulation: Int) {
        try {
            val connection = connection!!
            val insert = connection.prepareStatement("INSERT INTO exp_detail (date, name, email, data_stream, frequency, amplitude, type) VALUES ('$date', '$name', '$email', '$message', '$frequency', '$amplitude', '$modulation')")
            insert.executeUpdate()
        } catch (e: Exception) {
            println(e)
        } finally {
            println("Detail Row inserted successfully")
        }
    }

    // Working
    fun insertRow(firstName: String, lastName: String, email: String, password: String) {
        try {
            val connection = connection!!
            val insert = connection.prepareStatement("INSERT INTO user_info (first, last, email, password) VALUES ('$firstName', '$lastName', '$email', '$password')")
            insert.executeUpdate()
        } catch (e: Exception) {
            println(e)
        } finally {
            println("Row inserted successfully")
        }
    }

    val detail: ArrayList<ArrayList<String>>?
        get() {
            try {
                val connection = connection!!
                val select = connection.prepareStatement("SELECT * FROM exp_detail")
                val result = select.executeQuery()
                val arrayList = ArrayList<ArrayList<String>>()
                while (result.next()) {
                    print(result.getString("date"))
                    print(" ")
                    println(result.getString("name"))
                    val userInfo = ArrayList<String>()
                    userInfo.add(result.getString("id"))
                    userInfo.add(result.getString("date"))
                    userInfo.add(result.getString("name"))
                    userInfo.add(result.getString("email"))
                    userInfo.add(result.getString("data_stream"))
                    userInfo.add(result.getString("frequency"))
                    userInfo.add(result.getString("amplitude"))
                    userInfo.add(result.getString("modulation"))
                    arrayList.add(userInfo)
                }
                println("All detail records are selected")
                return arrayList
            } catch (e: Exception) {
                println(e)
            }
            return null
        }

    // Working
    val userLoginInfo: ArrayList<ArrayList<String>>?
        get() {
            try {
                val connection = connection!!
                val select = connection.prepareStatement("SELECT email, password FROM user_info")
                val result = select.executeQuery()
                val arrayList = ArrayList<ArrayList<String>>()
                while (result.next()) {
                    print(result.getString("email"))
                    print(" ")
                    println(result.getString("password"))
                    val userInfo = ArrayList<String>()
                    userInfo.add(result.getString("email"))
                    userInfo.add(result.getString("password"))
                    arrayList.add(userInfo)
                }
                println("All records are selected")
                return arrayList
            } catch (e: Exception) {
                println(e)
            }
            return null
        }
}