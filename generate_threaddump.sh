for i in `seq 1 5` ; do
  echo ${i}
  jstack `ps aux | grep java | grep spring | grep -v grep | awk '{print $2}'` >> threaddump.log
  sleep 10
done