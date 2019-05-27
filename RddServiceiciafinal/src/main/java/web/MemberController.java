package web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import PageDTO.PageMaker;
import file.FileDTO;
import service.BasketVO;
import service.Email;
import service.MemberVO;
import service.OrderVO;
import service.organicVO;
import service.impl.MemberDAOJDBC;
import service.impl.MemberLoginService;
import service.impl.SearchPwService;

@Controller
public class MemberController {

	@RequestMapping(value = "/joinMember.do", method = RequestMethod.POST) // 회원가입컨트롤러
	public String joinMember(MemberVO vo, MemberDAOJDBC memberDAO, Model model, HttpServletRequest req)
			throws Exception {

		String name = req.getParameter("name");

		System.out.println("회원등록완료");

		memberDAO.insertMember(vo);

		model.addAttribute("name", name);

		return "joinsuccess"; // 회원가입완료시 조인석세로 이동

	}

	@RequestMapping(value = "/loginMember.do", method = RequestMethod.POST) // 로그인시 contoroller
	public String loginMember(MemberVO vo, HttpServletResponse resp, MemberDAOJDBC memberDAO, HttpSession session,
			organicVO organicvo, Model model) throws Exception { // 로그인 클릭시 호출되는 컨트롤러

		MemberLoginService memberloginservice = new MemberLoginService();
		boolean loginResult = memberloginservice.login(vo); // memberloginservice페이지에서 true 반환

		if (loginResult) { // true
			session.setAttribute("id", vo.getId()); // 세션에 아이디를 저장
			System.out.println(vo.getId());
			model.addAttribute("organic_item", memberDAO.organic_item(organicvo));
			model.addAttribute("processedfood_item", memberDAO.processedfood_item(organicvo));
			model.addAttribute("junkfood_item", memberDAO.junkfood_item(organicvo));
			model.addAttribute("dringkcafe_item", memberDAO.dringkcafe_item(organicvo));
			model.addAttribute("girlclothes_item", memberDAO.girlclothes_item(organicvo));
			model.addAttribute("boyclothes_item", memberDAO.boyclothes_item(organicvo));
			model.addAttribute("babyclothes_item", memberDAO.babyclothes_item(organicvo));
			model.addAttribute("kitch_item", memberDAO.kitch_item(organicvo));
			model.addAttribute("bathroom_item", memberDAO.bathroom_item(organicvo));
			model.addAttribute("storage_item", memberDAO.storage_item(organicvo));
			model.addAttribute("cleaning_item", memberDAO.cleaning_item(organicvo));
			model.addAttribute("cosmetic_item", memberDAO.cosmetic_item(organicvo));
			model.addAttribute("beauty_item", memberDAO.beauty_item(organicvo));
			model.addAttribute("perfume_item", memberDAO.perfume_item(organicvo));

			return "mainPage"; // 로그인 성공시 mainPage 리턴
		} else {
			System.out.println("로그인 실패dd");
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('아이디 또는 비밀번호를 다시 확인하세요.\\n등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.')");
			out.println("location.href='./login.jsp'");
			out.println("</script>");

			return null;

		}

	}

	@RequestMapping(value = "/logout.do") // 로그아웃 컨트롤러
	public String logout(HttpSession session, MemberLoginService memberloginservice, Model model, organicVO organicvo,
			MemberDAOJDBC memberDAO) throws Exception {

		memberloginservice.logout(session);

		model.addAttribute("organic_item", memberDAO.organic_item(organicvo));
		model.addAttribute("processedfood_item", memberDAO.processedfood_item(organicvo));
		model.addAttribute("junkfood_item", memberDAO.junkfood_item(organicvo));
		model.addAttribute("dringkcafe_item", memberDAO.dringkcafe_item(organicvo));
		model.addAttribute("girlclothes_item", memberDAO.girlclothes_item(organicvo));
		model.addAttribute("boyclothes_item", memberDAO.boyclothes_item(organicvo));
		model.addAttribute("babyclothes_item", memberDAO.babyclothes_item(organicvo));
		model.addAttribute("kitch_item", memberDAO.kitch_item(organicvo));
		model.addAttribute("bathroom_item", memberDAO.bathroom_item(organicvo));
		model.addAttribute("storage_item", memberDAO.storage_item(organicvo));
		model.addAttribute("cleaning_item", memberDAO.cleaning_item(organicvo));
		model.addAttribute("cosmetic_item", memberDAO.cosmetic_item(organicvo));
		model.addAttribute("beauty_item", memberDAO.beauty_item(organicvo));
		model.addAttribute("perfume_item", memberDAO.perfume_item(organicvo));
		return "forward:index.jsp";

	}

	@RequestMapping(value = "/checkId.do", method = RequestMethod.POST) // 아이디 중복확인
	public @ResponseBody String AjaxView(@RequestParam("id") String id, MemberVO vo, HttpSession session)
			throws Exception {
		System.out.println("되는거겠지.....?");
		String str = "";
		MemberLoginService memberloginservice = new MemberLoginService();
		System.out.println(vo.getId());
		boolean idcheckResult = memberloginservice.idCheck(vo);
		System.out.println(idcheckResult);

		if (idcheckResult) {

			str = "NO";
		} else {
			str = "YES";

		}

		System.out.println(str);
		return str;

	}

	@RequestMapping(value = "/foodorganic.do", method = RequestMethod.GET)
	public String organiclist(organicVO vo, MemberDAOJDBC memberDAO, Model model, HttpServletRequest req,
			PageMaker page) throws Exception {

		if (vo.getSearch() == null || vo.getSearch().equals("")) {

			String pagenum = req.getParameter("pagenum");

			if (req.getParameter("pagenum") == null || req.getParameter("pagenum").equals("")) {
				pagenum = "1";

			}

			int cpagenum = Integer.parseInt(pagenum);
			int realpagenum = Integer.parseInt(pagenum);

			System.out.println("받아오는 page 갑 : " + page.getPagenum());
			page.setTotalcount(memberDAO.pageTotal(page, vo));
			page.setPagenum(cpagenum - 1); // 현재 페이지를 페이지 객체에 지정한다
			/* page.setContentnum(ccontentnum); */ // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다
			page.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다
			page.setLastblock(page.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다

			page.prevnext(cpagenum); // 현재 페이지 번호로 화살표 여부를 지정한다.
			page.setStartPage(page.getCurrentblock()); // 시작 페이지를 페이지 블록 번호로 정한다
			page.setEndPage(page.getLastblock(), page.getCurrentblock());
			// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다

			System.out.println("현재 페이지 번호" + page.getPagenum());

			model.addAttribute("pageval", realpagenum);

			model.addAttribute("organiclist", memberDAO.organicList(vo, page));

			model.addAttribute("page", page);

		} else if (vo.getSearch() != null) {

			String pagenum = req.getParameter("pagenum");

			if (req.getParameter("pagenum") == null || req.getParameter("pagenum").equals("")) {
				pagenum = "1";
				System.out.println("if pagenum : " + pagenum);
			}

			int cpagenum = Integer.parseInt(pagenum);
			System.out.println("cpagenum : " + cpagenum);

			page.setTotalcount(memberDAO.Totalsearch(page, vo));
			page.setPagenum(cpagenum - 1); // 현재 페이지를 페이지 객체에 지정한다
			/* page.setContentnum(ccontentnum); */ // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다
			page.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다
			page.setLastblock(page.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다

			page.prevnext(cpagenum); // 현재 페이지 번호로 화살표 여부를 지정한다.
			page.setStartPage(page.getCurrentblock()); // 시작 페이지를 페이지 블록 번호로 정한다
			page.setEndPage(page.getLastblock(), page.getCurrentblock());
			// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다

			List<organicVO> search = new ArrayList<organicVO>();

			search = memberDAO.searchList(vo, page);

			String search_category = "";
			// String search_keyword = "";

			for (int a = 0; a < search.size(); a++) {
				organicVO organic = new organicVO();

				organic = search.get(a);
				search_category = organic.getP_category();
				// search_keyword = organic.getSearch();
			}

			vo.setP_category(search_category);
			// vo.setSearch(search_keyword);

			model.addAttribute("search_val", vo.getSearch());

			model.addAttribute("organiclist", memberDAO.searchList(vo, page));
			model.addAttribute("page", page);
		}

		return "food_organic";

	}

	@RequestMapping(value = "/selectorganic.do")
	public String selectSample(organicVO vo, MemberDAOJDBC memberDAO, Model model, HttpSession session, PageMaker page)
			throws Exception {

		String name = (String) session.getAttribute("id");
		System.out.println(name);

		if (name == null || name.equals("")) {
			model.addAttribute("select", memberDAO.selectorganic(vo));
			System.out.println("비회원");
			return "select_organic";

		} else if (name.equals("admin")) {
			model.addAttribute("select", memberDAO.selectorganic(vo));
			System.out.println("관리자");

			System.out.println("상세보기 페이지 넘 : " + page.getPagenum());
			System.out.println("상세보기 카테고리 : " + vo.getP_category());

			int pagenum = page.getPagenum();
			model.addAttribute("category", vo.getP_category());
			model.addAttribute("pageval", pagenum);

			return "adminselect_product";

		} else {
			model.addAttribute("select", memberDAO.selectorganic(vo));
			System.out.println("회원");
			System.out.println("카테고리" + vo.getP_category());
			System.out.println("넘버" + vo.getP_number());

			return "select_organic";
		}

	}

	@RequestMapping(value = "/hompage.do", method = RequestMethod.GET) // 로고클릭시 홈페이지로 가기
	public String hompage(HttpSession session, Model model, MemberDAOJDBC memberDAO, organicVO organicvo)
			throws Exception {

		if (session.getAttribute("id") != null) {

			model.addAttribute("organic_item", memberDAO.organic_item(organicvo));
			model.addAttribute("processedfood_item", memberDAO.processedfood_item(organicvo));
			model.addAttribute("junkfood_item", memberDAO.junkfood_item(organicvo));
			model.addAttribute("dringkcafe_item", memberDAO.dringkcafe_item(organicvo));
			model.addAttribute("girlclothes_item", memberDAO.girlclothes_item(organicvo));
			model.addAttribute("boyclothes_item", memberDAO.boyclothes_item(organicvo));
			model.addAttribute("babyclothes_item", memberDAO.babyclothes_item(organicvo));
			model.addAttribute("kitch_item", memberDAO.kitch_item(organicvo));
			model.addAttribute("bathroom_item", memberDAO.bathroom_item(organicvo));
			model.addAttribute("storage_item", memberDAO.storage_item(organicvo));
			model.addAttribute("cleaning_item", memberDAO.cleaning_item(organicvo));
			model.addAttribute("cosmetic_item", memberDAO.cosmetic_item(organicvo));
			model.addAttribute("beauty_item", memberDAO.beauty_item(organicvo));
			model.addAttribute("perfume_item", memberDAO.perfume_item(organicvo));
			return "mainPage";
		}
		model.addAttribute("organic_item", memberDAO.organic_item(organicvo));
		model.addAttribute("processedfood_item", memberDAO.processedfood_item(organicvo));
		model.addAttribute("junkfood_item", memberDAO.junkfood_item(organicvo));
		model.addAttribute("dringkcafe_item", memberDAO.dringkcafe_item(organicvo));
		model.addAttribute("girlclothes_item", memberDAO.girlclothes_item(organicvo));
		model.addAttribute("boyclothes_item", memberDAO.boyclothes_item(organicvo));
		model.addAttribute("babyclothes_item", memberDAO.babyclothes_item(organicvo));
		model.addAttribute("kitch_item", memberDAO.kitch_item(organicvo));
		model.addAttribute("bathroom_item", memberDAO.bathroom_item(organicvo));
		model.addAttribute("storage_item", memberDAO.storage_item(organicvo));
		model.addAttribute("cleaning_item", memberDAO.cleaning_item(organicvo));
		model.addAttribute("cosmetic_item", memberDAO.cosmetic_item(organicvo));
		model.addAttribute("beauty_item", memberDAO.beauty_item(organicvo));
		model.addAttribute("perfume_item", memberDAO.perfume_item(organicvo));
		return "forward:index.jsp";

	}

	@RequestMapping(value = "/modify.do", method = RequestMethod.GET) // 정보수정
	public String modifyTest(HttpSession session, Model model) throws Exception {

		if (session.getAttribute("id") != null) {

			String id = (String) session.getAttribute("id");

			MemberVO vo = new MemberVO();
			vo.setId(id);
			MemberDAOJDBC memberDAO = new MemberDAOJDBC();

			MemberVO voForModify = new MemberVO();
			voForModify = memberDAO.memberModify(vo);

			model.addAttribute("voForModify", voForModify);

			return "modify";

		} else {
			return "modify";
		}
	}

	@RequestMapping(value = "/inputToBasket.do", method = RequestMethod.POST, produces = "application/text;charset=utf8")
	public @ResponseBody String inputToBasket(@RequestParam("p_name") String pname, BasketVO vo, HttpSession session)
			throws Exception {
		String loginId = (String) session.getAttribute("id");
		String result = null;
		System.out.println(loginId);

		if (loginId != null) {

			// 장바구니DB에 넣기
			MemberDAOJDBC memberDAO = new MemberDAOJDBC();
			result = memberDAO.inputToBasket(vo, loginId);

			return result;

		} else {

			return null;
			// 로그인창 이동 추후구현

		}

	}

	@RequestMapping(value = "/clickorder.do", method = RequestMethod.POST) // 즉시구매 버튼을 클릭하였을때
	public String clickOrder(OrderVO orderVo, HttpSession session, Model model, organicVO organicvo) throws Exception {
		String loginId = (String) session.getAttribute("id");

		MemberVO membervo;
		if (loginId != null) {

			System.out.println("아 ㅡㅡ" + organicvo.getP_stock());

			model.addAttribute("ordervo", orderVo);
			model.addAttribute("organicvo", organicvo);
			MemberVO vo = new MemberVO();
			vo.setId(loginId);
			MemberDAOJDBC memberDAO = new MemberDAOJDBC();

			MemberVO covo = new MemberVO();
			covo = memberDAO.clickorder(vo); // covo = clickorder

			model.addAttribute("covo", covo);
			System.out.println("나와라" + covo.getEmail());

			return "ordernow";

		} else {

			return "forward:login.jsp";

		}

	}

	@RequestMapping(value = "/modifyMember.do", method = RequestMethod.POST)
	public String modifyfinal(HttpServletRequest req, HttpSession session, Model model) throws Exception {
		System.out.println("완료되나?");

		if (session.getAttribute("id") != null) {

			boolean modifyResult = false;

			String id = (String) session.getAttribute("id");
			String pw = req.getParameter("pw");
			String tel1 = req.getParameter("tel1");
			String tel2 = req.getParameter("tel2");
			String tel3 = req.getParameter("tel3");
			String email = req.getParameter("email");
			String address1 = req.getParameter("Address1");
			String address2 = req.getParameter("Address2");
			String address3 = req.getParameter("Address3");

			MemberVO vo = new MemberVO();
			vo.setId(id);
			vo.setPw(pw);
			vo.setTel1(tel1);
			vo.setTel2(tel2);
			vo.setTel3(tel3);
			vo.setEmail(email);
			vo.setAddress1(address1);
			vo.setAddress2(address2);
			vo.setAddress3(address3);

			MemberDAOJDBC memberDAO = new MemberDAOJDBC();

			modifyResult = memberDAO.updateMember(vo);
		}

		return "forward:mypage.jsp";

	}

	@RequestMapping(value = "/shoppingbasket.do", method = RequestMethod.GET)
	public String shoppingbasket(HttpSession session, Model mv) throws Exception {

		// 로그인 아이디 가져오기(장바구니DB에서 해당 아이디의 장바구니를 가져오기 위해서)
		String loginId = (String) session.getAttribute("id");

		// 장바구니DB접속해서 가져오기
		MemberDAOJDBC memberDAO = new MemberDAOJDBC();
		List<BasketVO> basketList = new ArrayList<BasketVO>();

		basketList = memberDAO.shoppingbasket(loginId);

		int totalSum = 0;

		for (int a = 0; a < basketList.size(); a++) {

			BasketVO vo = new BasketVO();
			vo = basketList.get(a);

			totalSum += vo.getP_amount() * vo.getP_price();

		}

		// 모델에 ArrayList객체 담아서 보내기. ArrayList객체는 해당 아이디의 장바구니정보를 담고 있다.
		mv.addAttribute("basketList", basketList);
		mv.addAttribute("totalSum", totalSum);

		System.out.println("총 합계 : " + totalSum);

		return "forward:shoppingbasket.jsp";

	}

	@RequestMapping(value = "/ordercomplete.do", method = RequestMethod.POST) // 바로구매에서 결제하기 누름
	public String ordercomplete(OrderVO ordervo, MemberDAOJDBC memberDAO, HttpSession session, Model model,
			HttpServletRequest req) throws Exception {

		System.out.println("ordernow에서 넘어오기 성공?");
		String loginId = (String) session.getAttribute("id");
		String droneAddress = ordervo.getDroneaddress();

		String orderNumber = memberDAO.insertordercomplete(ordervo, loginId);

		System.out.println(ordervo.getPaymethod() + "결제방법");
		String name = ordervo.getName();

		int p_amount = ordervo.getP_amount();
		int p_stock = Integer.parseInt(req.getParameter("p_stock"));

		organicVO organicvo = new organicVO();

		organicvo.setP_stock(p_stock);
		System.out.println("이게 나와야 한다 " + p_amount);
		System.out.println(p_stock);

		memberDAO.contorolStock(ordervo, organicvo);

		model.addAttribute("name", name);
		model.addAttribute("orderNumber", orderNumber);
		model.addAttribute("droneAddress", droneAddress);

		return "ordercomplete";

	}

	@RequestMapping(value = "/deletebasket.do", method = RequestMethod.GET) // 장바구니 삭제
	public String deletebasket(BasketVO basketvo, MemberDAOJDBC memberDAO, HttpSession session, Model mv,
			HttpServletRequest req) throws Exception {

		System.out.println("삭제로 넘어온다.");

		String[] basketNum = req.getParameterValues("checkArray");

		memberDAO.deleteBasket(basketvo, basketNum);

		return null;
	}

	@RequestMapping(value = "/orderdelivery.do", method = RequestMethod.GET) // MYPAGE에서 주문배송 눌렀을때 리스트 뜨기

	public String orderlist(HttpSession session, MemberDAOJDBC memberDAO, Model mv) throws Exception {

		String loginId = (String) session.getAttribute("id");

		List<OrderVO> orderListVO = new ArrayList<OrderVO>();

		orderListVO = memberDAO.allorderlist(loginId);

		mv.addAttribute("orderListVO", orderListVO);

		return "forward:orderdelivery.jsp";

	}

	@RequestMapping(value = "/buybasket1.do", method = RequestMethod.POST)
	public String text(HttpServletRequest req, OrderVO orderVo, HttpSession session, Model model) throws Exception {

		System.out.println("넘어온다");

		String id = (String) session.getAttribute("id");

		String allpay_price = req.getParameter("allpay_price");

		if (id != null) {

			// 주문/결제창 구매자 정보 불러오기 id값에 로그인 되어있다면

			MemberVO vo = new MemberVO();
			vo.setId(id);
			MemberDAOJDBC memberDAO = new MemberDAOJDBC();

			MemberVO covo = new MemberVO();
			covo = memberDAO.clickorder(vo); //

			model.addAttribute("covo", covo);
			System.out.println("나와라" + covo.getEmail());

		} else {

			return "forward:login.jsp";

		}

		// 장바구니에서 선택한 상품 가져오기 .

		String[] basketNum = req.getParameterValues("shoppinglist");

		List<BasketVO> basketorderList = new ArrayList<BasketVO>();

		for (int i = 0; i < basketNum.length; i++) {

			MemberDAOJDBC memberDAO = new MemberDAOJDBC();
			BasketVO basketvo = new BasketVO();

			basketvo = memberDAO.basketorderList(basketNum[i], id);

			basketorderList.add(basketvo);

		}

		model.addAttribute("basketorderList", basketorderList);

		model.addAttribute("allpay_price", allpay_price);

		System.out.println("이게 총액 :" + allpay_price);

		return "basketorder";
	}

	@RequestMapping(value = "/basketordercomplete.do", method = RequestMethod.POST) // 장바구니 -->주문결제 -->결제완료 클릭
	public String basketordercomplete(HttpServletRequest req, HttpSession session, Model model) throws Exception {

		System.out.println("baksetorder에서 잘 넘어온다.");
		String id = (String) session.getAttribute("id");

		String name = req.getParameter("name");
		String address1 = req.getParameter("address1");
		String address2 = req.getParameter("address2");
		String address3 = req.getParameter("address3");
		String tel1 = req.getParameter("tel1");
		String tel2 = req.getParameter("tel2");
		String tel3 = req.getParameter("tel3");
		String request = req.getParameter("request");
		int allpay_price = Integer.parseInt(req.getParameter("allpay_price"));
		String paymethod = req.getParameter("paymethod");
		String p_delivery = req.getParameter("p_delivery");
		String droneaddress = req.getParameter("droneaddress");

		OrderVO getOrderInfo = new OrderVO();

		getOrderInfo.setId(id);
		getOrderInfo.setName(name);
		getOrderInfo.setAddress1(address1);
		getOrderInfo.setAddress2(address2);
		getOrderInfo.setAddress3(address3);
		getOrderInfo.setTel1(tel1);
		getOrderInfo.setTel2(tel2);
		getOrderInfo.setTel3(tel3);
		getOrderInfo.setRequest(request);
		getOrderInfo.setAllpay_price(allpay_price);
		getOrderInfo.setPaymethod(paymethod);
		getOrderInfo.setP_delivery(p_delivery);
		getOrderInfo.setDroneaddress(droneaddress);

		System.out.println(getOrderInfo.getName());
		System.out.println(getOrderInfo.getP_delivery());

		MemberDAOJDBC memberDAO = new MemberDAOJDBC();
		String[] basketNum = req.getParameterValues("basketnum");
		String[] p_name = req.getParameterValues("p_name");
		String[] p_category = req.getParameterValues("p_category");

		String[] p_amount = req.getParameterValues("p_amount");

		for (int a = 0; a < basketNum.length; a++) {
			System.out.println("상품넘버 : " + basketNum[a]);

			memberDAO.orderDeliveryRecord(basketNum[a], getOrderInfo);
			int p_amount2 = Integer.parseInt(p_amount[a]);
			memberDAO.controlbasketstock(p_amount2, p_name[a], p_category[a]);
			System.out.println(p_amount2 + "나와");
			System.out.println(p_name[0] + "나와");
			System.out.println(p_category[0] + "나와");

		}

		model.addAttribute("name", name);

		return "ordercomplete";

	}

	@RequestMapping(value = "/deleteproduct.do", method = RequestMethod.POST)
	public String deleteproduct(organicVO organicvo, MemberDAOJDBC memberDAO, PageMaker page, Model model,
			HttpServletRequest req) throws Exception {

		memberDAO.admindelete(organicvo);

		String path = "C:\\eGovFrameDev-3.8.0-64bit\\workspace\\0524.zip_expanded\\RddServiceicia12\\src\\main\\webapp\\imgs\\text/";
		String path2 = "C:\\eGovFrameDev-3.8.0-64bit\\workspace\\0524.zip_expanded\\RddServiceicia12\\src\\main\\webapp\\";

		String img = organicvo.getP_photourl();
		System.out.println("img : " + img);

		File file = new File(path);
		String[] filelist = file.list();
		int fcontent = file.list().length;

		String childpath = "";

		for (int i = 0; i < fcontent; i++) {
			childpath = "imgs/text/" + filelist[i];
			System.out.println("filelist : " + childpath);

			if (childpath.equals(img)) {
				String realPath = path2 + childpath;

				File f = new File(realPath);
				f.delete();
			}
		}

		int num = page.getPagenum();
		int relapagenum = page.getPagenum();
		if (num < 1) {
			num = 1;
		} else if (relapagenum < 1) {
			num = 1;
		}

		System.out.println("제발 떠라 : " + num);

		page.setTotalcount(memberDAO.pageTotal(page, organicvo));
		page.setPagenum(num - 1); // 현재 페이지를 페이지 객체에 지정한다
		page.setCurrentblock(num); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다
		page.setLastblock(page.getTotalcount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정한다

		page.prevnext(num); // 현재 페이지 번호로 화살표 여부를 지정한다.
		page.setStartPage(page.getCurrentblock()); // 시작 페이지를 페이지 블록 번호로 정한다
		page.setEndPage(page.getLastblock(), page.getCurrentblock());
		// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다

		System.out.println("현재 페이지 번호" + page.getPagenum());

		model.addAttribute("pageval", relapagenum);

		model.addAttribute("organiclist", memberDAO.organicList(organicvo, page));

		model.addAttribute("page", page);

		return "food_organic";

	}

	@RequestMapping(value = "/sendcheckcode.do", method = RequestMethod.POST) // 아이디 중복확인
	public @ResponseBody String sendCheckCode(MemberVO vo, HttpSession session, HttpServletRequest request,
			HttpServletResponse response, MemberDAOJDBC memberDAO, Model model) throws Exception {

		System.out.println("이름 : " + vo.getName());
		System.out.println("tel1 : " + vo.getTel1());
		System.out.println("tel2 : " + vo.getTel2());
		System.out.println("tel3 : " + vo.getTel3());

		// sms발송기능을 구현하기 위한 변수들 시작
		String transferedRphone = vo.getTel1() + "-" + vo.getTel2() + "-" + vo.getTel3();
		String transgeredAction = request.getParameter("action");

		System.out.println("action 값 : " + transgeredAction);
		// sms발송기능을 구현하기 위한 변수들 끝

		System.out.println(transferedRphone);

		boolean result = memberDAO.checkNameAndPhone(vo);

		System.out.println(result);

		if (result) {

			String randomNum = "";
			int d = 0;
			for (int i = 1; i <= 6; i++) {
				Random r = new Random();
				d = r.nextInt(9);
				randomNum = randomNum + Integer.toString(d);
			}

			System.out.println(randomNum);

			// 상당히 어려운 sms 발송 시작

			System.out.println("컨트롤러 SMS 서비스 입장");
			String charsetType = "UTF-8"; // EUC-KR 또는 UTF-8
			request.setCharacterEncoding(charsetType);
			response.setCharacterEncoding(charsetType);
			String action = nullcheck(transgeredAction, "");

			if (action.equals("go")) {

				String sms_url = "";
				sms_url = "https://sslsms.cafe24.com/sms_sender.php"; // SMS 전송요청 URL
				String user_id = base64Encode("leeboram92"); // SMS아이디
				String secure = base64Encode("3f900d01fa8066cc5c740c533a67e774 ");// 인증키
				String msg = base64Encode(nullcheck(randomNum, ""));
				String rphone = base64Encode(nullcheck(transferedRphone, ""));
				String sphone1 = base64Encode("010");
				String sphone2 = base64Encode("6676");
				String sphone3 = base64Encode("3893");

				String rdate = base64Encode(nullcheck(request.getParameter("rdate"), ""));
				String rtime = base64Encode(nullcheck(request.getParameter("rtime"), ""));
				String mode = base64Encode("1");
				String subject = "";
				if (nullcheck(request.getParameter("smsType"), "").equals("L")) {
					subject = base64Encode(nullcheck(request.getParameter("subject"), ""));
				}
				String testflag = base64Encode(nullcheck(request.getParameter("testflag"), ""));
				String destination = base64Encode(nullcheck(request.getParameter("destination"), ""));
				String repeatFlag = base64Encode(nullcheck(request.getParameter("repeatFlag"), ""));
				String repeatNum = base64Encode(nullcheck(request.getParameter("repeatNum"), ""));
				String repeatTime = base64Encode(nullcheck(request.getParameter("repeatTime"), ""));
				String returnurl = nullcheck(request.getParameter("returnurl"), "");
				String nointeractive = nullcheck(request.getParameter("nointeractive"), "");
				String smsType = base64Encode(nullcheck(request.getParameter("smsType"), ""));

				String[] host_info = sms_url.split("/");
				String host = host_info[2];
				String path = "/" + host_info[3];
				int port = 80;

				// 데이터 맵핑 변수 정의
				String arrKey[] = new String[] { "user_id", "secure", "msg", "rphone", "sphone1", "sphone2", "sphone3",
						"rdate", "rtime", "mode", "testflag", "destination", "repeatFlag", "repeatNum", "repeatTime",
						"smsType", "subject" };
				String valKey[] = new String[arrKey.length];
				valKey[0] = user_id;
				valKey[1] = secure;
				valKey[2] = msg;
				valKey[3] = rphone;
				valKey[4] = sphone1;
				valKey[5] = sphone2;
				valKey[6] = sphone3;
				valKey[7] = rdate;
				valKey[8] = rtime;
				valKey[9] = mode;
				valKey[10] = testflag;
				valKey[11] = destination;
				valKey[12] = repeatFlag;
				valKey[13] = repeatNum;
				valKey[14] = repeatTime;
				valKey[15] = smsType;
				valKey[16] = subject;

				String boundary = "";
				Random rnd = new Random();
				String rndKey = Integer.toString(rnd.nextInt(32000));
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] bytData = rndKey.getBytes();
				md.update(bytData);
				byte[] digest = md.digest();
				for (int i = 0; i < digest.length; i++) {
					boundary = boundary + Integer.toHexString(digest[i] & 0xFF);
				}
				boundary = "---------------------" + boundary.substring(0, 11);

				// 본문 생성
				String data = "";
				String index = "";
				String value = "";
				for (int i = 0; i < arrKey.length; i++) {
					index = arrKey[i];
					value = valKey[i];
					data += "--" + boundary + "\r\n";
					data += "Content-Disposition: form-data; name=\"" + index + "\"\r\n";
					data += "\r\n" + value + "\r\n";
					data += "--" + boundary + "\r\n";
				}

				// out.println(data);

				InetAddress addr = InetAddress.getByName(host);
				Socket socket = new Socket(host, port);
				// 헤더 전송
				BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charsetType));
				wr.write("POST " + path + " HTTP/1.0\r\n");
				wr.write("Content-Length: " + data.length() + "\r\n");
				wr.write("Content-type: multipart/form-data, boundary=" + boundary + "\r\n");
				wr.write("\r\n");

				// 데이터 전송
				wr.write(data);
				wr.flush();

				// 결과값 얻기
				BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(), charsetType));
				String line;
				String alert = "";
				ArrayList tmpArr = new ArrayList();
				while ((line = rd.readLine()) != null) {
					tmpArr.add(line);
				}
				wr.close();
				rd.close();

				String tmpMsg = (String) tmpArr.get(tmpArr.size() - 1);
				System.out.println(tmpMsg);
				String[] rMsg = tmpMsg.split(",");
				String Result = rMsg[0]; // 발송결과
				String Count = ""; // 잔여건수
				if (rMsg.length > 1) {
					Count = rMsg[1];
				}
				int SMSresult = 0;
				// 발송결과 알림
				if (Result.equals("success")) {
					SMSresult = 1;
				} else if (Result.equals("3205")) {
					SMSresult = 2;
				}
				// System.out.println("결과값 " + SMSresult);
				// response.getWriter().write(SMSresult + "");
			}

			// 상당히 어려운 sms 발송 끝

			return randomNum;
		} else {

			return "fail";
		}

	}

	// sms발송을위한 컨트롤러

	@RequestMapping(value = "/SMSService.do", method = RequestMethod.GET)
	public void sendSMSService(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("컨트롤러 SMS 서비스 입장");
		String charsetType = "UTF-8"; // EUC-KR 또는 UTF-8
		request.setCharacterEncoding(charsetType);
		response.setCharacterEncoding(charsetType);
		String action = nullcheck(request.getParameter("action"), "");

		if (action.equals("go")) {

			String sms_url = "";
			sms_url = "https://sslsms.cafe24.com/sms_sender.php"; // SMS 전송요청 URL
			String user_id = base64Encode("leeboram92"); // SMS아이디
			String secure = base64Encode("3f900d01fa8066cc5c740c533a67e774 ");// 인증키
			String msg = base64Encode(nullcheck(request.getParameter("msg"), ""));
			String rphone = base64Encode(nullcheck(request.getParameter("rphone"), ""));
			String sphone1 = base64Encode("010");
			String sphone2 = base64Encode("5331");
			String sphone3 = base64Encode("1850");

			String rdate = base64Encode(nullcheck(request.getParameter("rdate"), ""));
			String rtime = base64Encode(nullcheck(request.getParameter("rtime"), ""));
			String mode = base64Encode("1");
			String subject = "";
			if (nullcheck(request.getParameter("smsType"), "").equals("L")) {
				subject = base64Encode(nullcheck(request.getParameter("subject"), ""));
			}
			String testflag = base64Encode(nullcheck(request.getParameter("testflag"), ""));
			String destination = base64Encode(nullcheck(request.getParameter("destination"), ""));
			String repeatFlag = base64Encode(nullcheck(request.getParameter("repeatFlag"), ""));
			String repeatNum = base64Encode(nullcheck(request.getParameter("repeatNum"), ""));
			String repeatTime = base64Encode(nullcheck(request.getParameter("repeatTime"), ""));
			String returnurl = nullcheck(request.getParameter("returnurl"), "");
			String nointeractive = nullcheck(request.getParameter("nointeractive"), "");
			String smsType = base64Encode(nullcheck(request.getParameter("smsType"), ""));

			String[] host_info = sms_url.split("/");
			String host = host_info[2];
			String path = "/" + host_info[3];
			int port = 80;

			// 데이터 맵핑 변수 정의
			String arrKey[] = new String[] { "user_id", "secure", "msg", "rphone", "sphone1", "sphone2", "sphone3",
					"rdate", "rtime", "mode", "testflag", "destination", "repeatFlag", "repeatNum", "repeatTime",
					"smsType", "subject" };
			String valKey[] = new String[arrKey.length];
			valKey[0] = user_id;
			valKey[1] = secure;
			valKey[2] = msg;
			valKey[3] = rphone;
			valKey[4] = sphone1;
			valKey[5] = sphone2;
			valKey[6] = sphone3;
			valKey[7] = rdate;
			valKey[8] = rtime;
			valKey[9] = mode;
			valKey[10] = testflag;
			valKey[11] = destination;
			valKey[12] = repeatFlag;
			valKey[13] = repeatNum;
			valKey[14] = repeatTime;
			valKey[15] = smsType;
			valKey[16] = subject;

			String boundary = "";
			Random rnd = new Random();
			String rndKey = Integer.toString(rnd.nextInt(32000));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytData = rndKey.getBytes();
			md.update(bytData);
			byte[] digest = md.digest();
			for (int i = 0; i < digest.length; i++) {
				boundary = boundary + Integer.toHexString(digest[i] & 0xFF);
			}
			boundary = "---------------------" + boundary.substring(0, 11);

			// 본문 생성
			String data = "";
			String index = "";
			String value = "";
			for (int i = 0; i < arrKey.length; i++) {
				index = arrKey[i];
				value = valKey[i];
				data += "--" + boundary + "\r\n";
				data += "Content-Disposition: form-data; name=\"" + index + "\"\r\n";
				data += "\r\n" + value + "\r\n";
				data += "--" + boundary + "\r\n";
			}

			// out.println(data);

			InetAddress addr = InetAddress.getByName(host);
			Socket socket = new Socket(host, port);
			// 헤더 전송
			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charsetType));
			wr.write("POST " + path + " HTTP/1.0\r\n");
			wr.write("Content-Length: " + data.length() + "\r\n");
			wr.write("Content-type: multipart/form-data, boundary=" + boundary + "\r\n");
			wr.write("\r\n");

			// 데이터 전송
			wr.write(data);
			wr.flush();

			// 결과값 얻기
			BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(), charsetType));
			String line;
			String alert = "";
			ArrayList tmpArr = new ArrayList();
			while ((line = rd.readLine()) != null) {
				tmpArr.add(line);
			}
			wr.close();
			rd.close();

			String tmpMsg = (String) tmpArr.get(tmpArr.size() - 1);
			System.out.println(tmpMsg);
			String[] rMsg = tmpMsg.split(",");
			String Result = rMsg[0]; // 발송결과
			String Count = ""; // 잔여건수
			if (rMsg.length > 1) {
				Count = rMsg[1];
			}
			int SMSresult = 0;
			// 발송결과 알림
			if (Result.equals("success")) {
				SMSresult = 1;
			} else if (Result.equals("3205")) {
				SMSresult = 2;
			}
			System.out.println("결과값 " + SMSresult);
			response.getWriter().write(SMSresult + "");
		}
	}

	public static String nullcheck(String str, String Defaultvalue) throws Exception {
		String ReturnDefault = "";
		if (str == null) {
			ReturnDefault = Defaultvalue;
		} else if (str == "") {
			ReturnDefault = Defaultvalue;
		} else {
			ReturnDefault = str;
		}
		return ReturnDefault;
	}

	public static String base64Encode(String str) throws java.io.IOException {
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		byte[] strByte = str.getBytes();
		String result = encoder.encode(strByte);
		return result;
	}

	public static String base64Decode(String str) throws java.io.IOException {
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		byte[] strByte = decoder.decodeBuffer(str);
		String result = new String(strByte);
		return result;
	}

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = "/searchPw.do", method = RequestMethod.POST) // 비밀번호 찾기를 눌렀을때
	public @ResponseBody String searchPassword(Email email, MemberVO vo, HttpServletResponse resp,
			MemberDAOJDBC memberDAO, HttpSession session) throws Exception {

		System.out.println("비번 찾기!!!");

		SearchPwService searchpwservice = new SearchPwService();
		MemberVO searchVO = searchpwservice.searchPw(vo);

		if (vo.getEmail().equals(searchVO.getEmail())) {

			String receiveId = searchVO.getId();
			String receiveEmail = searchVO.getEmail();
			String receivePassword = searchVO.getPw();
			String receiveName = searchVO.getName();

			email.setContent(receiveName + "님의 아이디는" + receiveId + "입니다." + "비밀번호는" + receivePassword + "입니다"
					+ "보안을 위하여 정보수정페이지에서 비밀번호 변경을 부탁드립니다.");
			email.setReceiver(receiveEmail); // 이메일을 받는사람
			email.setSubject("드론팡" + receiveName + "님께서 요청하신 아이디와 이메일입니다.");

			try {

				MimeMessage msg = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");

				messageHelper.setSubject(email.getSubject());
				messageHelper.setText(email.getContent());
				messageHelper.setTo(email.getReceiver());

				msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email.getReceiver()));
				mailSender.send(msg);

			} catch (Exception e) {

			}

			return "success";
		} else {

			System.out.println("이메일이 안맞음");
			return "fail";
		}

	}

	@RequestMapping(value = "/sendEmailAciton.do", method = RequestMethod.GET)
	public String sendemailaction(MemberVO vo, MemberDAOJDBC memberDAO, Email email, HttpSession session) {

		System.out.println(session.getAttribute("email"));
		System.out.println(session.getAttribute("password"));
		System.out.println(session.getAttribute("name"));
		String receiveId = (String) session.getAttribute("id");
		String receiveEmail = (String) session.getAttribute("email");
		String receivePassword = (String) session.getAttribute("password");
		String receiveName = (String) session.getAttribute("name");

		email.setContent(receiveName + "님의 아이디는" + receiveId + "입니다." + "비밀번호는" + receivePassword + "입니다"
				+ "보안을 위하여 정보수정페이지에서 비밀번호 변경을 부탁드립니다.");
		email.setReceiver(receiveEmail); // 이메일을 받는사람
		email.setSubject("드론팡" + receiveName + "님께서 요청하신 아이디와 이메일입니다.");

		try {

			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");

			messageHelper.setSubject(email.getSubject());
			messageHelper.setText(email.getContent());
			messageHelper.setTo(email.getReceiver());

			msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(email.getReceiver()));
			mailSender.send(msg);

		} catch (Exception e) {

		}

		return "forward:login.jsp";

	}

	@RequestMapping(value = "/iteminsert.do", method = RequestMethod.POST)
	public String fileUpload(HttpServletRequest req, organicVO vo, Model model) throws Exception {

		String path = "C:\\eGovFrameDev-3.8.0-64bit\\workspace\\0524.zip_expanded\\RddServiceicia12\\src\\main\\webapp\\imgs\\text/";
		/* String path = req.getServletContext().getRealPath("/imgs/sample/"); */
		System.out.println("path : " + path);

		int sizeLimit = 1024 * 1024 * 15;

		File dir = new File(path);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		try {
			// from에서 enctype="multipart/form-data" 사용해서 데이터를 받아온다면 MultipartRequest 객체를
			// 이용해서 받아야한다
			// ex) MultipartRequest.getParameter("name값")
			MultipartRequest multi = new MultipartRequest(req, path, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

			String fileRealname = multi.getOriginalFileName("file");

			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + multi.getOriginalFileName("file");
			System.out.println("filename : " + fileName);

			File realfile = new File(path + fileRealname);
			realfile.renameTo(new File(path + fileName));

			String uplodurl = "imgs/text/" + fileName;

			vo.setP_tabler(multi.getParameter("p_tabler"));
			vo.setP_category(multi.getParameter("p_category"));
			vo.setP_name(multi.getParameter("p_name"));
			vo.setP_stock(Integer.parseInt(multi.getParameter("p_stock")));
			vo.setP_price(Integer.parseInt(multi.getParameter("p_price")));
			vo.setP_explain(multi.getParameter("p_explain"));
			vo.setP_photourl(uplodurl);

			MemberDAOJDBC member = new MemberDAOJDBC();
			member.iteminsert(vo);

			model.addAttribute("organic_item", member.organic_item(vo));
			model.addAttribute("processedfood_item", member.processedfood_item(vo));
			model.addAttribute("junkfood_item", member.junkfood_item(vo));
			model.addAttribute("dringkcafe_item", member.dringkcafe_item(vo));
			model.addAttribute("girlclothes_item", member.girlclothes_item(vo));
			model.addAttribute("boyclothes_item", member.boyclothes_item(vo));
			model.addAttribute("babyclothes_item", member.babyclothes_item(vo));
			model.addAttribute("kitch_item", member.kitch_item(vo));
			model.addAttribute("bathroom_item", member.bathroom_item(vo));
			model.addAttribute("storage_item", member.storage_item(vo));
			model.addAttribute("cleaning_item", member.cleaning_item(vo));
			model.addAttribute("cosmetic_item", member.cosmetic_item(vo));
			model.addAttribute("beauty_item", member.beauty_item(vo));
			model.addAttribute("perfume_item", member.perfume_item(vo));

		} catch (IOException e) {
			e.printStackTrace();

		}

		return "mainPage";

	}

	@RequestMapping(value = "/itemmodify.do", method = RequestMethod.GET)
	public String adminselect_modify(HttpServletRequest req, organicVO vo, Model model, PageMaker page)
			throws Exception {

		System.out.println("페이지값 : " + page.getPagenum());

		MemberDAOJDBC member = new MemberDAOJDBC();

		model.addAttribute("pageval", page.getPagenum());
		model.addAttribute("select", member.itemmodify(vo));

		return "adminselect_updata";
	}

	@RequestMapping(value = "/itemupdate.do", method = RequestMethod.POST)
	public String adminselect_update(HttpServletRequest req, organicVO vo, Model model, HttpServletResponse resp,
			PageMaker page) throws Exception {

		String path = "C:\\eGovFrameDev-3.8.0-64bit\\workspace\\0524.zip_expanded\\RddServiceicia12\\src\\main\\webapp\\imgs\\text/";
		// 업로드 파일 저장경로

		String path2 = "C:\\eGovFrameDev-3.8.0-64bit\\workspace\\0524.zip_expanded\\RddServiceicia12\\src\\main\\webapp\\";
		// 파일 업로드 될시 기존파일 삭제하는 경로

		int sizeLimit = 1024 * 1024 * 15;

		File dir = new File(path);
		if (!dir.isDirectory()) { // imgs/text 폴더가 없다면 생성해라
			dir.mkdirs();
		}

		try {
			// from에서 enctype="multipart/form-data" 사용해서 데이터를 받아온다면 MultipartRequest 객체를
			// 이용해서 받아야한다
			// ex) MultipartRequest.getParameter("name값")
			MultipartRequest multi = new MultipartRequest(req, path, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

			String fileRealname = multi.getOriginalFileName("file");
			// 업로드 된 파일 이름

			UUID uuid = UUID.randomUUID(); // 랜덤값으로 변경할 파일 이름
			String fileName = uuid + "_" + multi.getOriginalFileName("file");
			System.out.println("filename : " + fileName);

			String uplodurl = "";
			if (fileRealname != null) { // 첨부파일이 있다면 기존파일은 삭제해라
				File file = new File(path); // 해당경로로 들어가서
				String[] filelist = file.list(); // 해당 파일목록을 받아온다
				int fcontent = filelist.length; // 갯수를 구하고

				File realfile = new File(path + fileRealname);
				realfile.renameTo(new File(path + fileName));
				// 업로드된 파일 이름을 랜덤값으로 변경된 이름으로 바꿔라

				String childPath = "";

				for (int i = 0; i < fcontent; i++) {
					// 파일의 갯수만큼 for문을 돌리고
					childPath = "imgs/text/" + filelist[i];
					// 리스트를 읽어라
					System.out.println("childPath : " + childPath);

					if (childPath.equals(vo.getP_photourl())) {
						// 읽은 리스트중에서 받아온 img url 값이 있다면
						String realPath = path2 + childPath;
						// 해당 폴더로 가서

						System.out.println("realPath : " + realPath);
						File f = new File(realPath);
						// 삭제해라
						f.delete();
					}
				}

				uplodurl = "imgs/text/" + fileName;
				// 변경된 파일을 저장한다.

			} else {

				uplodurl = vo.getP_photourl();
				// 파일이 없다면 받아온 img url 값을 저장한다

			}

			vo.setP_number(multi.getParameter("p_number"));
			System.out.println("번호 : " + vo.getP_number());

			vo.setP_tabler(multi.getParameter("p_tabler"));
			vo.setP_category(multi.getParameter("p_category"));
			vo.setP_name(multi.getParameter("p_name"));
			vo.setP_stock(Integer.parseInt(multi.getParameter("p_stock")));
			vo.setP_price(Integer.parseInt(multi.getParameter("p_price")));
			vo.setP_explain(multi.getParameter("p_explain"));
			vo.setP_photourl(uplodurl);
			// 받아온 파라미터값을 vo에 저장하고

			System.out.println(vo.getP_photourl());

			MemberDAOJDBC member = new MemberDAOJDBC();
			member.itemupdata(vo);
			// 저장된 vo를 가지고 mysql에 update 쿼리문을 사용한다

			model.addAttribute("pageval", page.getPagenum());
			model.addAttribute("select", member.selectorganic(vo));
			// 저장한 vo를 return 한다.

		} catch (IOException e) {
			e.printStackTrace();

		}

		return "adminselect_product";
	}

}