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

    public static final int PAGESIZE = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");

        ArrayList<String> pageItems = (ArrayList<String>) session.getAttribute("pageItems");
         String action = (String) request.getAttribute("action");
       
        if (action!=null && action.equals("logout")) {
     session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
        String page = request.getParameter("page");
       
        Integer pages= (Integer) session.getAttribute("page");
        if(page ==null){
                        session.setAttribute("page",1);

        }

        if (items!=null && page != null) {
            int pageN = Integer.parseInt(page);
          
           pageItems.clear();
            for (int i = (pageN - 1) * 10; i<items.size() && i < (pageN - 1) * 10 + PAGESIZE; i++) {
                
                pageItems.add(items.get(i));
        
            }
          session.setAttribute("page",pageN);
           session.setAttribute("pageItems",pageItems);
         System.out.println(session.getAttribute("page"));
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
         if (action == null) {
            session.setAttribute("username", null);
            session.setAttribute("items", null);
            session.setAttribute("pageItems", null);
            
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
        }
       

    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        ArrayList<String> pageItems = (ArrayList<String>) session.getAttribute("pageItems");

        if (items == null) {
            items = new ArrayList<>();
        }
        if (pageItems == null) {
            pageItems = new ArrayList<>();
        }
        int size = items.size();
        session.setAttribute("size", size);
        String action = request.getParameter("action");
        Integer page=(Integer) session.getAttribute("page");
        if(page==null){
        session.setAttribute("page",1);
        }
        if (action.equals("add")) {
            String theItem = request.getParameter("item");
            items.add((String) request.getAttribute("item"));
            items.set(items.size() - 1, items.size() + ". " + theItem);
            if (pageItems != null && pageItems.size() < 10) {
                pageItems.add(items.get(items.size() - 1));

            }
            session.setAttribute("items", items);
            session.setAttribute("pageItems", pageItems);
            //session.setAttribute("page",1+items.size()/10);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        } else if (action.equals("delete")) {
            String beRemoved = request.getParameter("item");
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).equals(beRemoved)) {
                    items.remove(i);
                }
            }
            for (int i = 0; i < pageItems.size(); i++) {
                if (pageItems.get(i).equals(beRemoved)) {
                    pageItems.remove(i);
                }
            }
            session.setAttribute("items", items);
            session.setAttribute("pageItems", pageItems);
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
