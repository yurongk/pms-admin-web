package com.yurongku.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        PaginationInnerInterceptor paginationInnerInterceptor =
                new PaginationInnerInterceptor(DbType.POSTGRE_SQL);
        paginationInnerInterceptor.setOverflow(true);   // 页码超过最大页数，返回第一页
        paginationInnerInterceptor.setMaxLimit(1000L);  // 最大每页限制 1000

        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }
}
