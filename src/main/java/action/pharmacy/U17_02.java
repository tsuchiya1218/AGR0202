package action.pharmacy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.Drug_informationDAO;
import dao.Electronic_prescriptionDAO;
import dao.MemberDAO;
import model.Drug_informationBean;
import model.PharmacyBean;

public class U17_02 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession(true);

		String ep_num_ = request.getParameter("ep_num");

		if (ep_num_ == null || "".equals(ep_num_)) {
			forward.setErrorMsg("電子処方箋番号がみつかりませんでした。");
			return forward;
		}
		int ep_num = Integer.parseInt(ep_num_);
		Drug_informationDAO diDAO = Drug_informationDAO.getInstance();
		Drug_informationBean di = new Drug_informationBean();
		PharmacyBean pharmacy = (PharmacyBean) session.getAttribute("pharmacy");
		di.setDi_auth(true);
		di.setDi_p_num(pharmacy.getP_num());

		int di_num = diDAO.createDi(di);
		
		if (di_num == 0) {
			forward.setErrorMsg("薬剤情報提供書登録が失敗しました。");
			return forward;
		}
		Electronic_prescriptionDAO epDAO = Electronic_prescriptionDAO.getInstance();
		if (!epDAO.updateElectronic_prescriptionToDrug_infomation_num(di_num, ep_num)) {
			forward.setErrorMsg("データーベースエラー:電子処方箋に薬剤情報提供書の番号の登録が失敗しました。");
			return forward;
		}

		int m_num = epDAO.findByEp_numToM_num(ep_num);
		int m_qr_num = MemberDAO.getInstance().findByM_numToQr_num(m_num);

		if (m_qr_num == 0) {
			// actionではなくviewを実行するために、setMsgを使う
			forward.setMsg("会員のQRコード番号がみつかりませんでした。");
			forward.setPath("u10_01_pharmacy");
			return forward;
		}

		forward.setMsg("薬剤情報提供書の登録が完了しました。");
		forward.setRedirectToAction(true);
		forward.setPath("u10&m_qr_num="+m_qr_num);
		return forward;
	}

}
