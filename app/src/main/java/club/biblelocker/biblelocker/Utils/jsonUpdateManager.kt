package club.biblelocker.biblelocker.Utils

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class jsonUpdateManager {
    fun checkUpdate() : Int
    {
        val url = "URL"
        val obj = URL(url)

        with(obj.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "GET"


            println("\nSending 'GET' request to URL : $url")
            println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                println(response.toString())
                return Integer.parseInt(response.toString())
            }
        }
    }

    fun getUpdate(kind : Int)
    {
        val url = "http://asdf.com/getUpdate/"
        when(kind)
        {
            0 -> url + "bible"
            1 -> url + "budda"
            2 -> url + "idiom"
        }


        val obj = URL(url)

        with(obj.openConnection() as HttpURLConnection)
        {
            requestMethod = "GET"
            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
            }
        }
    }
}