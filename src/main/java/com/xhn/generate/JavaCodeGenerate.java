package com.xhn.generate;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author xhn
 * @date 2025/8/29 9:34
 * @description
 */
public class JavaCodeGenerate {

    private static final Pattern TABLE_NAME_PATTERN = Pattern.compile("@TableName\\(\"([^\"]+)\"\\)");


    public static void main(String[] args) {
        //定义模块名名
        String moduleName = "sys";

        try {
            Properties props = new Properties();
            try (InputStream in = JavaCodeGenerate.class.getClassLoader()
                    .getResourceAsStream("application-dev.properties")) {
                props.load(in);
            }
            String url = props.getProperty("spring.datasource.url");
            String username = props.getProperty("spring.datasource.username");
            String password = props.getProperty("spring.datasource.password");

            //"D:\\IDEA_Workspace\\chat-wave-server\\src\\main\\java"  直接读取项目目录

            String projectPath = System.getProperty("user.dir");
            String outputDir = projectPath + "/src/main/java";
            String xmlPath = projectPath + "/src/main/resources/mapper/";
            FastAutoGenerator.create(url, username, password)
                    .globalConfig(builder ->
                            builder.author("xhn")
                                    .disableOpenDir()
                                    .outputDir(outputDir)
                    )
                    .packageConfig(builder ->
                            builder.parent("com.xhn")
                                    .moduleName(moduleName)
                                    .entity("model.base")
                                    .pathInfo(Map.of(
                                            OutputFile.xml, xmlPath
                                    ))
                    )
                    .strategyConfig(builder ->
                            builder.likeTable(new LikeTable(moduleName + "_"))
                                    .entityBuilder()
                                    .enableLombok()
                                    .enableChainModel()
                                    .enableFileOverride()
                                    .convertFileName(entityName -> {
                                        String prefix = Character.toUpperCase(moduleName.charAt(0)) + moduleName.substring(1);
                                        if (entityName.startsWith(prefix)) {
                                            entityName = entityName.substring(prefix.length());
                                        }
                                        return "Base" + entityName + "Entity";
                                    })
                                    .controllerBuilder()
                                    .enableRestStyle()
                                    .convertFileName(controllerName -> {
                                        String prefix = Character.toUpperCase(moduleName.charAt(0)) + moduleName.substring(1);
                                        if (controllerName.startsWith(prefix)) {
                                            controllerName = controllerName.substring(prefix.length());
                                        }
                                        return controllerName + "Controller";
                                    })
                                    .serviceBuilder()
                                    .convertServiceFileName(serviceName -> {
                                        String prefix = Character.toUpperCase(moduleName.charAt(0)) + moduleName.substring(1);
                                        if (serviceName.startsWith(prefix)) {
                                            serviceName = serviceName.substring(prefix.length());
                                        }
                                        return "I" + serviceName + "Service";
                                    })
                                    .convertServiceImplFileName(serviceImplName -> {
                                        String prefix = Character.toUpperCase(moduleName.charAt(0)) + moduleName.substring(1);
                                        if (serviceImplName.startsWith(prefix)) {
                                            serviceImplName = serviceImplName.substring(prefix.length());
                                        }
                                        return serviceImplName + "ServiceImpl";
                                    })
                                    .mapperBuilder()
                                    .convertMapperFileName(mapperName -> {
                                        String prefix = Character.toUpperCase(moduleName.charAt(0)) + moduleName.substring(1);
                                        if (mapperName.startsWith(prefix)) {
                                            mapperName = mapperName.substring(prefix.length());
                                        }
                                        return mapperName + "Mapper";
                                    })
                                    .convertXmlFileName(xmlName -> {
                                        String prefix = Character.toUpperCase(moduleName.charAt(0)) + moduleName.substring(1);
                                        if (xmlName.startsWith(prefix)) {
                                            xmlName = xmlName.substring(prefix.length());
                                        }
                                        return xmlName + "Mapper";
                                    })
                                    .enableBaseResultMap()
                                    .enableBaseColumnList()
                                    .build())
                    .templateEngine(new FreemarkerTemplateEngine())
                    .execute();


            //========= 生成空实体类 =========
            String modelBasePath = projectPath + "/src/main/java/com/xhn/" + moduleName + "/model/base/";
            String modelPath = projectPath + "/src/main/java/com/xhn/" + moduleName + "/model/";

            Path modelDirPath = Path.of(modelPath);
            Files.createDirectories(modelDirPath);

            File baseDir = new File(modelBasePath);
            File[] baseFiles = baseDir.listFiles((dir, name) -> name.endsWith(".java"));
            List<EntityMeta> entityMetaList = new ArrayList<>();
            if (baseFiles != null) {
                for (File baseFile : baseFiles) {
                    String baseFileName = baseFile.getName();
                    if (!baseFileName.startsWith("Base") || !baseFileName.endsWith(".java")) {
                        continue;
                    }
                    String baseClassName = baseFileName.replace(".java", "");
                    String domainClassName = baseClassName.replaceFirst("^Base", "");
                    String packageName = "com.xhn." + moduleName + ".model";
                    String baseQualifiedName = packageName + ".base." + baseClassName;
                    String domainQualifiedName = packageName + "." + domainClassName;

                    String baseContent = Files.readString(baseFile.toPath(), StandardCharsets.UTF_8);
                    String tableName = extractTableName(baseContent);

                    File targetFile = new File(modelDirPath.toFile(), domainClassName + ".java");
                    if (!targetFile.exists()) {
                        StringBuilder domainContent = new StringBuilder();
                        domainContent.append("package ").append(packageName).append(";\n\n");
                        if (tableName != null) {
                            domainContent.append("import com.baomidou.mybatisplus.annotation.TableName;\n");
                        }
                        domainContent.append("import java.io.Serializable;\n");
                        domainContent.append("import java.io.Serial;\n");
                        domainContent.append("import com.xhn.").append(moduleName).append(".model.base.")
                                .append(baseClassName).append(";\n\n");
                        if (tableName != null) {
                            domainContent.append("@TableName(\"").append(tableName).append("\")\n");
                        }
                        domainContent.append("public class ").append(domainClassName).append(" extends ")
                                .append(baseClassName).append(" implements Serializable {\n\n")
                                .append("    @Serial\n")
                                .append("    private static final long serialVersionUID = 1L;\n\n")
                                .append("}\n");

                        try {
                            Files.writeString(targetFile.toPath(), domainContent.toString(), StandardCharsets.UTF_8);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("文件已存在，跳过: " + targetFile.getPath());
                    }

                    entityMetaList.add(new EntityMeta(baseClassName, domainClassName,
                            baseQualifiedName, domainQualifiedName, tableName));
                }
            }

            syncLayerDependencies(projectPath, moduleName, entityMetaList);


            String controllerPath = projectPath + "/src/main/java/com/xhn/" + moduleName + "/controller/";
            File controllerDir = new File(controllerPath);
            File[] controllerFiles = controllerDir.listFiles((dir, name) -> name.endsWith(".java"));
            if (controllerFiles != null) {
                for (File controllerFile : controllerFiles) {
                    try {
                        String content = Files.readString(controllerFile.toPath(), StandardCharsets.UTF_8);

                        // 正则匹配 @RequestMapping("/sys/baseXXXEntity") 或 @RequestMapping("/sys/BaseXXXEntity")
                        Pattern pattern = Pattern.compile("@RequestMapping\\(\"(/" + moduleName + "/)(?i)base(\\w+)Entity\"\\)");
                        Matcher matcher = pattern.matcher(content);
                        StringBuffer sb = new StringBuffer();

                        while (matcher.find()) {
                            String prefix = matcher.group(1); // /sys/
                            String name = matcher.group(2);   // XXX
                            // 首字母小写
                            name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                            matcher.appendReplacement(sb, "@RequestMapping(\"" + prefix + name + "\")");
                        }
                        matcher.appendTail(sb);

                        Files.writeString(controllerFile.toPath(), sb.toString(), StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }





        } catch (Exception e) {
            e.printStackTrace();
        }


        //找到上面生成的model.base下的代码，去掉前面 的base, 这个类继承原本的base类，放在model下


        //找到每一个controller，找到他的@RequestMapping("/sys/baseDictDataEntity") 比如@RequestMapping("/sys/baseDictDataEntity") 第二个路径，去掉前面的base 和后面的Entity
    }

    private static String extractTableName(String baseContent) {
        Matcher matcher = TABLE_NAME_PATTERN.matcher(baseContent);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static void syncLayerDependencies(String projectPath, String moduleName, List<EntityMeta> entityMetaList) {
        if (entityMetaList.isEmpty()) {
            return;
        }
        Path serviceDir = Path.of(projectPath, "src", "main", "java", "com", "xhn", moduleName, "service");
        Path mapperDir = Path.of(projectPath, "src", "main", "java", "com", "xhn", moduleName, "mapper");
        Path mapperXmlDir = Path.of(projectPath, "src", "main", "resources", "mapper");

        rewriteJavaDirectory(serviceDir, entityMetaList);
        rewriteJavaDirectory(mapperDir, entityMetaList);
        rewriteXmlDirectory(mapperXmlDir, entityMetaList);
    }

    private static void rewriteJavaDirectory(Path dir, List<EntityMeta> entityMetaList) {
        if (!Files.isDirectory(dir)) {
            return;
        }
        try (Stream<Path> stream = Files.walk(dir)) {
            stream.filter(path -> Files.isRegularFile(path) && path.toString().endsWith(".java"))
                    .forEach(path -> rewriteJavaFile(path, entityMetaList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void rewriteJavaFile(Path file, List<EntityMeta> entityMetaList) {
        try {
            String original = Files.readString(file, StandardCharsets.UTF_8);
            String updated = original;
            for (EntityMeta meta : entityMetaList) {
                String baseImport = "import " + meta.baseQualifiedName() + ";";
                String domainImport = "import " + meta.domainQualifiedName() + ";";
                if (updated.contains(baseImport)) {
                    updated = updated.replace(baseImport, domainImport);
                }
                updated = updated.replace(meta.baseQualifiedName(), meta.domainQualifiedName());
                updated = replaceSimpleType(updated, meta);
            }
            if (!updated.equals(original)) {
                Files.writeString(file, updated, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String replaceSimpleType(String content, EntityMeta meta) {
        Pattern simplePattern = Pattern.compile("\\b" + Pattern.quote(meta.baseClassName()) + "\\b");
        return simplePattern.matcher(content).replaceAll(meta.domainClassName());
    }

    private static void rewriteXmlDirectory(Path dir, List<EntityMeta> entityMetaList) {
        if (!Files.isDirectory(dir)) {
            return;
        }
        try (Stream<Path> stream = Files.walk(dir)) {
            stream.filter(path -> Files.isRegularFile(path) && path.toString().endsWith(".xml"))
                    .forEach(path -> rewriteXmlFile(path, entityMetaList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void rewriteXmlFile(Path file, List<EntityMeta> entityMetaList) {
        try {
            String original = Files.readString(file, StandardCharsets.UTF_8);
            String updated = original;
            for (EntityMeta meta : entityMetaList) {
                updated = updated.replace(meta.baseQualifiedName(), meta.domainQualifiedName());
            }
            if (!updated.equals(original)) {
                Files.writeString(file, updated, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private record EntityMeta(String baseClassName, String domainClassName,
                              String baseQualifiedName, String domainQualifiedName,
                              String tableName) {
    }
}
