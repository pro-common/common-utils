package com.kaiyun.common.utils;

import java.net.InetAddress;

/**
 * @author by jeff
 *         on 2014/9/20.
 */
public class IpUtils {
    private final static int INADDRSZ = 4;

    /**
     * 把IP地址转化为字节数组
     * @param ipAddr
     * @return byte[]
     */
    public static byte[] ipToBytesByInet(String ipAddr) {
        try {
            return InetAddress.getByName(ipAddr).getAddress();
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }

    /**
     * 把IP地址转化为int
     * @param ipAddr
     * @return int
     */
    public static byte[] ipToBytesByReg(String ipAddr) {
        byte[] ret = new byte[4];
        try {
            String[] ipArr = ipAddr.split("\\.");
            ret[0] = (byte) (Integer.parseInt(ipArr[0]) & 0xFF);
            ret[1] = (byte) (Integer.parseInt(ipArr[1]) & 0xFF);
            ret[2] = (byte) (Integer.parseInt(ipArr[2]) & 0xFF);
            ret[3] = (byte) (Integer.parseInt(ipArr[3]) & 0xFF);
            return ret;
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }

    /**
     * 字节数组转化为IP
     * @param bytes
     * @return int
     */
    public static String bytesToIp(byte[] bytes) {
        return new StringBuffer().append(bytes[0] & 0xFF).append('.').append(bytes[1] & 0xFF)
            .append('.').append(bytes[2] & 0xFF).append('.').append(bytes[3] & 0xFF).toString();
    }

    /**
     * 根据位运算把 byte[] -> int
     * @param bytes
     * @return int
     */
    public static long bytesToInt(byte[] bytes) {
        long addr = bytes[3] & 0xFF;
        addr |= ((bytes[2] << 8) & 0xFF00);
        addr |= ((bytes[1] << 16) & 0xFF0000);
        addr |= ((bytes[0] << 24) & 0xFF000000);
        return addr;
    }

    /**
     * 把IP地址转化为int
     * @param ipAddr
     * @return int
     */
    public static long ipToInt(String ipAddr) {
        try {
            return bytesToInt(ipToBytesByInet(ipAddr));
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }

    /**
     * ipInt -> byte[]
     * @param ipInt
     * @return byte[]
     */
    public static byte[] intToBytes(int ipInt) {
        byte[] ipAddr = new byte[INADDRSZ];
        ipAddr[0] = (byte) ((ipInt >>> 24) & 0xFF);
        ipAddr[1] = (byte) ((ipInt >>> 16) & 0xFF);
        ipAddr[2] = (byte) ((ipInt >>> 8) & 0xFF);
        ipAddr[3] = (byte) (ipInt & 0xFF);
        return ipAddr;
    }

    /**
     * 把int->ip地址
     * @param ipInt
     * @return String
     */
    public static String intToIp(long ipInt) {
        return new StringBuilder().append(((ipInt >> 24) & 0xff)).append('.')
            .append((ipInt >> 16) & 0xff).append('.').append((ipInt >> 8) & 0xff).append('.')
            .append((ipInt & 0xff)).toString();
    }

    /**
     * 把192.168.1.1/24 转化为int数组范围
     * @param ipAndMask
     * @return int[]
     */
    public static long[] getIPIntScope(String ipAndMask) {
        String[] ipArr = ipAndMask.split("/");
        if (ipArr.length != 2) {
            throw new IllegalArgumentException("invalid ipAndMask with: " + ipAndMask);
        }
        int netMask = Integer.valueOf(ipArr[1].trim());
        if (netMask < 0 || netMask > 31) {
            throw new IllegalArgumentException("invalid ipAndMask with: " + ipAndMask);
        }
        long ipInt = ipToInt(ipArr[0]);
        long netIP = ipInt & (0xFFFFFFFF << (32 - netMask));
        long hostScope = (0xFFFFFFFF >>> netMask);
        return new long[] { netIP, netIP + hostScope };
    }

    /**
     * 把192.168.1.1/24 转化为IP数组范围
     * @param ipAndMask
     * @return String[]
     */
    public static String[] getIPAddrScope(String ipAndMask) {
        long[] ipIntArr = getIPIntScope(ipAndMask);
        return new String[] { intToIp(ipIntArr[0]), intToIp(ipIntArr[0]) };
    }

    /**
     * 根据IP 子网掩码（192.168.1.1 255.255.255.0）转化为IP段
     * @param ipAddr ipAddr
     * @param mask mask
     * @return int[]
     */
    public static long[] getIPIntScope(String ipAddr, String mask) {
        long ipInt;
        long netMaskInt = 0, ipcount = 0;
        try {
            ipInt = ipToInt(ipAddr);
            if (null == mask || "".equals(mask)) {
                return new long[] { ipInt, ipInt };
            }
            netMaskInt = ipToInt(mask);
            ipcount = ipToInt("255.255.255.255") - netMaskInt;
            long netIP = ipInt & netMaskInt;
            long hostScope = netIP + ipcount;
            return new long[] { netIP, hostScope };
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid ip scope express  ip:" + ipAddr + "  mask:"
                                               + mask);
        }
    }

    /**
     * 根据IP 子网掩码（192.168.1.1 255.255.255.0）转化为IP段
     * @param ipAddr ipAddr
     * @param mask mask
     * @return String[]
     */
    public static String[] getIPStrScope(String ipAddr, String mask) {
        long[] ipIntArr = getIPIntScope(ipAddr, mask);
        return new String[] { intToIp(ipIntArr[0]), intToIp(ipIntArr[0]) };
    }

    public static void main(String[] args) {
        String ip1 = "180.97.33.108";
        //        byte[] bytes = IPAddressUtil.textToNumericFormatV4(ip1);

        System.out.println(256 * (46 + 256 * (215 + 256 * 58)) + 169);
        System.out.println(180L * 256 * 256 * 256 + 97L * 256 * 256 + 33L * 256 + 108L);
        System.out.println(ipToInt("180.97.33.108"));
        long unsignedValue = ipToInt("180.97.33.108") & Integer.MAX_VALUE;
        System.out.println(unsignedValue |= 0x80000000L);
    }
}
