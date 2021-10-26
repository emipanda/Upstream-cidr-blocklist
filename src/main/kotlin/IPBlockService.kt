import java.lang.IllegalArgumentException

class IPBlockService(){
    private val ipBlockList: MutableList<CIDR> = mutableListOf<CIDR>()

    //ctor building list of CIDR addresses and adding them to blockList
    constructor(ipBlockList: List<String>) : this() {
        if(ipBlockList.isNotEmpty()){
            val ipBlockListCIDR = convertStringListToCIDR(ipBlockList)
            for(cidr in ipBlockListCIDR){
                if(cidr.isValidInput()){
                    this.ipBlockList.add(cidr);
                }
                else{
                    println("The CIDR ${cidr.toString()} is invalid!");
                }
            }
        }
    }

    fun isAllowed(incomingIp: String): Boolean {
        val ip : Ip = Ip(incomingIp);
        if (!ip.isValidIP(incomingIp)) {
            print("IP ($incomingIp) IS INVALID! --> BLOCKED ");
            return false;
        }
        else{
            //  Check if the address is part of the subnet
                //(ip & mask) == (subnet & mask)
            var ipAddrInt = ip.parseIPtoLong(incomingIp);
            for(cidr in this.ipBlockList) {
                var maskToInt = cidr.parseMaskToInt();
                var cidrIp = cidr.parseIPtoLong(cidr.getIpAddr());
                if (ipAddrInt and maskToInt ==  cidrIp and maskToInt) {
                    print("IP $incomingIp is Blocked! ")
                    return false;
                }
            }
            return true;
        }
    }

    private fun convertStringListToCIDR(inputList : List<String>) : ArrayList<CIDR>{
        var ipBlockArrayList : ArrayList<CIDR> = arrayListOf();
        if(inputList.isNotEmpty())
            for (str in inputList){
                var cidr : CIDR = CIDR(str);
                ipBlockArrayList.add(cidr);
            }
        return ipBlockArrayList;
    }
}


