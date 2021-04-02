# Troubleshooting-Performance

This application run a spring boot application with prometheus and graphana.

## Starting Analysis

1. Enable actuator metrics on **application.properties** (Already configured):

   ```sh
   management.endpoints.web.exposure.include=*
   ```

1. Start environment:

   ```sh
   make.sh
   ```

1. Run the Jmeter script

   ```sh
   jmeter -n -t Jmeter.jmx -l results.jtl
   ```

1. Take snapshots of threaddump

   ```sh
   sh generate_threaddump.sh
   ```

1. Alternate JVM parameters to see differences of resources:

```sh
   #Java Heap
   -Xms500m
   -Xmx500m
   -XX:+UseContainerSupport
   -XX:InitiatingHeapOccupancyPercent=70
   -XX:MaxRAMPercentage=50
   -XX:MaxGCPauseMillis=200
   -XX:ParallelGCThreads=5
   #Non Heap
   -XX:MaxDirectMemorySize=128m
   -XX:CompressedClassSpaceSize=64m
   -XX:MaxMetaspaceSize=128m
   -XX:+UseStringDeduplication
   # GC
   -XX:+UseG1GC
   -XX:MaxGCPauseMillis=200
   -XX:ConcGCThreads=5
   -XX:ParallelGCThreads=20
   -Xloggc:./gc.log
   -XX:+PrintGCDetails
   -XX:+PrintGCDateStamps
   -XX:+PrintGCTimeStamps
   # Monitoring
   -XX:NativeMemoryTracking=summary
   -XX:+UnlockCommercialFeatures
   -XX:+FlightRecorder
   -XX:StartFlightRecording=duration=60s,filename=profiling.jfr
```

### Services and port numbers

| Service     | Port Number | Type/Tech       |
| ----------- | ----------- | --------------- |
| Mysql       | 5432        | DB              |
| Application | 8080        | Spring boot     |
| Prometheus  | 9090        | DB              |
| Grafana     | 3030        | Metrics Monitor |

#### Urls

Application: http://localhost:8080/stock-quotes

Prometheus: http://localhost:9090

Grafana: http://localhost:3000

ThreadDump: http://localhost:8080/actuator/threaddump

HeapDump: http://localhost:8080/actuator/heapdump

JVM-Dashboard: https://grafana.com/grafana/dashboards/4701

#### Grafana

user: **admin**

password: **admin**

Dashboards:

      1. Micrometer - https://grafana.com/grafana/dashboards/4701

      2. Spring boot - https://grafana.com/grafana/dashboards/10280

### Analytics

Goto analytics folder and follow instructions of **Readme.md**.

#### Notes

**GC explained**:
* https://sematext.com/blog/java-garbage-collection/

Important:
Actually, any object larger than 50% of the region size is considered humongous. Those objects are not allocated in the
young generation space, but instead, they are put directly in the Tenured generation. Such objects can increase the
pause time of the garbage collector and can increase the risk of triggering the Full GC because of running out of
continued free space.

**JMC**
* https://www.oracle.com/webapps/redirect/signon?nexturl=https://download.oracle.com/otn-pub/java/jmc/7.1.2%2B04/a4634525489241b9a9e1aa73d9e118e6/jmc-7.1.2_linux-x64.tar.gz

**JVM Footprint**
* https://stackoverflow.com/questions/53451103/java-using-much-more-memory-than-heap-size-or-size-correctly-docker-memory-limi/53624438#53624438
* https://www.youtube.com/watch?v=c755fFv1Rnk

**Improving Performance and Footprint**
* https://medium.com/@jean_sossmeier/spring-boot-jvm-1eea422be930

