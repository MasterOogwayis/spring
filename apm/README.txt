#方式一
java -javaagent:../elastic-apm-agent-1.18.0.jar
-Delastic.apm.service_name=zsw-javaapm01
-Delastic.apm.server_urls=http://192.168.86.101:8200
-Delastic.apm.secret_token=
-Delastic.apm.application_packages=com.zsw
-jar zsw-javaapmdemo-1.0.jar



#方式二
-javaagent:E:/2020-07-zsw/Project-Java-2020/zsw-springclouddemo/jar-file/elastic-apm-agent-1.18.0.jar
-Delastic.apm.service_name=admin01
-Delastic.apm.server_urls=http://192.168.86.101:8200
-Delastic.apm.secret_token=
-Delastic.apm.application_packages=com.zsw