# Troubleshooting-Performance

## How to

1. Enable all Actuator metrics:
    ```sh
    management.endpoints.web.exposure.include=*
    ```

1. Start environment:

    ```sh
    make.sh
    ```

1. Run the Jmeter test

1. Take snapshots of threaddump
    ```sh
    sh generate_threaddump.sh
    ```

VM configs 

```sh

-XX:+UseContainerSupport
-XX:MaxRAMPercentage=50
-XX:+UseG1GC
-XX:MaxGCPauseMillis=200
-XX:ParallelGCThreads=5
-XX:ConcGCThreads=5


-Xms500m
-Xmx500m
-XX:+PrintGCDetails
-XX:+PrintGCDateStamps
-XX:+PrintGCTimeStamps
-Xloggc:./gc.log
-XX:+UseG1GC
-XX:MaxGCPauseMillis=200
-XX:ParallelGCThreads=20
-XX:ConcGCThreads=5
-XX:InitiatingHeapOccupancyPercent=70
-XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:StartFlightRecording=duration=60s,filename=profiling.jfr
```

**Urls:**

Application: http://localhost:8080/stock-quotes

Prometheus: http://localhost:9090

Grafana: http://localhost:3000

ThreadDump: http://localhost:8080/actuator/threaddump

HeapDump: http://localhost:8080/actuator/heapdump

JVM-Dashboard: https://grafana.com/grafana/dashboards/4701


**Jmeter Test:**

JMeter.jmx

**Important:**
When you run the Grafana 1st time, user and password are **admin**.


GC explained:
https://sematext.com/blog/java-garbage-collection/

Important:
Actually, any object larger than 50% of the region size is considered humongous. Those objects are not allocated in the young generation space, but instead, they are put directly in the Tenured generation. Such objects can increase the pause time of the garbage collector and can increase the risk of triggering the Full GC because of running out of continued free space.

JMC
https://www.oracle.com/webapps/redirect/signon?nexturl=https://download.oracle.com/otn-pub/java/jmc/7.1.2%2B04/a4634525489241b9a9e1aa73d9e118e6/jmc-7.1.2_linux-x64.tar.gz




Interesting Grafana Dashboards:
https://grafana.com/grafana/dashboards/10280
10280

## TODO
**Apply regression to optimize throuput. Use docker-compose limits and variate the number of tasks**
