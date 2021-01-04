package in.co.sunrays.proj0.contoller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import in.co.sunrays.proj0.dto.TestDTO;
import in.co.sunrays.proj0.exception.DuplicateRecodException;
import in.co.sunrays.proj0.service.TestServiceInt;

@Controller
@RequestMapping("/TestCtl")
public class TestCtl {
	@Autowired
	private TestServiceInt testservice = null;

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public void send(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ooooooooooooo");
		request.setAttribute("a", "Rajkumar Meena");
		RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
		rd.forward(request, response);
		System.out.println("kkkkkkkkkkk");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView submit(HttpServletRequest request, HttpServletResponse response) {
		int i = 10;
		int j = 20;
		int k = i + j;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Welcome.jsp");
		mv.addObject("message", k);
		return mv;
	}

	@RequestMapping(value = "/testList", method = RequestMethod.POST)
	public String testList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("TestList MEthod========11111");
		TestDTO testDto = new TestDTO();
		int pageNo = 1;
		int pageSize = 10;
		List<TestDTO> list = testservice.search(testDto, pageNo, pageSize);

		Iterator it = list.iterator();
		TestDTO dto = null;
		while (it.hasNext()) {
			System.out.println("while loop");
			dto = (TestDTO) it.next();
			System.out.println("FirstName==   " + dto.getFirstname());
			System.out.println("LastName==   " + dto.getLastname());
		}
		System.out.println("TestList MEthod========2222222");
		return "";
	}

}
