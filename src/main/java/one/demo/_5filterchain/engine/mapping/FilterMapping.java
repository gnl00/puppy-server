package one.demo._5filterchain.engine.mapping;

import jakarta.servlet.Filter;
import one.demo._5filterchain.utils.MappingUtil;

import java.util.regex.Pattern;

public class FilterMapping {

    final Pattern pattern; // 编译后的正则表达式
    final Filter filter;

    public FilterMapping(String urlPattern, Filter filter) {
        this.pattern = MappingUtil.buildPattern(urlPattern); // 编译为正则表达式
        this.filter = filter;
    }

}
