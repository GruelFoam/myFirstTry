package com.example.demo.common.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.example.demo.mapper"}, sqlSessionTemplateRef = "consoleSqlSessionTemplate")
public class DatabaseConfig {

    @Value("${db.driver}")
    String driver;
    @Value("${db.url}")
    String url;
    @Value("${db.username}")
    String username;
    @Value("${db.password}")
    String password;

    @Bean("consoleDataSource")
    public DataSource initDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        System.out.println(driver);
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
//        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "consoleSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(initDataSource());
//        try {
//            // 配置mapper的扫描，找到所有的mapper.xml映射文件
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*.xml"));
//             加载全局的配置文件
//            bean.setConfigLocation(new DefaultResourceLoader().getResource("mybatis-config.xml"));
        return bean.getObject();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    @Bean(name = "consoleDataSourceTransactionManager")
    public DataSourceTransactionManager clusterDataSourceTransactionManager(){
        return new DataSourceTransactionManager(initDataSource());
    }

    @Bean(name = "consoleSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        // 使用上面配置的Factory
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
