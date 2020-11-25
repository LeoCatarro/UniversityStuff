package org.therestaurant.tweb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddToWaitingList", urlPatterns = {"waitingList"}, loadOnStartup = 1) 
public class AddToWaitingList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.getWriter().append("Welcome to The Restaurant. ");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");	//Guarda a string passada em name

		if (name == null)
			name = "World";

		request.setAttribute("user", name);
		request.getRequestDispatcher("addClientToWaitingListResponse.jsp").forward(request, response);


	}
}
