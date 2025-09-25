package jp.co.sss.crud.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.service.LoginResult;
import jp.co.sss.crud.service.LoginService;

@Controller
public class IndexController {

	@Autowired
	HttpSession session;

	@Autowired
	LoginService loginService;

	/**
	 * ログイン画面の表示
	 * GET / にアクセスされたときログイン画面を返す
	 *
	 * @param loginForm LoginForm
	 * @return index.htmlビュー
	 */
	@GetMapping("/")
	public String index(@ModelAttribute LoginForm loginForm) {
		session.invalidate(); // セッション破棄
		return "index";
	}

	/**
	 * ログインコントローラー
	 * POST /login でログイン処理を実施
	 *
	 * @param loginForm フォーム入力データ
	 * @param result    入力エラー判定
	 * @param session   セッション
	 * @param model     モデル（エラーメッセージ登録用）
	 * @return 遷移先ビュー
	 */
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, HttpSession session,
			Model model) {

		// 入力エラーがある場合、index.htmlへ戻す
		if (result.hasErrors()) {
			return "index";
		}

		// loginServiceを呼び出し
		LoginResult loginResult = loginService.login(loginForm);

		System.out.println("empId=" + loginForm.getEmpId());
		System.out.println("empPass=" + loginForm.getEmpPass());

		// ログイン成功時
		if (loginResult.isLogin()) {
			// セッションに"user"を登録
			session.setAttribute("user", loginResult.getLoginUser());

			// 一覧画面へリダイレクト
			return "redirect:/list";
		}
		// ログイン失敗時
		else {
			// エラーメッセージをmodelスコープに登録
			model.addAttribute("errorMsg", loginResult.getErrorMsg());

			return "index";
		}
	}

	/**
	 * ログアウト処理
	 *
	 * @return index.htmlビュー
	 */
	@GetMapping("/logout")
	public String logout() {
		session.invalidate(); // セッション破棄
		return "redirect:/";
	}
}
