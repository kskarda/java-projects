import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n-----subnet calculator-----\n");
        System.out.println("enter the network address:");
        Scanner keyboard = new Scanner(System.in);
        String baseAddress = keyboard.nextLine();
        System.out.print("enter the CIDR mask:"+"\n"+"/");
        int mask = keyboard.nextInt();
        String addressClass = ipClassCalculator(baseAddress);
        System.out.println("The address you input is "+addressClass);
        int borrowedBits = borrowedBits(addressClass,mask);
        if (borrowedBits == -1)
            System.out.println("You entered an address from a restricted class");
        else
            System.out.println("Your subnet mask borrows "+borrowedBits+" network bits from the default");
        int numOfSubnets = subnetsProduced(borrowedBits);
        System.out.println("Your network address and subnet mask will produce "+numOfSubnets+" subnetworks");
        int numOfHosts = validHosts(mask);
        System.out.println("There will be "+numOfHosts+" valid host IDs per subnet");
        int[] intAddress = octets(baseAddress);
        if (addressClass.equals("class C")) {
            int blockSize = blockSizeSimple(numOfSubnets);
            calculationsClassC(intAddress, blockSize);
        }
        if (addressClass.equals("class B")) {
            int interestOctetSize = blockSizeComplex(addressClass,mask);
            calculationsClassB(intAddress, interestOctetSize,borrowedBits);
        }
        if (addressClass.equals("class A")) {
            int interestOctetSize = blockSizeComplex(addressClass,mask);
            calculationsClassA(intAddress, interestOctetSize,borrowedBits);
        }
        System.out.println("Valid host addresses fall between the subnet and broadcast addresses");
    }

    private static void calculationsClassC(int[] address, int blockSize){
        int startBlock = 0;
        System.out.println("The block size is " + blockSize);
        while (startBlock<256){
            System.out.println("subnet ID\t\t"+address[0]+"."+address[1]+"."+address[2]+"."+startBlock);
            startBlock = startBlock+blockSize;
            System.out.println("broadcast ID\t"+address[0]+"."+address[1]+"."+address[2]+"."+(startBlock-1));
        }
    }

    private static void calculationsClassB(int[] address, int blockSize, int borrowBits){
        int startBlock = 0;
        //CIDR /16 - /24
        if (borrowBits<9) {
            System.out.println("The block size of the third octet is " + blockSize + " and the fourth octet block size is 1");
            while (startBlock < 256) {
                System.out.println("subnet ID\t\t" + address[0] + "." + address[1] + "." + startBlock + ".0");
                startBlock = startBlock + blockSize;
                System.out.println("broadcast ID\t" + address[0] + "." + address[1] + "." + (startBlock - 1) + ".255");
            }
        }
        //CIDR /25 - /30
        else {
            System.out.println("The block size of the third octet is 1 and the fourth octet block size is " + blockSize);
            for (int i = 0; i < 256; i++) {
                while (startBlock < 256) {
                    System.out.println("subnet ID\t\t" + address[0] + "." + address[1] + "." + i + "." + startBlock);
                    startBlock = startBlock + blockSize;
                    System.out.println("broadcast ID\t" + address[0] + "." + address[1] + "." + i + "." + (startBlock - 1));
                }
                startBlock=0;
            }
        }
    }

    private static void calculationsClassA(int[] address, int blockSize, int borrowBits) {
        int startBlock = 0;
        //CIDR /8 - /16
        if (borrowBits < 9) {
            while (startBlock < 256) {
                System.out.println("subnet ID\t\t" + address[0] + "." + startBlock + ".0.0");
                startBlock = startBlock + blockSize;
                System.out.println("broadcast ID\t" + address[0] + "." + (startBlock - 1) + ".255.255");
            }
        }
        //CIDR /17 - /24
        else if (borrowBits < 17) {
            for (int i = 0; i < 256; i++) {
                while (startBlock < 256) {
                    System.out.println("subnet ID\t\t" + address[0] + "." + i + "." + startBlock + ".0");
                    startBlock = startBlock + blockSize;
                    System.out.println("broadcast ID\t" + address[0] + "." + i + "." + (startBlock - 1) + ".255");
                }
                startBlock = 0;
            }
        }
        //CIDR /25 - /30
        else
            for (int i = 0; i < 256; i++) {
                for (int j = 0; j < 256; j++) {
                    while (startBlock < 256) {
                        System.out.println("subnet ID\t\t" + address[0] + "." + i + "." + j + "." + startBlock);
                        startBlock = startBlock + blockSize;
                        System.out.println("broadcast ID\t" + address[0] + "." + i + "." + j + "." + (startBlock - 1));
                    }
                    startBlock = 0;
                }
            }
    }
    private static int[] octets(String base){
        Scanner scan = new Scanner(base);
        scan.useDelimiter("\\.");
        //System.out.println("this is scan.next"+scan.next()+base);
        String firstOctet = scan.next();
        //System.out.println(firstOctet);
        String secondOctet = scan.next();
        //System.out.println(secondOctet);
        String thirdOctet = scan.next();
        //System.out.println(thirdOctet);
        String fourthOctet = scan.next();
        //System.out.println(fourthOctet);
        int first = Integer.parseInt(firstOctet);
        int second = Integer.parseInt(secondOctet);
        int third = Integer.parseInt(thirdOctet);
        int fourth = Integer.parseInt(fourthOctet);
        return new int[]{first,second,third,fourth};
    }

    private static String ipClassCalculator(String base){
        String ipClass;
        int[] octetArray = octets(base);
        int first = octetArray[0];
        if (first>=0 && first<=127)
            ipClass = "class A";
        else if (first>=128 && first<=191)
            ipClass = "class B";
        else if (first>=192 && first<=223)
            ipClass = "class C";
        else
            ipClass = "non host class";
        return ipClass;
    }

    private static int borrowedBits(String classIP, int mask){
        if (classIP.equals("class A"))
            return mask-8;
        else if (classIP.equals("class B"))
            return mask-16;
        else if (classIP.equals("class C"))
            return mask-24;
        else return -1;
    }

    private static int subnetsProduced(int bits){
        return (int) Math.pow(2,bits);
    }

    private static int validHosts(int mask){
        int result;
        int zeroBits = 32-mask;
        result = (int) Math.pow(2,zeroBits);
        result = result-2;
        return result;
    }

    private static int blockSizeSimple(int subnets){
        return 256/subnets;
    }

    private static int blockSizeComplex(String classIP, int mask){
        if (classIP.equals("class B")) {
            //returns the block size of the third octet
            if (mask<25)
                return blockSizeSimple(subnetsProduced(mask-16));
            //returns the block size of the fourth octet in class b
            else
                return blockSizeSimple(subnetsProduced(mask-24));
        }
        if (classIP.equals("class A")){
            //returns the block size of the second octet
            if (mask<17)
                return blockSizeSimple(subnetsProduced(mask-8));
            //returns the block size of the third octet
            else if (mask<25)
                return blockSizeSimple(subnetsProduced(mask-16));
            //returns the block size of the fourth octet
            else
                return blockSizeSimple(subnetsProduced(mask-24));
        }
        else
            return -1;
    }
}