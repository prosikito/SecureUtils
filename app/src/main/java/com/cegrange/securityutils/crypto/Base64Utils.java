package com.cegrange.securityutils.crypto;


import android.util.Base64;

/**
 * Clase que facilita el uso del codificador de Android. Permite codificar y decodificar en Base 64
 */
public class Base64Utils {

	/**
	 * Codifica un array de bytes en base64 en un String
	 *
	 * @param data el array de bytes a codificar
	 * @return la cadena codificada
	 */
	public static String encodeToString(byte[] data) {
		return Base64.encodeToString(data, Base64.NO_WRAP);
	}

	public static String decodeToString(String data) {
		return new String(Base64.decode(data, Base64.NO_WRAP));
	}

	/**
	 * Codifica un array de bytes en base64 en otro array de bytes codificados
	 *
	 * @param data el array de bytes a codificar
	 * @return el array de bytes codificado
	 */
	public static byte[] encode(byte[] data) {
		return Base64.encode(data, Base64.NO_WRAP);
	}

	/**
	 * Decodifica un array de bytes en base64 en otro array de bytes decodificados
	 *
	 * @param data el array de bytes a decodificar
	 * @return el array de bytes decodificado
	 */
	public static byte[] decode(byte[] data) {
		return Base64.decode(new String(data), Base64.NO_WRAP);
	}

	/**
	 * Decodifica un String codificada en base64 en un array de bytes decodificados
	 *
	 * @param data el String a decodificar
	 * @return el array de bytes decodificado
	 */
	public static byte[] decode(String data) {
		return Base64.decode(data, Base64.NO_WRAP);
	}
}
