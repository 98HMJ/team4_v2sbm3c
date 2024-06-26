package dev.mvc.team4;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")  // 설정 파일 위치
@MapperScan(basePackages= {"dev.mvc.admin",
                            "dev.mvc.community",
                            "dev.mvc.communitycate",
                            "dev.mvc.report.community",
                            "dev.mvc.report.reply",
                            "dev.mvc.member",
                            "dev.mvc.reply",
                            "dev.mvc.nephron",
                            "dev.mvc.nephroncate",
                            "dev.mvc.trash",
                            "dev.mvc.trashcate",
                            "dev.mvc.search",
                            "dev.mvc.log.adminlog",
                            "dev.mvc.log.memberlog",
                            "dev.mvc.singo",
                            "dev.mvc.rereply",
                            "dev.mvc.bookmark",
                            "dev.mvc.trash_exploration",
                            "dev.mvc.report",
                            "dev.mvc.ai_sort",
                            "dev.mvc.ai_history"}) // DAO interface를 찾는 위치
public class DatabaseConfiguration {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari")  // 설정 파일의 접두사 선언 spring.datasource.hikari....
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }
    
    @Bean
    public DataSource dataSource() throws Exception{
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println(dataSource.toString());  // 정상적으로 연결 되었는지 해시코드로 확인
        return dataSource;
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // "/src/main/resources/mybatis" 폴더의 파일명이 "xml"로 끝나는 파일 매핑
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/**/*.xml"));
        
        return sqlSessionFactoryBean.getObject();
    }
    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
