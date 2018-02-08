package lv.tsi.javaweb.seabattle.controller;

import lv.tsi.javaweb.seabattle.model.PlayerGameContext;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Dimitrijs Fedotovs <a href="http://www.bug.guru">www.bug.guru</a>
 * @version 1.0
 * @since 1.0
 */
@WebServlet(name = "WaitEnemyPlacementServlet", urlPatterns = "waitEnemyPlacement")
public class WaitEnemyPlacementServlet extends HttpServlet {
    @Inject
    private PlayerGameContext playerGameContext;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (playerGameContext.getGame().isReadyToStart()) {
            response.sendRedirect("game");
        } else {
            request.getRequestDispatcher("/WEB-INF/waitEnemyPlacement.jsp")
                    .include(request, response);
        }
    }
}
