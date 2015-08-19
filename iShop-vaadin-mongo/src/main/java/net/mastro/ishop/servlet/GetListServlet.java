package net.mastro.ishop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mastro.ishop.Dao;
import net.mastro.ishop.model.ShopItem;
import net.mastro.ishop.model.User;
import net.mastro.ishop.utility.Cfg;
import net.mastro.ishop.utility.Mapper;

import org.bson.types.BasicBSONList;

/**
 * Servlet implementation class GetListServlet
 */
public class GetListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetListServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = null;
		
		if (Cfg.getBoolean(Cfg.DEBUG_DISABLE_REST_AUTHENTICATION)) {
			
			if (Cfg.getBoolean(Cfg.DEBUG_USE_DEFAULT_USER)) {
				
				user = Dao.auth().authorize(Cfg.get(Cfg.DEBUG_DEFAULT_USER), Cfg.get(Cfg.DEBUG_DEFAULT_PASS));
				
			}
			
		}
		else {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			user = Dao.auth().authorize(username, password);
			
		}

		if ( user != null ) {
						
			BasicBSONList list = new BasicBSONList();
			for ( ShopItem item : Dao.shopItem().getShopItems(user)) {
				list.add(Mapper.map(item));
			}
			response.getOutputStream().write(list.toString().getBytes());
			
		}
		else {

			response.getOutputStream().write("ERROR".getBytes());

		}

	}

}
