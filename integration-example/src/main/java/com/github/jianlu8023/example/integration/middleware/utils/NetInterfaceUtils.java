package com.github.jianlu8023.example.integration.middleware.utils;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;

public class NetInterfaceUtils {

    /**
     * 获取本机MAC地址，将所有网卡的MAC地址用冒号拼起来，当作本机的唯一标识。
     * 因为需要考虑内网穿透场景的话，就可能发生不同内网中没有配置外网IP时，机子内网IP相同的情况。
     * 所以不能简单的用内网IP当作机器标识，有外网IP用当然最好。
     */
    private static String getLocalMACList()
            throws SocketException, UnknownHostException {
        StringBuilder sb = new StringBuilder();
        Enumeration<NetworkInterface> allNetInterfaces =
                NetworkInterface.getNetworkInterfaces();
        byte[] mac;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = allNetInterfaces.nextElement();
            if (netInterface.isLoopback() || netInterface.isVirtual() ||
                    netInterface.isPointToPoint() || !netInterface.isUp()) {
                continue;
            }
            mac = netInterface.getHardwareAddress();
            if (mac != null) {
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s",
                            mac[i], (i < mac.length - 1) ? "-" : ":"));
                }
            }
        }
        if (sb.length() <= 1) {
            throw new UnknownHostException("Failed to get local MAC address");
        }
        return sb.substring(0, sb.length() - 1);
    }

    private static String getInterIP2() throws SocketException {
        String localip = null;  // 本地IP，如果没有配置外网IP则返回它
        String netip = null;  // 外网IP
        Enumeration<NetworkInterface> netInterfaces;
        netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip;
        boolean finded = false;  // 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {  // 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {  // 内网IP
                    localip = ip.getHostAddress();
                }
            }
        }
        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }

    public static class RealInter {
        private String ip;
        private String unique;
        private String unique32;

        public RealInter(String ip, String unique) {
            this.ip = ip;
            this.unique = unique;
            try {
                this.unique32 = md5HashCode32(new ByteArrayInputStream(unique.getBytes()));
            } catch (Exception ignore) {
                this.unique32 = UUID.randomUUID().toString().replace("-", "");
            }
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getUnique() {
            return unique;
        }

        public String getUnique32() throws Exception {
            return unique32;
        }

        public void setUnique(String unique) {
            this.unique = unique;
        }

        @Override
        public String toString() {
            return "RealInter{" +
                    "ip='" + ip + '\'' +
                    ", unique='" + unique + '\'' +
                    ", unique32='" + unique32 + '\'' +
                    '}';
        }

        private String md5HashCode32(InputStream fis)
                throws NoSuchAlgorithmException, IOException {
            //拿到一个MD5转换器,如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
            MessageDigest md = MessageDigest.getInstance("MD5");
            //分多次将一个文件读入，对于大型文件而言，比较推荐这种方式，占用内存比较少。
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();
            // 转换并返回包含16个元素字节数组,返回数值范围为-128到127
            byte[] md5Bytes = md.digest();
            StringBuilder hexValue = new StringBuilder();
            for (byte md5Byte : md5Bytes) {
                /*
                 * 在Java语言中涉及到字节byte数组数据的一些操作时，经常看到 byte[i] & 0xff这样的操作，这里就记录总结一下这里包含的意义：
                 * 1、0xff是16进制（十进制是255），它默认为整形，二进制位为32位，最低八位是“1111 1111”，其余24位都是0。
                 * 2、&运算: 如果2个bit都是1，则得1，否则得0；
                 * 3、byte[i] & 0xff：首先，这个操作一般都是在将byte数据转成int或者其他整形数据的过程中；使用了这个操作，最终的整形数据只有低8位有数据，其他位数都为0。
                 * 4、这个操作得出的整形数据都是大于等于0并且小于等于255的
                 */
                int val = ((int) md5Byte) & 0xff;
                if (val < 16) {
                    /*
                     * 如果小于16，那么val值的16进制形式必然为一位，
                     * 因为十进制0,1...9,10,11,12,13,14,15 对应的 16进制为 0,1...9,a,b,c,d,e,f;
                     * 此处高位补0。
                     */
                    hexValue.append("0");
                }
                // 这里借助了Integer类的方法实现16进制的转换
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        }

    }

    private static RealInter getRealIPMAC() throws SocketException {
        Enumeration<NetworkInterface> allNetInterfaces =
                NetworkInterface.getNetworkInterfaces();
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = allNetInterfaces.nextElement();
            // 去除回环接口，子接口，未运行和接口
            if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                continue;
            }
            if (!netInterface.getDisplayName().contains("Intel") && !netInterface.getDisplayName().contains("Realtek")) {
                continue;
            }
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            // System.out.println(netInterface.getDisplayName());
            while (addresses.hasMoreElements()) {
                InetAddress ip = addresses.nextElement();
                if (ip != null) {
                    if (ip instanceof Inet4Address) {
                        byte[] mac = netInterface.getHardwareAddress();
                        StringBuilder sb = new StringBuilder();
                        if (mac != null) {
                            for (int i = 0; i < mac.length; i++) {
                                sb.append(String.format("%02X%s",
                                        mac[i], (i < mac.length - 1) ? "-" : ""));
                            }
                        }
                        return new RealInter(ip.getHostAddress(), sb.toString());
                    }
                }
            }
            break;
        }
        return null;
    }

    public static RealInter getRealInter() throws SocketException, UnknownHostException {
        RealInter inter = getRealIPMAC();
        if (inter != null) {
            return inter;
        }
        return new RealInter(getInterIP2(), getLocalMACList());
    }

}
