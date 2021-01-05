



cd /bing.Pan/applicationSoftware/workspace/bing.Pan-springCloud
git checkout devlp
git pull

mvn clean
mvn package -P aLiYunServer  -D skipTests


############################### eureka server
echo "Stopping SpringCloud Application for bing.Pan-springCloud: cloud-eureka"
eureka_pid=`ps -ef | grep cloud-eureka-1.0-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
if [ -n "$eureka_pid" ]
then
#!kill -9 强制终止
   echo "kill -9 的eureka_pid:" $eureka_pid
   kill -9 $eureka_pid
fi
echo "启动cloud-eureka...."
chmod 777 /bing.Pan/applicationSoftware/workspace/bing.Pan-springCloud/cloud-eureka/target/cloud-eureka-1.0-SNAPSHOT.jar
cd /bing.Pan/applicationSoftware/workspace/bing.Pan-springCloud/cloud-eureka/target
java -jar cloud-eureka-1.0-SNAPSHOT.jar &
sleep 25




############################## config_server
echo "Stopping SpringCloud Application for bing.Pan-springCloud:cloud-config-server"
config_server_pid=`ps -ef | grep cloud-config-server-1.0-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
if [ -n "$config_server_pid" ]
then
  echo "kill -9 的config_server_pid:" $config_server_pid
  kill -9 $config_server_pid
fi
echo "启动cloud-config-server...."
chmod 777 /bing.Pan/applicationSoftware/workspace/bing.Pan-springCloud/cloud-config-server/target/cloud-config-server-1.0-SNAPSHOT.jar
cd /bing.Pan/applicationSoftware/workspace/bing.Pan-springCloud/cloud-config-server/target
java -jar cloud-config-server-1.0-SNAPSHOT.jar &
sleep 25




############################# config_client
echo "Stopping SpringCloud Application for bing.Pan-springCloud:cloud-config-client"
config_client_pid=`ps -ef | grep cloud-config-client-1.0-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
if [ -n "$config_client_pid" ]
then
  echo "kill -9 的config_client_pid:" $config_client_pid
  kill -9 $config_client_pid
fi
echo "启动cloud-config-client...."
chmod 777 /bing.Pan/applicationSoftware/workspace/bing.Pan-springCloud/cloud-config-client/target/cloud-config-client-1.0-SNAPSHOT.jar
cd /bing.Pan/applicationSoftware/workspace/bing.Pan-springCloud/cloud-config-client/target
java -jar cloud-config-client-1.0-SNAPSHOT.jar &
sleep 25




########################### hystris turbine
echo "Stopping SpringCloud Application for bing.Pan-springCloud:cloud-hystrix-turbine"
hystrix_turbine_pid=`ps -ef | grep cloud-hystrix-turbine-1.0-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
if [ -n "$hystrix_turbine_pid" ]
then
  echo "kill -9 的hystrix_turbine_pid:" $hystrix_turbine_pid
  kill -9 $hystrix_turbine_pid
fi
echo "启动cloud-hystrix-dashboard...."
chmod 777 /bing.Pan/applicationSoftware/workspace/bing.Pan-springCloud/cloud-hystrix-turbine/target/cloud-hystrix-turbine-1.0-SNAPSHOT.jar
cd /bing.Pan/applicationSoftware/workspace/bing.Pan-springCloud/cloud-hystrix-turbine/target
java -jar cloud-hystrix-turbine-1.0-SNAPSHOT.jar &
sleep 25





########################### hystris dashboard
echo "Stopping SpringCloud Application for bing.Pan-springCloud:cloud-hystrix-dashboard"
hystrix_dashborad_pid=`ps -ef | grep cloud-hystrix-dashboard-1.0-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
if [ -n "$hystrix_dashborad_pid" ]
then
  echo "kill -9 的hystrix_dashborad_pid:" $hystrix_dashborad_pid
  kill -9 $hystrix_dashborad_pid
fi
echo "启动cloud-hystrix-dashboard...."
chmod 777 /bing.Pan/applicationSoftware/workspace/bing.Pan-springCloud/cloud-hystrix-dashboard/target/cloud-hystrix-dashboard-1.0-SNAPSHOT.jar
cd /bing.Pan/applicationSoftware/workspace/bing.Pan-springCloud/cloud-hystrix-dashboard/target
java -jar cloud-hystrix-dashboard-1.0-SNAPSHOT.jar &
sleep 25




########################## cloud-gateway
echo "Stopping SpringCloud Application for bing.Pan-springCloud:cloud-gateway"
gateway_pid=`ps -ef | grep cloud-gateway-1.0-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
if [ -n "$gateway_pid" ]
then
  echo "kill -9 的gateway_pid:" $gateway_pid
  kill -9 $gateway_pid
fi
echo "启动cloud-gateway...."
chmod 777 /bing.Pan/applicationSoftware/workspace/bing.Pan-springCloud/cloud-gateway/target/cloud-gateway-1.0-SNAPSHOT.jar
cd /bing.Pan/applicationSoftware/workspace/bing.Pan-springCloud/cloud-gateway/target
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8890,suspend=n -jar cloud-gateway-1.0-SNAPSHOT.jar &
sleep 25
