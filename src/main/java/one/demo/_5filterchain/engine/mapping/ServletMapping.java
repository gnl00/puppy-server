package one.demo._5filterchain.engine.mapping;

import jakarta.servlet.Servlet;
import lombok.Data;
import one.demo._5filterchain.utils.MappingUtil;

import java.util.regex.Pattern;

@Data
public class ServletMapping {

    final Pattern pattern; // 编译后的正则表达式
    final Servlet servlet; // Servlet实例

    public ServletMapping(String urlPattern, Servlet servlet) {
        this.pattern = MappingUtil.buildPattern(urlPattern); // 编译为正则表达式
        this.servlet = servlet;
    }

    public boolean matches(String uri) {
        return pattern.matcher(uri).matches();
    }

}
