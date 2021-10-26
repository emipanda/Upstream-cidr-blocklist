import java.lang.IllegalArgumentException
import kotlin.math.pow

open class Ip (private val ip : String) {

    //check if ip is valid ( x.x.x.x while 0 < x < 255)
    fun isValidIP(ip: String): Boolean {
        var regex = "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!\$)|\$)){4}\$".toRegex();
        if (regex.matches(ip))
            return true;
        return false;
    }

    //parse IP to decimal number
    fun parseIPtoLong(ipAddr : String): Long {
        if (!this.isValidIP(ipAddr))
            throw IllegalArgumentException("INVALID IPv4 ADDRESS: $ip")
        var result: Long = 0;
        var ipAddressInArray = ipAddr.split(".");

        for (i in 0..3) {
            val power = 3 - i
            val ip = ipAddressInArray[i].toInt()
            result += (ip * 256.0.pow(power.toDouble())).toLong()
        }
        return result;
    }

}