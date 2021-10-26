## isAllowed function explanation by example: 

Let's say we have this entry in our CIDR noataion list: 
  `10.1.2.45/24`

That means we should block all ip in the range: 
  `10.1.2.1 - 10.1.2.254`

We are given the following ip: 
  `10.1.2.150`
##
##### This is how we check if this ip should be blocked:
* First, we need to convert the given ip to a decimal number: 

  `10 x (256)^3 + 1 x (256)^2 + 2 x (256)^1 + 150 x (256)^0 = 167838358`

* Then convert to decimal number the ip part of the CIDR notation:

  `10 x (256)^3 + 1 x (256)^2 + 2 x (256)^1 + 45 x (256)^0= 167838253`

* Then convert to decimal number the mask of the CIDR notaion (with left shift): 

  `0xffffffff << 32-24  = 0xffffffff << 8 = 1099511627520`


* And finally we apply 'AND' bitwise function between the calculated values:
    `(ip & mask) == (CIDRip & mask)`
  
  In our case: 
  
    `167838358 AND 1099511627520 = 167838208 `
    
    `167838253 AND 1099511627520 = 167838208 `
  
And the ip:  `10.1.2.150` is BLOCKED :)


