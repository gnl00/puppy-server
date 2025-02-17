package one.demo._4servletcontext.engine.mapping;

import jakarta.servlet.Servlet;
import lombok.Data;

import java.util.regex.Pattern;

@Data
public class ServletMapping {

    final Pattern pattern; // 编译后的正则表达式
    final Servlet servlet; // Servlet实例

    public ServletMapping(String urlPattern, Servlet servlet) {
        this.pattern = buildPattern(urlPattern); // 编译为正则表达式
        this.servlet = servlet;
    }

    Pattern buildPattern(String urlPattern) {
        StringBuilder sb = new StringBuilder(urlPattern.length() + 16);
        sb.append('^');
        for (int i = 0; i < urlPattern.length(); i++) {
            char ch = urlPattern.charAt(i);
            if (ch == '*') {
                sb.append(".*");
            } else if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch >= '0' && ch <= '9') {
                sb.append(ch);
            } else {
                sb.append('\\').append(ch);
            }
        }
        sb.append('$');
        return Pattern.compile(sb.toString());
    }

    public boolean matches(String uri) {
        return pattern.matcher(uri).matches();
    }

}
