import java.lang.IllegalArgumentException

open class CIDR (private val cidr : String) : Ip(cidr){

    //check if CIDR notation is valid (ip, mask, format)
    fun isValidInput(): Boolean {
        var ipAddr = getIpAddr();
        var mask = getMask();

        if(ipAddr.isBlank() || mask.isBlank()){
            return false;
        }
        if (!isValidIP(ipAddr))
            throw IllegalArgumentException("The input contains invalid IP! -> $ipAddr");
        if (mask.toInt() !in 0..32)
            throw IllegalArgumentException("The input string contains invalid subnet mask! -> '$ipAddr / $mask'");
        return true;
    }

    //Getters
    open fun getIpAddr() : String{
        var ipAddr = "";
        if (cidr.isNotBlank()) {
            var splitCIDR = cidr.split("/");
            if (splitCIDR.size != 2)
                throw IllegalArgumentException("The input list contains illegal notation! - > '$cidr'");
            ipAddr = splitCIDR[0];
        }
        return ipAddr;
}
    open fun getMask() : String{
        var mask = "";
        if (cidr.isNotBlank()) {
            var splitCIDR = cidr.split("/");
            if (splitCIDR.size != 2)
                throw IllegalArgumentException("The input contains illegal notation! -> '$cidr'");
            mask = splitCIDR[1];
        }
        return mask;
    }


    //parse mask to decimal number
    fun parseMaskToInt(): Long {
        var mask = this.getMask();
        if (mask.isNotBlank() && mask.toInt() in 0..32){
            val prefix = mask.toInt();
            val intMask = 0xffffffff shl ((32 - prefix).toInt());
            return intMask;
        }
        return -1;
    }

}