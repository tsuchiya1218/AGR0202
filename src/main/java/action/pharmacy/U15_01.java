package action.pharmacy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.DrugDAO;
import model.DrugBean;

public class U15_01 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ActionForward forward = new ActionForward();
		DrugDAO drugDAO = DrugDAO.getInstance();
		
		String p_ = request.getParameter("p");
		int p = 1;
		if(p_ != null) {
			p = Integer.parseInt(p_);
		}
		
		int startNum = (p-1)*10; // 0 -> 10 -> 20-> 30
		int endNum = p*10; // 9 -> 19 -> 29 -> 39
		
		List<DrugBean> drugPage = drugDAO.getDrugList();
		
		String keyword_type = request.getParameter("keyword_type");
		String medicine_type = request.getParameter("medicine_type");
		String keyword = request.getParameter("keyword");
		int count = drugDAO.totalPageCount();
		
		if ((keyword_type != null && !"".equals(keyword_type)) && (!"".equals(medicine_type) && medicine_type != null)) {
			if ("名前".equals(keyword_type)) {
				if ("全部".equals(medicine_type)) {
					drugPage = drugDAO.searchByName(keyword);
					count = drugDAO.countSearchByName(keyword);
				} else {
					drugPage = drugDAO.searchByNameAndType(keyword, medicine_type);
					count = drugDAO.countSearchByNameAndType(keyword, medicine_type);
				}
			} else {
				if ("全部".equals(medicine_type)) {
					drugPage = drugDAO.searchByEffect(keyword);
					count = drugDAO.countSearchByEffect(keyword);
				} else {
					drugPage = drugDAO.searchByEffectAndType(keyword, medicine_type);
					count = drugDAO.countSearchByEffectAndType(keyword, medicine_type);
				}
			}
			request.setAttribute("keyword_type", keyword_type);
			request.setAttribute("medicine_type", medicine_type);
			request.setAttribute("keyword", keyword);
			request.setAttribute("search_result", count);
		}
		int endPage = (count%10 != 0) ? count/10+1 : count/10; // 表示するPageの番号 リストが3番目までしたい場合は1-2-3まで表示する。
		
		//降順
		Collections.sort(drugPage, new Comparator<DrugBean>() {
            @Override
            public int compare(DrugBean s1, DrugBean s2) {
                if (s1.getDrug_num() < s2.getDrug_num()) {
                    return 1; // 元のreturnは-1だった
                } else if (s1.getDrug_num() > s2.getDrug_num()) {
                    return -1; // 元のreturnは1だった
                }
                return 0;
            }
        });
		
		List<DrugBean> drugList = new ArrayList<>();
		if(drugPage.size() < endNum) {
			endNum = drugPage.size();
		}
		for(int i= startNum; i < endNum; i++ ) {
			drugList.add(drugPage.get(i));
		}
		
		request.setAttribute("startNum", startNum);
		request.setAttribute("endPage", endPage);
		request.setAttribute("drugList", drugList);
		
		forward.setPath("u15_01");
		return forward;
	}

}
