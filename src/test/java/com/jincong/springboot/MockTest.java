package com.jincong.springboot;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.jincong.springboot.domain.User;
import com.jincong.springboot.mapper.NewUserMapper;
import com.jincong.springboot.mapper.UserMapper;
import com.jincong.springboot.utils.ListUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = SpringbootApplication.class)
@RunWith(SpringRunner.class)
public class MockTest {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NewUserMapper newUserMapper;


    @Test
    public void testHello() {

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("userCode", "");
        criteria.andIsNull("userCode");

        List<User> userList = newUserMapper.selectByExample(example);


        //List<User> userList = userMapper.findAllUser();
        System.out.println(userList);


    }

    @Test
    public void testSplit() {

     List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

        List<List<Integer>> result1 = ListUtil.groupBySize(list, 10);

        List<List<Integer>> result2 = ListUtil.averageList(list, 4);

        System.out.println(result2);
    }

    /**
     * 自动生成表的描述文档
     */

    @Test
    public void generateDocumentByTable() {

        //数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("Jincong@163.com");
        //设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(hikariConfig);
        //生成配置
        EngineConfig engineConfig = EngineConfig.builder()
                //生成文件路径
                .fileOutputDir("E:\\java_file")
                //打开目录
                .openOutputDir(true)
                //文件类型
                .fileType(EngineFileType.MD)
                //生成模板实现
                .produceType(EngineTemplateType.freemarker).build();

        //忽略表
        ArrayList<String> ignoreTableName = new ArrayList<>();
        ignoreTableName.add("test_user");
        ignoreTableName.add("test_group");
        //忽略表前缀
        ArrayList<String> ignorePrefix = new ArrayList<>();
        ignorePrefix.add("test_");
        //忽略表后缀
        ArrayList<String> ignoreSuffix = new ArrayList<>();
        ignoreSuffix.add("_test");
        ProcessConfig processConfig = ProcessConfig.builder()
                //忽略表名
                .ignoreTableName(ignoreTableName)
                //忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
                .ignoreTableSuffix(ignoreSuffix).build();
        //配置
        Configuration config = Configuration.builder()
                //版本
                .version("1.0.0")
                //描述
                .description("数据库设计文档生成")
                //数据源
                .dataSource(dataSource)
                //生成配置
                .engineConfig(engineConfig)
                //生成配置
                .produceConfig(processConfig).build();
        //执行生成
        new DocumentationExecute(config).execute();
    }


}
