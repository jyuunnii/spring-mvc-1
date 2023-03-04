package hello.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* https://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html
* */
@WebServlet(name="requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printStartLine(req);
        printHeaders(req);
        printHeaderUtils(req);
        printEtc(req);

        resp.getWriter().write("ok");
    }

    //start line 정보
    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); // GET
        System.out.println("request.getProtocol() = " + request.getProtocol()); // HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); // http
        System.out.println("request.getRequestURL() = " + request.getRequestURL()); // http://localhost:8080/request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI()); // /request-header
        System.out.println("request.getQueryString() = " + request.getQueryString()); // username=hi
        System.out.println("request.isSecure() = " + request.isSecure()); // false https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
    }

    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");
        /*
          Enumeration<String> headerNames = request.getHeaderNames();
          while (headerNames.hasMoreElements()) {
              String headerName = headerNames.nextElement();
              System.out.println(headerName + ": " + request.getHeader(headerName));
          }
        */
        request.getHeaderNames()
                .asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": " + request.getHeader(headerName)));

        System.out.println("--- Headers - end ---");
    }

    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");

        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); // localhost
        System.out.println("request.getServerPort() = " + request.getServerPort()); // 8080

        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales()
                .asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale)); // ko, en_US, en, ko_KR
        System.out.println("request.getLocale() = " + request.getLocale()); // ko

        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }

        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " + request.getContentType()); // null
        System.out.println("request.getContentLength() = " + request.getContentLength()); // -1
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding()); // UTF-8

        System.out.println("--- Header 편의 조회 end ---");
    }

    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");

        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost()); // 0:0:0:0:0:0:0:1
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr()); // 0:0:0:0:0:0:0:1
        System.out.println("request.getRemotePort() = " + request.getRemotePort()); // 58557

        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " +  request.getLocalName()); // localhost
        System.out.println("request.getLocalAddr() = " +  request.getLocalAddr()); // 0:0:0:0:0:0:0:1
        System.out.println("request.getLocalPort() = " + request.getLocalPort()); // 8080

        System.out.println("--- 기타 조회 end ---");
    }


}
