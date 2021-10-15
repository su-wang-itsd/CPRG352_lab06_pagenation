/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 845593
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = (String) request.getAttribute("action");
        if(action==null){
             session.setAttribute("username", null);
            session.setAttribute("items",null);
             getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
        if (action.equals("logout")) {
            session.setAttribute("username", null);
            session.setAttribute("items",null);
             getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
      

       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        if(items==null){
        items=new ArrayList<>();
        }
        int size=items.size();
        session.setAttribute("size",size);
        String action = request.getParameter("action");
        
        if (action.equals("add")) {
            String theItem=request.getParameter("item");
            items.add((String) request.getAttribute("item"));
            items.set(items.size()-1, items.size()+". "+theItem);
            session.setAttribute("items",items);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        } else if (action.equals("delete")) {
            String beRemoved =request.getParameter("item");
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).equals(beRemoved)) {
                    items.remove(i);
                }
            }
            session.setAttribute("items",items);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        } else if (action.equals("register")) {
            String username = request.getParameter("username");
           if (username == null || username.trim().equals("")) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        } else {
              session.setAttribute("username", username);
               getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
         
        
        }

    }

}
