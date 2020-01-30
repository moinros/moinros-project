package com.moinros.project.tool.cipher;

import com.moinros.project.tool.util.string.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 注释: 对密码进行加盐加密处理
 * 
 * @Author [ moinros ]
 * @Website [ https://www.moinros.com ]
 * @Title Token
 * @Version [ V - 1.0.0 β ]
 * @Data [ 2019年6月20日 下午4:33:51 ]
 */
public class Token {

	/**
	 * KEY : 密文序列
	 */
	private static final String KEY = "zxvutsronmlkjihgfedcba0123456789";

	/**
	 * 注释:为密码混入盐值
	 *
	 * @param password 密码
	 * @return String 混入盐后的密码
	 */
	public String addSalt(String password) {
		return sort(password);
	}

	/**
	 * 注释: 验证密码是否正确
	 *
	 * @param password 密码
	 * @param keySalt  混入盐后的密码
	 * @return boolean 匹配返回true,不匹配返回false
	 */
	public boolean validate(String password, String keySalt) {
		if (password == null || keySalt == null) {
			return false;
		}
		char[] c = keySalt.toCharArray();
		capitalToLowercase(c);
		String k = reduction(new String(c));
		String p = md5(fill(password));
		return p.equals(k);
	}

	/**
	 * 注释: 密文还原
	 */
	private String reduction(String pwd) {
		String[] key = reduction(pwd.toCharArray());
		int[] r = getSequence(key[1].toCharArray());
		char[] k = key[0].toCharArray();
		char[] p = new char[32];
		for (int i = 0; i < r.length; i++) {
			p[r[i]] = k[i];
		}
		return new String(p);
	}

	/**
	 * 注释: 获取密文排序规则
	 */
	private int[] getSequence(char[] arr) {
		int[] irr = new int[arr.length];
		char[] k = KEY.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < k.length; j++) {
				if (arr[i] == k[j]) {
					irr[i] = j;
				}
			}
		}
		return irr;
	}

	/**
	 * 注释: 密文还原
	 */
	private String[] reduction(char[] arr) {
		StringBuilder s1 = new StringBuilder();
		StringBuilder s2 = new StringBuilder();
		boolean f = true;
		for (int i = 0; i < arr.length; i++) {
			if (f) {
				s1.append(arr[i]);
				f = !f;
			} else {
				s2.append(arr[i]);
				f = !f;
			}
		}
		return new String[] { s1.toString(), s2.toString() };
	}

	/**
	 * 注释: 密码补位
	 */
	private String fill(String pwd) {
		if (pwd == null) {
			throw new RuntimeException(">> Error  encrypting password！password is null！");
		} else {
			if (pwd.length() < 32) {
				StringBuilder sb = new StringBuilder();
				char[] p = pwd.toCharArray();
				for (int i = 0; i < 64 - p.length; i++) {
					if (i < p.length) {
						sb.append(p[i]);
					}
					sb.append('-');
				}
				return sb.toString();
			}
			return pwd;
		}
	}

	/**
	 * 注释: 排序
	 */
	private String sort(String pwd) {
		String p = md5(fill(pwd));
		char[] arr = p.toCharArray();
		int[] irr = createArray(arr.length);
		return sort(p.toCharArray(), irr);
	}

	/**
	 * 注释: 排序
	 */
	private String sort(char[] p, int[] r) {
		StringUtil.randomlySort(r); // 随机排序数组
		char[] np = new char[32];
		for (int i = 0; i < r.length; i++) {
			np[i] = p[r[i]];
		}
		char[] ek = sort(r);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < np.length; i++) {
			sb.append(np[i]);
			sb.append(ek[i]);
		}
		char[] c = sb.toString().toCharArray();
		lowercaseToCapital(c);
		return new String(c);
	}

	/**
	 * 注释: 小写字母转为大写
	 */
	private void lowercaseToCapital(char[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= 97 || arr[i] >= 122) {
				arr[i] = (char) ((byte) arr[i] - 32);
			}
		}
	}

	/**
	 * 注释: 大写字母转为小写
	 */
	private void capitalToLowercase(char[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= 65 || arr[i] >= 90) {
				arr[i] = (char) ((byte) arr[i] + 32);
			}
		}
	}

	/**
	 * 注释: 排序
	 */
	private char[] sort(int[] r) {
		char[] c = new char[32];
		char[] k = KEY.toCharArray();
		for (int i = 0; i < r.length; i++) {
			c[i] = k[r[i]];
		}
		return c;
	}

	/**
	 * 注释: 生成一个有序的数组
	 */
	private int[] createArray(int x) {
		int[] arr = new int[x];
		for (int i = 0; i < x; i++) {
			arr[i] = i;
		}
		return arr;
	}

	/**
	 * 注释: 为字符串做MD5
	 */
	private String md5(String s) {
		return DigestUtils.md5Hex(s);
	}

}
