package com.servlets;


import com.Dao.LoginDao;
import com.beans.User;
import com.beans.WorkingHour;
import com.google.gson.Gson;
import com.services.ReportingServices;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

@WebServlet(name = "mainServlet", value = "/mainServlet")
public class MainServlet  extends HttpServlet {

    static Logger logger = null;


    @Override
    public void init() {
        logger = Logger.getRootLogger();
        logger.debug(" MainServlet.init Servlet... : ");
    }


    @Override
    public void destroy() {
        logger.info(" MainServlet.destroy : ");
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("INFO - MainServlet.service : ");
        logger.debug("DEBUG - MainServlet.service : ");
        doGet(request, response);
    }



    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info(" MainServlet.doGet : ");
        String action = request.getParameter("act");
        logger.info("MainServlet.doGet : " + "action is : " + action);

        try {
            if ("register".equals(action)) {
                Register(request, response);
                UsersDisplay(request, response);
            } else if ("login".equals(action)) {
                Login(request, response);
            } else if ("DeleteUser".equals(action)) {
                DeleteUser(request, response);
                UsersDisplay(request, response);
            } else if ("loadUsers".equals(action)) {
                UsersJSONDisplay(request, response);
            } else if ( "enter".equals(action) || "exit".equals(action)) {
                cardStamping(request, response);
            } else if ("view".equals(action)) {
                viewAllReports(request, response);
            } else {
                UsersDisplay(request, response);
            }
        } catch ( SQLException | ServletException e) {
            e.printStackTrace();
        }
    }




    private void cardStamping(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, SQLException {
        logger.info("MainServlet.cardStamping : " );

        HttpSession session = request.getSession();

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        User user = (User) session.getAttribute("user");
        String action = request.getParameter("act");
        boolean isCardStampOK = ReportingServices.cardStamping(user,action);
        List<WorkingHour> list = ReportingServices.fetchAllWorkingHours();
        Gson gson = new Gson();
        String listAsJSON = gson.toJson(list);
        request.setAttribute("reports", list);
        out.println(list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
        dispatcher.forward(request, response);

    }

    private void viewAllReports(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            logger.info("MainServlet.report : " );
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            List<WorkingHour> list = ReportingServices.fetchAllWorkingHours();
            //Gson gson = new Gson();
            //String listAsJSON = gson.toJson(list);
            //System.out.println(listAsJSON);
            //request.setAttribute("reports", listAsJSON);
            request.setAttribute("reports", list);
            out.println(list);
            RequestDispatcher dispatcher = request.getRequestDispatcher("reports.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }


    private void DeleteUser(HttpServletRequest request, HttpServletResponse response) {
        logger.info("MainServlet.DeleteUser : " );
        try {
            String userId = request.getParameter("id");
            ReportingServices.deleteUser(userId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }




    private void Register(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        logger.info("MainServlet.Register : " );
        String id = request.getParameter("identityNo");
        String fullname = (String) request.getParameter("fullName");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("psw");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFullName(fullname);
        user.setId(id);
        boolean isUserAdded = ReportingServices.addUser(user);
    }


    private void UsersJSONDisplay(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        logger.info("MainServlet.UsersJSONDisplay : " );
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        List<User> list = ReportingServices.fetchAllUsers();
        Gson gson = new Gson();
        String listAsJSON = gson.toJson(list);
        System.out.println(listAsJSON);
        request.setAttribute("list", listAsJSON);
        out.println(listAsJSON);
    }


    private void UsersDisplay(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            logger.info("MainServlet.UsersDisplay : " );
            List<User> list = ReportingServices.fetchAllUsers();
            request.setAttribute("list", list);
            String destPage = "users.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage()); e.printStackTrace();
        }
    }


    private void Login(HttpServletRequest request, HttpServletResponse response) {
        logger.info("MainServlet.Login : " );
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // String fullName = request.getParameter("fullName");
        LoginDao loginDao = new LoginDao();
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        try {
            User user1 = loginDao.validateLogin(user);
            String destPage = "login.jsp";
            if (user1 != null) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", user1);
                request.setAttribute("login_msg", "congradulations !!");
                destPage = "users.jsp";
                List<User> list = ReportingServices.fetchAllUsers();
                request.setAttribute("list", list);
            } else {

                String message = "Invalid email/password";
                request.setAttribute("login_msg", message);
                logger.error("MainServlet.Login Login failed !!! with email: " + email);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage()); e.printStackTrace();
        }
    }

}
