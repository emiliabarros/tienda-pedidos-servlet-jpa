package com.tienda.servlet;

import com.tienda.managerdb.PedidoManager;
import com.tienda.service.PedidoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/pedidos")
public class PedidoServlet extends HttpServlet {

    private PedidoService pedidoService;
    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            emf = Persistence.createEntityManagerFactory("tiendaPU");
            EntityManager em = emf.createEntityManager();
            PedidoManager pedidoManager = new PedidoManager(em);
            pedidoService = new PedidoService(pedidoManager);
        } catch (Exception e) {
            throw new ServletException("Error inicializando JPA / Hibernate", e);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setContentType("application/json");

        String idParam = req.getParameter("id");
        String jsonResponse;

        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                jsonResponse = pedidoService.obtenerPedidoPorIdComoJson(id);
            } catch (NumberFormatException e) {
                jsonResponse = "{\"error\":\"ID inválido\"}";
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            jsonResponse = pedidoService.obtenerPedidosComoJson();
        }

        resp.getWriter().write(jsonResponse);
    }

    @Override
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
        super.destroy();
    }
}
