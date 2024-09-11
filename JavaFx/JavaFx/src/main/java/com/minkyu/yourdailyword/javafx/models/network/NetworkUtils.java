package com.minkyu.yourdailyword.javafx.models.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkUtils {
	public static String getHostAddress(boolean excludeEthernet) throws SocketException {
		String hostAddress = "";

		Enumeration<NetworkInterface> networkInterfaces = null;
		try {
			networkInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		while (networkInterfaces.hasMoreElements()) {
			NetworkInterface networkInterface = networkInterfaces.nextElement();

			if (
				!networkInterface.isUp() ||
					(excludeEthernet && networkInterface.getName().contains("eth"))
			) {
				continue;
			}

			Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
			while (inetAddresses.hasMoreElements()) {
				InetAddress inetAddress = inetAddresses.nextElement();
				if (inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress()) {
					continue;
				}

				if (inetAddress.isSiteLocalAddress()) {
					hostAddress = inetAddress.getHostAddress();
				}
			}
		}

		return hostAddress;
	}
}
