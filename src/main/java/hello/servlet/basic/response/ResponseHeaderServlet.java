package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK); //200이라고 직접 쓰는 것보단 이 형식이 좋음(매직 넘버가 아니기 때문)

        //[response-header] F12를 통해 확인가능
        response.setHeader("Content-Type", "text/plain;charset=utf-8");//인코딩 셋팅 안해주면 응답에 한글이 깨질 수 있음
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //캐시 완전히 무효화
        response.setHeader("Pragma", "no-cache");//과거 버전까지 무효화
        response.setHeader("my-header", "hello");

        //[Header 편의 메서드]
        content(response);
        cookie(response);
        redirect(response);

        //[message body]
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    //content 편의 메서드
    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain"); //이 2가지를 하면 자동으로 "text/plain;charset=utf-8" 생성 (22번 라인 생략가능)
        response.setCharacterEncoding("utf-8"); //이 2가지를 하면 자동으로 "text/plain;charset=utf-8" 생성
        //response.setContentLength(2); //(숫자 생략시 자동 생성)
    }

    //cookie 편의 메서드
    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good"); //48, 49, 50을 통해 47번 라인과 동일하게 작동
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    //redirect 편의 메서드
    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
