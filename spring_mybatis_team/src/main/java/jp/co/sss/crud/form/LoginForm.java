package jp.co.sss.crud.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * ログインフォーム
 */
public class LoginForm {

	/** 社員ID */
	@NotNull(message = "社員IDを入力してください")
	private Integer empId;

	/** パスワード */
	@NotNull(message = "パスワードを入力してください")
	@Size(min = 4, message = "パスワードは4文字以上で入力してください")
	private String empPass;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpPass() {
		return empPass;
	}

	public void setEmpPass(String empPass) {
		this.empPass = empPass;
	}
}
