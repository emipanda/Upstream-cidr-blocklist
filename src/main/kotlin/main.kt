fun main(args: Array<String>) {

    var myIpList = listOf<String>("10.11.12.13/24","10.11.12.12/24", "1.1.10.1/30", "70.114.131.253/24");
    val blockService : IPBlockService = IPBlockService(myIpList);

    println(blockService.isAllowed("20.123.22.1")); // Pass
    println(blockService.isAllowed("10.11.12.13")); //Block
    println(blockService.isAllowed("70.114.131.1")); // Block
    println(blockService.isAllowed("70.114.131.253")); //Block
    println(blockService.isAllowed("70.114.132.253")); //Pass
    println(blockService.isAllowed("2.2.2.2")); // Pass
    println(blockService.isAllowed("1.1.1.1100")); // Invalid IP (Block)
    println(blockService.isAllowed("a.a.a.a.a")); // Invalid IP (Block)
    println(blockService.isAllowed("0000")); // Invalid IP (Block)
}
