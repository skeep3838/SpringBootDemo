package com.example.comment;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.util.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;

public class RsaUtil {
	private static final String privateKey =
			"MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMvUMfD6zBzmznt59TQ66vLubiPtm5UOsbq35eJh5hQoklh0Ii5hEsvfVDG8Y7lTWO/AP165i3uJ/vGB5Vvgo3pLtFk2/xKTgoDuFG9jsjBp/q1nBJJvKWu0k09pVI+CallHkz6rgMpVJlOUqw/kHBVsFTw5RsCZtrRHGs4aM92vAgMBAAECgYBv+JeezAeI8PoJNswO7zbWUUjMD+FiFvpnmlJ89E7SUB9k3BdS5xzORlcYI2edDT5UJoYpYUBQk1QsE7GNsW/b6fUEB2IQs5BW3sHe8rnL93jnWvx2XKqBqgtUcxzHPtVUENOPG9iMgwsRSxPtJZYwoDCE+y+OGHrpdq3ymf5pgQJBAOTo4ev3hQCQXPC0Cb2x9GvS2uAG+JfW5gO4rL6HiAoJ+ZIzPcvj7pnHFR6K5l7oa8hUHVWexOM7wdq+CyPrPKECQQDj83Tt8xZ43t4Rw8rxhpCogmHhT+72ax96PJbRTcX0cK3wgqlYRREfQI0/Q9BQD5/AyVFvMWyd082JKjlFQyhPAkBfVHEJ0SoNoxFhmMjZvCubX9ORKrYKng9qi1U9f7ny3FaYQUjCCamLVzJ/Zr8r1UVVx1n0F0vNikxgKXcAPW4hAkBMVShgzjhGPYtrs37wJjmm1BJm2teQmQqvaj6u1DZnxpvYoEiJEIfNSVaDSvgk9zZt3nxwmcJfj5+SQRtwgC6tAkBpUAk4ETJ8J4QN1a6siwd5ZTNdVcvjuwqIzFjL5AgG1N/nVQzI7F4jLHFvxXiW5TyU5rgYYbqDSfVdEpOTxBmo";
	private static final String publicKey =
			"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDL1DHw+swc5s57efU0Oury7m4j7ZuVDrG6t+XiYeYUKJJYdCIuYRLL31QxvGO5U1jvwD9euYt7if7xgeVb4KN6S7RZNv8Sk4KA7hRvY7Iwaf6tZwSSbylrtJNPaVSPgmpZR5M+q4DKVSZTlKsP5BwVbBU8OUbAmba0RxrOGjPdrwIDAQAB";
	private static final String dataStr = "amount=1000&datetime=2022-08-26 09:18:23&discount=0";

	/*  加密  */
	public String rsaEncrypt() throws RuntimeException {
		if (StringUtils.isEmpty(dataStr) || StringUtils.isEmpty(privateKey)) {
			throw new RuntimeException("param is empty. data:" + dataStr + ", privateKey:" + privateKey);
		}
		try {
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) getRSAPrivateKeyFromPKCS8(Base64.decodeBase64(privateKey));
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
			String encrypt =
					Base64.encodeBase64String(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, dataStr.getBytes(StandardCharsets.UTF_8), rsaPrivateKey.getModulus().bitLength()));
			return encrypt.replaceAll("(\r\n|\n)", "");
		} catch (Exception e) {
			throw new RuntimeException("rsa encrypt fail!", e);
		}
	}

	/*  解密  */
	@Test
	public void rsaDecrypt() throws RuntimeException {
		String sign = rsaEncrypt();  //取得加密字串
		if (StringUtils.isEmpty(dataStr) || StringUtils.isEmpty(publicKey)) {
			throw new RuntimeException("param is empty. data:" + sign + ", publicKey:" + publicKey);
		}
		try {
			RSAPublicKey rsaPublicKey = (RSAPublicKey) getRSAPublicKeyFromX509(Base64.decodeBase64(publicKey));
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
			String decrypt =
					new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, org.apache.commons.net.util.Base64.decodeBase64(sign), rsaPublicKey.getModulus().bitLength()),
							StandardCharsets.UTF_8);
			System.out.println(decrypt.replaceAll("(\r\n|\n)", ""));
		} catch (Exception e) {
			throw new RuntimeException("rsa decrypt fail!", e);
		}
	}

	private byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
		int maxBlock = 0;
		if (opmode == Cipher.DECRYPT_MODE) {
			maxBlock = keySize / 8;
		} else {
			maxBlock = keySize / 8 - 11;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] buff;
		int i = 0;
		try {
			while (datas.length > offSet) {
				if (datas.length - offSet > maxBlock) {
					buff = cipher.doFinal(datas, offSet, maxBlock);
				} else {
					buff = cipher.doFinal(datas, offSet, datas.length - offSet);
				}
				out.write(buff, 0, buff.length);
				i++;
				offSet = i * maxBlock;
			}
		} catch (Exception e) {
			throw new RuntimeException("encrypt maxBlock:" + maxBlock + " occurs error", e);
		}
		byte[] resultDatas = out.toByteArray();
		IOUtils.closeQuietly(out);
		return resultDatas;
	}

	private static PrivateKey getRSAPrivateKeyFromPKCS8(byte[] pkcs8) throws InvalidKeySpecException {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8);
		KeyFactory keyFactory;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return keyFactory.generatePrivate(keySpec);
	}

	private static PublicKey getRSAPublicKeyFromX509(byte[] x509) throws InvalidKeySpecException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(x509);
		KeyFactory keyFactory;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return keyFactory.generatePublic(keySpec);
	}
}
