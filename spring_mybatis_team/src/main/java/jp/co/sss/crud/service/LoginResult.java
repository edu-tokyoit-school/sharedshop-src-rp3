package jp.co.sss.crud.service;

import jp.co.sss.crud.entity.Employee;

public class LoginResult {

	private boolean login;
	private Employee loginUser;
	private String errorMsg;

	public boolean isLogin() {
		return login;
	}

	public Employee getLoginUser() {
		return loginUser;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	/** ログイン成功時の生成メソッド */
	public static LoginResult succeedLogin(Employee user) {
		LoginResult result = new LoginResult();
		result.login = true;
		result.loginUser = user;
		return result;
	}

	/** ログイン失敗時の生成メソッド */
	public static LoginResult failLogin(String errorMsg) {
		LoginResult result = new LoginResult();
		result.login = false;
		result.errorMsg = errorMsg;
		return result;
	}
}
