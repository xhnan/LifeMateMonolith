package com.xhn.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.InputStream;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author xhn
 * @date 2025/7/4 9:09
 * @description
 */
public class JavaCodeGenerate {


    public static void main(String[] args) throws Exception {



        String path = JavaCodeGenerate.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        java.io.File file = new java.io.File(path);
        String moduleRoot = file.getParentFile().getParent();
        String moduleName = "study";

        Properties props = loadProperties("application-dev.properties");

        FastAutoGenerator.create(props.getProperty("spring.datasource.url"), props.getProperty("spring.datasource.username"), props.getProperty("spring.datasource.password"))
                .globalConfig(builder -> {
                    builder.author("xhn") // 设置作者
                            .outputDir(moduleRoot+"/src/main/java"); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("com.xhn") // 设置父包名
                                .moduleName(moduleName) // 设置父包模块名
                                .entity("model")
                                .pathInfo(Collections.singletonMap(OutputFile.xml, moduleRoot+"/src/main/resources/mapper"))// 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder

                                .likeTable(new LikeTable(moduleName+"%")) // 设置过滤表名
                                .entityBuilder()
                                .enableLombok() // 启用 Lombok
                                .enableTableFieldAnnotation() // 启用字段注解
                                .enableFileOverride() // 启用文件覆盖
                                .controllerBuilder()
                                .enableRestStyle() // 启用 REST 风格
                                .serviceBuilder()
                                .formatServiceFileName("%sService") // 设置服务类文件名格式

                )
                .injectionConfig(consumer -> {
                    consumer.beforeOutputFile(((tableInfo, objectMap) -> {
                        // 直接修改 objectMap，而不是使用全局 customMap
                        Map<String,Object> aPackage = (Map<String, Object>) objectMap.get("package");
                        objectMap.put("packageName", aPackage.get("Entity"));

                        // 保留原有 entity，不需要重新赋值
                        // 保存表名，可以在模板中使用
                        objectMap.put("tableName", tableInfo.getName());

                        List<Object> importPackages = (List<Object>) objectMap.get("importPackages");
                        objectMap.put("importPackage", importPackages.get(1));
                        objectMap.put("date", java.time.LocalDate.now().toString());
                    }));

                    consumer.customFile(new CustomFile.Builder()
                            .fileName("DTO.java") // 使用 %s 占位符，会自动替换为实体名
                            .templatePath("templates/entityDTO.java.ftl")
                            .filePath(moduleRoot + "/src/main/java/com/xhn/"+moduleName+"/model/")
                            .build());
                })

                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }


    public static Properties loadProperties(String fileName) throws Exception {
        Properties props = new Properties();
        try (InputStream input = JavaCodeGenerate.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("配置文件未找到: " + fileName);
            }
            props.load(input);
        }
        return props;
    }
}
