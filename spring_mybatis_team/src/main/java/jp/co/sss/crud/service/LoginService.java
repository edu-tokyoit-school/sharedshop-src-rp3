package jp.co.sss.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.mapper.EmployeeMapper;

/**
 * ログイン処理サービス
 */
@Service
public class LoginService {

	@Autowired
	private EmployeeMapper mapper;

	/**
	 * ログイン処理
	 *
	 * @param loginForm ログインフォーム
	 * @return LoginResult ログイン成功/失敗の結果
	 */
	public LoginResult login(LoginForm loginForm) {
		// DBから社員情報を取得
		Employee employee = mapper.findByEmpIdAndEmpPass(loginForm.getEmpId(), loginForm.getEmpPass());

		if (employee != null) {
			// ログイン成功
			return LoginResult.succeedLogin(employee);
		} else {
			// ログイン失敗
			return LoginResult.failLogin("社員IDまたはパスワードが間違っています。");
		}
	}
}
