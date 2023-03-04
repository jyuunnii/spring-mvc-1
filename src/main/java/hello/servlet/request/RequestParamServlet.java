package hello.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * https://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html
 * https://docs.oracle.com/javaee/6/api/javax/servlet/ServletRequest.html
 * */
@WebServlet(name="requestServlet", urlPatterns = "/request")
public class RequestParamServlet extends HttpServlet {
    // GET - query parameter & POST - HTML form 처리 예시.
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[단일 파라미터 조회]");
        System.out.println("req.getParameter(username) = " + req.getParameter("username"));
        System.out.println("req.getParameter(age) = " + req.getParameter("age"));

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        System.out.println("req.getParameterValues(username)");
        req.getParameterNames()
                .asIterator()
                .forEachRemaining((name) -> System.out.println("name = " + name));

        System.out.println("[전체 파라미터 조회] - start - 복수 파라미터 조회 불가능"); //
        req.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + "=" + req.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");

        resp.getWriter().write("ok");
    }



}
