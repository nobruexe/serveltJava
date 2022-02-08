package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/novaEmpresa")
public class NovaEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Cadastrando nova empresa.");		
		String nomeEmpresa = request.getParameter("nome");
		String paramDataAbertura = request.getParameter("data");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dataAbertura = null;
		
		try {
			dataAbertura = sdf.parse(paramDataAbertura);
		} catch (ParseException e) {
			//Modelo catch and reThrow
			throw new ServletException(e);
		}
		
		PrintWriter out = response.getWriter();
		
		Empresa empresa = new Empresa();
		empresa.setNome(nomeEmpresa);
		empresa.setDataAbertura(dataAbertura);
		
		Banco banco = new Banco();
		banco.adiciona(empresa);
		
		request.setAttribute("empresa", nomeEmpresa);
		
		//Redirecionamento client-side
		response.sendRedirect("listaEmpresas");
		
	/** 
	 * Comentado porque n�o vamos mais usar o redirecionamento server-side, 
	 * porque a cada F5 ele cadastrava a mesma empresa na p�gina listaEmpresas.jsp
	 * Detalhe: mostrava a lista mas a URL se mantinha em novaEmpresa.jsp  
	 *
		//chamar o JSP
		RequestDispatcher rd = request.getRequestDispatcher("/listaEmpresas");
		request.setAttribute("empresa", nomeEmpresa);
		rd.forward(request, response);
	*/
	}

}
